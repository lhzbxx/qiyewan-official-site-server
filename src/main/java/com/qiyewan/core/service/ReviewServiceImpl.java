package com.qiyewan.core.service;

import com.qiyewan.core.domain.*;
import com.qiyewan.common.enums.OrderStage;
import com.qiyewan.common.exceptions.InvalidRequestException;
import com.qiyewan.common.exceptions.NotFoundException;
import com.qiyewan.core.domain.OrderRepository;
import com.qiyewan.core.domain.ProductRepository;
import com.qiyewan.core.domain.ReviewRepository;
import com.qiyewan.core.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/28.
 *
 * 评论
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<Review> getReviewsByProductSerialId(String serialId, Pageable pageable) {
        return reviewRepository.findByProductSerialId(serialId, pageable);
    }

    @Override
    @Transactional
    public Review addReview(Long userId, Review review) {
        Order order = orderRepository.findBySerialId(review.getSerialId());
        checkOrder(userId, order);
        if (order.getOrderStage() != OrderStage.PAID)
            throw new InvalidRequestException("订单未支付或已评价。");
        for (OrderDetail detail : order.getDetails()) {
            if (detail.getProductSerialId().equals(review.getProductSerialId())) {
                detail.setIsReviewed(true);
                review.setAmount(detail.getAmount());
                break;
            }
        }
        int count = 0;
        for (OrderDetail detail : order.getDetails()) {
            if (!detail.getIsReviewed()) {
                break;
            }
            count += 1;
        }
        if (count == order.getDetails().size()) {
            order.setOrderStage(OrderStage.REVIEWED);
            order.setUpdateAt(new Date());
            orderRepository.save(order);
        }
        Product product = productRepository.findBySerialId(review.getProductSerialId());
        int num = product.getPurchaseNumber();
        product.setPurchaseNumber(num + 1);
        product.setRate((num * product.getRate() + review.getStar()) / (num + 1));
        review.setProductSerialId(product.getSerialId());
        review.setUser(userRepository.findOne(userId));
        review.setBuyAt(order.getCreateAt());
        for (ReviewTag tag : review.getTags()) {
            tag.setProductSerialId(product.getSerialId());
        }
        productRepository.save(product);
        return reviewRepository.save(review);
    }

    private void checkOrder(Long userId, Order order) {
        if (order == null)
            throw new NotFoundException("订单不存在。");
        if (!userId.equals(order.getUserId()))
            throw new InvalidRequestException("不能评价别人的订单。");
    }
}
