package com.qiyewan.service;

import com.qiyewan.domain.Cart;
import com.qiyewan.exceptions.IllegalActionException;
import com.qiyewan.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by lhzbxx on 2016/10/28.
 *
 * 购物车
 */

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Page<Cart> getCartsByUser(Long userId, Pageable pageable) {
        return cartRepository.findByUserId(userId, pageable);
    }

    @Override
    public Cart saveCart(Long userId, Cart cart) {
        cart.setUserId(userId);
        cartRepository.saveAndFlush(cart);
        return cart;
    }

    @Override
    public void deleteCart(Long userId, Long id) {
        Cart cart = cartRepository.findOne(id);
        if ( ! cart.getUserId().equals(userId)) {
            throw new IllegalActionException("无法删除其他人的购物车内容。");
        }
    }

}
