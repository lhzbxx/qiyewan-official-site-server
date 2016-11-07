package com.qiyewan.service;

import com.qiyewan.domain.Article;
import com.qiyewan.dto.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

/**
 * Created by lhzbxx on 2016/11/3.
 *
 * 文章
 */

public interface ArticleService {

    Article getArticle(Long id);

    Page<Article> getArticlesByCategory(String category, Pageable pageable);

    Article save(Article article);

    ArticleDto findArticleNode(Long id);
    Integer countAuthorArticles(String author);
}
