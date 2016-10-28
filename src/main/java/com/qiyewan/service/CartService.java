package com.qiyewan.service;

import com.qiyewan.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 购物车
 */

public interface CartService {

    Page<Cart> getCartsByUser(Long userId, Pageable pageable);

    Cart saveCart(Long userId, Cart cart);

    void deleteCart(Long userId, Long id);

}
