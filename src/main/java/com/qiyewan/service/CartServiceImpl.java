package com.qiyewan.service;

import com.qiyewan.domain.Cart;
import com.qiyewan.domain.OrderDetail;
import com.qiyewan.domain.Product;
import com.qiyewan.exceptions.IllegalActionException;
import com.qiyewan.exceptions.InvalidParamException;
import com.qiyewan.exceptions.NotFoundException;
import com.qiyewan.repository.CartRepository;
import com.qiyewan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Product product = productRepository.findBySerialId(cart.getSerialId());
        if (product == null)
            throw new NotFoundException("Error.Product.NOT_EXIST");
        Cart c = cartRepository.findFirstBySerialId(product.getSerialId());
        if (c != null) {
            c.setAmount(c.getAmount() + 1);
            cartRepository.saveAndFlush(c);
            return c;
        } else {
            cart.setProduct(product);
            cartRepository.saveAndFlush(cart);
            return cart;
        }
    }

    @Override
    public Cart updateCart(Long userId, Cart cart) {
        if (cart.getId() == null)
            throw new InvalidParamException("Error.Cart.NO_CART_ID");
        Cart c = cartRepository.findOne(cart.getId());
        checkCart(userId, c);
        cartRepository.save(c.copy(cart));
        return cart;
    }

    @Override
    public OrderDetail convertToOrderDetail(Long userId, Long cartId) {
        Cart cart = cartRepository.findOne(cartId);
        checkCart(userId, cart);
        return new OrderDetail(cart);
    }

    @Override
    public void deleteCart(Long userId, Long id) {
        Cart cart = cartRepository.findOne(id);
        checkCart(userId, cart);
        cartRepository.delete(cart);
    }

    @Override
    public void deleteCarts(Long userId, List<Long> cartIds) {
        for (Long cartId: cartIds) {
            cartRepository.delete(cartId);
        }
    }

    private void checkCart(Long userId, Cart cart) {
        if (cart == null)
            throw new NotFoundException("Error.Cart.NOT_EXIST");
        if ( ! cart.getUserId().equals(userId))
            throw new IllegalActionException("Error.Cart.NOT_YOUR_CART");
    }

}
