package com.qiyewan.repository;

import com.qiyewan.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 文章-分类
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
