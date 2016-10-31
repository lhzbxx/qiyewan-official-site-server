package com.qiyewan.controller;

import com.qiyewan.domain.Product;
import com.qiyewan.dto.ErrorDto;
import com.qiyewan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 产品
 */

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    public Product login(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public Page<Product> login(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.getProducts(pageable);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ErrorDto<?> add(@Validated @RequestBody Product product) {
        productService.saveProduct(product);
        return new ErrorDto<>();
    }

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public ErrorDto<?> update(@Validated @RequestBody Product product) {
        productService.updateProduct(product);
        return new ErrorDto<>();
    }

}
