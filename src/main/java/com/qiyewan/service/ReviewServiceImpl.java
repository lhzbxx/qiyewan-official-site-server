package com.qiyewan.service;

import com.qiyewan.domain.Order;
import com.qiyewan.domain.Review;
import com.qiyewan.exceptions.IllegalActionException;
import com.qiyewan.exceptions.NotFoundException;
import com.qiyewan.repository.OrderRepository;
import com.qiyewan.repository.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<Review> getReviewsBySerialId(String serialId, Pageable pageable) {
        return reviewRepository.findBySerialId(serialId, pageable);
    }

    @Override
    public Review addReview(Long userId, Review review) {
//        Order order = orderRepository.findBySerialId(review.getSerialId());
//        checkOrder(userId, order);
//        if (order.getOrderState() != OrderState.Paid)
//            throw new IllegalActionException("Error.Review.ORDER_UNPAID_OR_REVIEWED");
//        order.setOrderState(OrderState.Reviewed);
//        Product product = productRepository.findFirstBySerialId(order.getProductSerialId());
//        int num = product.getPurchaseNumber();
//        product.setPurchaseNumber(num + 1);
//        product.setRate((num * product.getRate() + review.getStar()) / (num + 1));
//        review.setProductSerialId(product.getSerialId());
//        orderRepository.save(order);
//        productRepository.save(product);
        return reviewRepository.save(review);
    }

    private void checkOrder(Long userId, Order order) {
        if (order == null)
            throw new NotFoundException("Error.Order.NOT_EXIST");
        if ( ! userId.equals(order.getUserId()))
            throw new IllegalActionException("Error.Review.NOT_YOUR_ORDER");
    }

}
