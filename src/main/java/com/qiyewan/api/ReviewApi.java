package com.qiyewan.api;

import com.qiyewan.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 评论
 */

@RestController
public class ReviewApi {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ReviewService reviewService;

}
