package com.qiyewan.repository;

import com.qiyewan.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 评论
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByProductSerialId(String serialId, Pageable pageable);
}
