package com.qiyewan.service;

import com.qiyewan.domain.Article;
import com.qiyewan.domain.Category;
import com.qiyewan.exceptions.NotFoundException;
import com.qiyewan.repository.ArticleRepository;
import com.qiyewan.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lhzbxx on 2016/11/3.
 *
 * 文章
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

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
}
