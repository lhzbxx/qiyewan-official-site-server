package com.qiyewan.api;

import com.qiyewan.domain.Cart;
import com.qiyewan.dto.ErrorDto;
import com.qiyewan.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 购物车
 */

@RestController
public class CartApi {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    public Page<Cart> show(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Long userId = (Long) request.getAttribute("userId");
        return cartService.getCartsByUser(userId, pageable);
    }

    @RequestMapping(value = "/carts", method = RequestMethod.POST)
    public Cart add(@RequestBody @Validated Cart cart) {
        Long userId = (Long) request.getAttribute("userId");
        return cartService.saveCart(userId, cart);
    }

    @RequestMapping(value = "/carts/{cartId}", method = RequestMethod.DELETE)
    public ErrorDto<?> remove(@PathVariable Long cartId) {
        Long userId = (Long) request.getAttribute("userId");
        cartService.deleteCart(userId, cartId);
        return new ErrorDto<>();
    }

}
