package com.qiyewan.core.service;

import com.qiyewan.core.domain.Cart;
import com.qiyewan.core.domain.OrderDetail;
import com.qiyewan.core.domain.Product;
import com.qiyewan.common.exceptions.InvalidRequestException;
import com.qiyewan.common.exceptions.InvalidParamException;
import com.qiyewan.common.exceptions.NotFoundException;
import com.qiyewan.core.domain.CartRepository;
import com.qiyewan.core.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    @Transactional
    public Cart saveCart(Long userId, Cart cart, boolean isOverride) {
        cart.setUserId(userId);
        Product product = productRepository.findBySerialId(cart.getSerialId());
        if (product == null)
            throw new NotFoundException("该产品不存在。");
        if (cart.getPremium().signum() == -1) {
            cart.setPremium(BigDecimal.ZERO);
        }
        if (isOverride) {
            Cart c = cartRepository.findFirstByUserIdAndSerialId(userId, product.getSerialId());
            if (c != null && c.equal(cart)) {
                c.setAmount(c.getAmount() + cart.getAmount());
                c.setUpdateAt(new Date());
                cartRepository.saveAndFlush(c);
                return c;
            }
        }
        cart.setProduct(product);
        cartRepository.saveAndFlush(cart);
        return cart;
    }

    @Override
    @Transactional
    public Cart updateCart(Long userId, Cart cart) {
        if (cart.getId() == null)
            throw new InvalidParamException("无效的购物车ID。");
        Cart c = cartRepository.findOne(cart.getId());
        checkCart(userId, c);
        cartRepository.save(c.copy(cart));
        return cart;
    }

    @Override
    @Transactional
    public OrderDetail convertToOrderDetail(Long userId, Long cartId) {
        Cart cart = cartRepository.findOne(cartId);
        checkCart(userId, cart);
        return new OrderDetail(cart);
    }

    @Override
    @Transactional
    public void deleteCart(Long userId, Long id) {
        Cart cart = cartRepository.findOne(id);
        checkCart(userId, cart);
        cartRepository.delete(cart);
    }

    @Override
    @Transactional
    public void deleteCarts(Long userId, List<Long> cartIds) {
        List<Cart> carts = new ArrayList<>();
        for (Long cartId : cartIds) {
            Cart cart = cartRepository.findOne(cartId);
            checkCart(userId, cart);
            carts.add(cart);
        }
        cartRepository.delete(carts);
    }

    private void checkCart(Long userId, Cart cart) {
        if (cart == null)
            throw new NotFoundException("购物车不存在。");
        if (!cart.getUserId().equals(userId))
            throw new InvalidRequestException("无法更改别人的购物车。");
    }
}
