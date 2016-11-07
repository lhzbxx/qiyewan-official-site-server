package com.qiyewan.repository;

import com.qiyewan.domain.Article;
import com.qiyewan.dto.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 文章
 */

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByCategory(String category, Pageable pageable);

    @Query("select a from Article  a where a.author = ?1")
    List<Article> findAllByAuthor(String author);
}
