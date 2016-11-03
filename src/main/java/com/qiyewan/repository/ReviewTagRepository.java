package com.qiyewan.repository;

import com.qiyewan.domain.ReviewTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 评论-标签
 */

@Repository
public interface ReviewTagRepository extends JpaRepository<ReviewTag, Long> {
}
