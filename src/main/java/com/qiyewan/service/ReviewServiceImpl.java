package com.qiyewan.service;

import com.qiyewan.domain.*;
import com.qiyewan.enums.OrderState;
import com.qiyewan.exceptions.IllegalActionException;
import com.qiyewan.exceptions.NotFoundException;
import com.qiyewan.repository.OrderRepository;
import com.qiyewan.repository.ProductRepository;
import com.qiyewan.repository.ReviewRepository;
import com.qiyewan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/28.
 * <p>
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
    public Review addReview(Long userId, Review review) {
        Order order = orderRepository.findBySerialId(review.getSerialId());
        checkOrder(userId, order);
        OrderDetail orderDetail = null;
        if (order.getOrderState() != OrderState.Paid)
            throw new IllegalActionException("Error.Review.ORDER_UNPAID_OR_REVIEWED");
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
            order.setOrderState(OrderState.Reviewed);
            order.setUpdateAt(new Date());
            orderRepository.save(order);
        }
        Product product = productRepository.findBySerialId(review.getProductSerialId());
        int num = product.getPurchaseNumber();
        product.setPurchaseNumber(num + 1);
        product.setRate((num * product.getRate() + review.getStar()) / (num + 1));
        review.setProductSerialId(product.getSerialId());
        review.setUser(userRepository.findOne(userId));
        for (ReviewTag tag : review.getTags()) {
            tag.setProductSerialId(product.getSerialId());
        }
        productRepository.save(product);
        return reviewRepository.save(review);
    }

    private void checkOrder(Long userId, Order order) {
        if (order == null)
            throw new NotFoundException("Error.Order.NOT_EXIST");
        if (!userId.equals(order.getUserId()))
            throw new IllegalActionException("Error.Review.NOT_YOUR_ORDER");
    }

}
