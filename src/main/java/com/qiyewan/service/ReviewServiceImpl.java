package com.qiyewan.service;

import com.qiyewan.domain.Review;
import com.qiyewan.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by lhzbxx on 2016/10/28.
 *
 * 评论
 */

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Page<Review> getReviewsBySerialId(String serialId, Pageable pageable) {
        return reviewRepository.findBySerialId(serialId, pageable);
    }

}
