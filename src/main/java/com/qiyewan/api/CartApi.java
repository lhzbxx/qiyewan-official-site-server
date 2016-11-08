package com.qiyewan.api;

import com.qiyewan.domain.Cart;
import com.qiyewan.dto.ErrorDto;
import com.qiyewan.exceptions.InvalidParamException;
import com.qiyewan.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    @CrossOrigin
    @GetMapping("/carts")
    public Page<Cart> show(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Long userId = (Long) request.getAttribute("userId");
        return cartService.getCartsByUser(userId, pageable);
    }

    @CrossOrigin
    @PostMapping("/carts")
    public Cart add(@RequestBody @Validated Cart cart) {
        Long userId = (Long) request.getAttribute("userId");
        return cartService.saveCart(userId, cart);
    }

    @CrossOrigin
    @PatchMapping("/carts")
    public Cart update(@RequestBody @Validated Cart cart) {
        Long userId = (Long) request.getAttribute("userId");
        return cartService.updateCart(userId, cart);
    }

    @CrossOrigin
    @DeleteMapping("/carts/{id}")
    public ErrorDto<?> remove(@PathVariable String id) {
        Long userId = (Long) request.getAttribute("userId");
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            cartService.deleteCart(userId, Long.parseLong(new String(decoder.decodeBuffer(id))));
        } catch (IOException e) {
            throw new InvalidParamException("Error.Cart.INVALID_ID");
        }
        return new ErrorDto<>();
    }

}
