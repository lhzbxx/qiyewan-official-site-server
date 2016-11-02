package com.qiyewan.service;

import com.qiyewan.domain.Cart;
import com.qiyewan.domain.Product;
import com.qiyewan.exceptions.IllegalActionException;
import com.qiyewan.exceptions.NotFoundException;
import com.qiyewan.repository.CartRepository;
import com.qiyewan.repository.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Cart> getCartsByUser(Long userId, Pageable pageable) {
        return cartRepository.findByUserId(userId, pageable);
    }

    @Override
    public Cart saveCart(Long userId, Cart cart) {
        cart.setUserId(userId);
        Product product = productRepository.findFirstBySerialId(cart.getSerialId());
        if (product == null)
            throw new NotFoundException("Error.Product.NOT_EXIST");
        cart.setProduct(product);
        cartRepository.saveAndFlush(cart);
        return cart;
    }

    @Override
    public Cart updateCart(Long userId, Cart cart) {
        if ( ! cart.getUserId().equals(userId))
            throw new IllegalActionException("Error.Cart.NOT_YOUR_CART");
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public void deleteCart(Long userId, Long id) {
        Cart cart = cartRepository.findOne(id);
        if ( ! cart.getUserId().equals(userId)) {
            throw new IllegalActionException("Error.Cart.NOT_YOUR_CART");
        }
    }

}
