package com.qiyewan.web.api;

import com.qiyewan.domain.Cart;
import com.qiyewan.dto.ResultDto;
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
    public Cart add(@RequestBody @Validated Cart cart,
                    @RequestParam(defaultValue = "true", required = false) boolean isOverride) {
        Long userId = (Long) request.getAttribute("userId");
        return cartService.saveCart(userId, cart, isOverride);
    }

    @CrossOrigin
    @PatchMapping("/carts")
    public Cart update(@RequestBody @Validated Cart cart) {
        if (cart.getId() == null) {
            throw new InvalidParamException("购物车ID不能为空。");
        }
        Long userId = (Long) request.getAttribute("userId");
        return cartService.updateCart(userId, cart);
    }

    @CrossOrigin
    @DeleteMapping("/carts/{id}")
    public ResultDto remove(@PathVariable String id) {
        Long userId = (Long) request.getAttribute("userId");
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            Long cartId = Long.parseLong(new String(decoder.decodeBuffer(id)));
            cartService.deleteCart(userId, cartId);
        } catch (IOException e) {
            throw new InvalidParamException("非法的购物车ID。");
        }
        return new ResultDto();
    }
}
