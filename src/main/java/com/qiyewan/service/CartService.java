package com.qiyewan.service;

import com.qiyewan.domain.Cart;
import com.qiyewan.domain.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 购物车
 */

public interface CartService {
    Page<Cart> getCartsByUser(Long userId, Pageable pageable);

    Cart saveCart(Long userId, Cart cart, boolean isOverride);

    Cart updateCart(Long userId, Cart cart);

    OrderDetail convertToOrderDetail(Long userId, Long cartId);

    void deleteCart(Long userId, Long id);

    void deleteCarts(Long userId, List<Long> cartIds);
}
