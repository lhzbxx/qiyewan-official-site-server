package com.qiyewan.repository;

import com.qiyewan.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 文章
 */

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByCategory(String category, Pageable pageable);

}
