package com.qiyewan.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiyewan.domain.Article;
import com.qiyewan.dto.ArticleDto;
import com.qiyewan.exceptions.NotFoundException;
import com.qiyewan.repository.ArticleRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by lhzbxx on 2016/11/3.
 *
 * 文章
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public Article getArticle(Long id) {
        Article article = articleRepository.findOne(id);
        if (article == null)
            throw new NotFoundException("Error.Article.NOT_EXIST");
        article.setViewers(article.getViewers() + 1);
        articleRepository.save(article);
        return article;
    }

    @Override
    public Page<Article> getArticlesByCategory(String category, Pageable pageable) {
        return articleRepository.findByCategory(category, pageable);
    }

    @Override
    public Article save(Article article){
        return articleRepository.save(article);
    }

    @Override
    public ArticleDto findArticleNode(Long id) throws NotFoundException{
        String currHQL = "from Article where id = " + id;
        String preHQL = "from Article where id < " + id + " order by id desc";
        String nextHQL = "from Article where id > " + id + " order by id asc";
        String firstHQL = "from Article order by id asc";
        String lastHQL = "from Article order by id desc";

        //region find transaction
        Session session = entityManager.unwrap(Session.class);
        Transaction transaction = session.beginTransaction();

        Query currQuery = session.createQuery(currHQL);
        List<Article> currList = currQuery.list();
        if(currList.isEmpty()) throw new NotFoundException("该文章不存在");

        Query preQuery = session.createQuery(preHQL).setMaxResults(1);
        List<Article> preList = preQuery.list();
        preList = preList.isEmpty() ? session.createQuery(lastHQL).setMaxResults(1).list() : preList;

        Query nextQuery = session.createQuery(nextHQL).setMaxResults(1);
        List<Article> nextList = nextQuery.list();
        nextList = nextList.isEmpty() ? session.createQuery(firstHQL).setMaxResults(1).list() : nextList;

        transaction.commit();
        session.close();
        //endregion

        ArticleDto articleDto = objectMapper.convertValue(currList.get(0), ArticleDto.class);
        articleDto.setPre(preList.get(0));
        articleDto.setNext(nextList.get(0));

        return articleDto;
    }

    @Override
    public Integer countAuthorArticles(String author) {
       List<Article> articles = articleRepository.findAllByAuthor(author);
        return articles.size();
    }
}
