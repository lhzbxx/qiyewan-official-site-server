package com.qiyewan.core.web;

import com.qiyewan.core.domain.Review;
import com.qiyewan.core.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 评论
 */
@RestController
public class ReviewController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ReviewService reviewService;

    @CrossOrigin
    @PostMapping("/reviews")
    public Review add(@Validated @RequestBody Review review) {
        Long userId = (Long) request.getAttribute("userId");
        return reviewService.addReview(userId, review);
    }
}
