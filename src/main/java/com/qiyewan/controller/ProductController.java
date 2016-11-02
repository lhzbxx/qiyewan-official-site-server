package com.qiyewan.controller;

import com.qiyewan.domain.Faq;
import com.qiyewan.domain.Product;
import com.qiyewan.dto.ErrorDto;
import com.qiyewan.service.FaqService;
import com.qiyewan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 产品
 */

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FaqService faqService;

    @GetMapping("/products/{serialId}")
    public Product show(@PathVariable String serialId) {
        return productService.getProduct(serialId);
    }

    @GetMapping("/products")
    public Page<Product> showList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                  @NotNull @RequestParam String classificationName) {
        return productService.getProductsWithClassification(classificationName, pageable);
    }

    @PostMapping("/products")
    public ErrorDto<?> add(@Validated @RequestBody Product product) {
        productService.saveProduct(product);
        return new ErrorDto<>();
    }

    @PutMapping("/products")
    public ErrorDto<?> update(@Validated @RequestBody Product product) {
        productService.updateProduct(product);
        return new ErrorDto<>();
    }

    @GetMapping("/products/{serialId}/faq")
    public Page<Faq> faq(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                         @PathVariable String serialId) {
        return faqService.getFaqs(serialId, pageable);
    }

}
