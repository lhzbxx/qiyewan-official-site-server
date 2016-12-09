package com.qiyewan.core.service;

import com.qiyewan.core.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 评论
 */
public interface ReviewService {
    Page<Review> getReviewsByProductSerialId(String serialId, Pageable pageable);

    Review addReview(Long userId, Review review);
}
