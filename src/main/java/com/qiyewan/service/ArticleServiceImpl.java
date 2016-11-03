package com.qiyewan.service;

import com.qiyewan.domain.Article;
import com.qiyewan.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<Article> getArticlesByCategory(String category, Pageable pageable) {
        return articleRepository.findByCategory(category, pageable);
    }

}
