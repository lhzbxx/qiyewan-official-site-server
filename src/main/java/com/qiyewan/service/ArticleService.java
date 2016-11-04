package com.qiyewan.service;

import com.qiyewan.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lhzbxx on 2016/11/3.
 *
 * 文章
 */

public interface ArticleService {

    Article getArticle(Long id);

    Page<Article> getArticlesByCategory(String category, Pageable pageable);

}
