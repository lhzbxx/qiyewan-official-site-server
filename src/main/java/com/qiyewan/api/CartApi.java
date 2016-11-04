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

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/carts")
    public Page<Cart> show(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Long userId = (Long) request.getAttribute("userId");
        return cartService.getCartsByUser(userId, pageable);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/carts")
    public Cart add(@RequestBody @Validated Cart cart) {
        Long userId = (Long) request.getAttribute("userId");
        return cartService.saveCart(userId, cart);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PatchMapping("/carts")
    public Cart update(@RequestBody @Validated Cart cart) {
        Long userId = (Long) request.getAttribute("userId");
        return cartService.updateCart(userId, cart);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/carts/{cartId}")
    public ErrorDto<?> remove(@PathVariable Long cartId) {
        Long userId = (Long) request.getAttribute("userId");
        cartService.deleteCart(userId, cartId);
        return new ErrorDto<>();
    }

}
