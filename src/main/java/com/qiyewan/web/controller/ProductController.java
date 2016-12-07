package com.qiyewan.web.controller;

import com.qiyewan.domain.Faq;
import com.qiyewan.domain.Product;
import com.qiyewan.domain.Review;
import com.qiyewan.service.FaqService;
import com.qiyewan.service.ProductService;
import com.qiyewan.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private ReviewService reviewService;

    @CrossOrigin
    @GetMapping("/products/{serialId}")
    public Product show(@PathVariable String serialId) {
        return productService.getProduct(serialId);
    }

    @CrossOrigin
    @GetMapping("/products")
    public List<?> showList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                           @RequestParam String regionCode,
                                           @RequestParam(required = false) String classificationName) {
        if (classificationName != null) {
            return productService.getProductsWithClassification(regionCode, classificationName);
        }
        return productService.getProducts(regionCode);
    }

    @PostMapping("/products")
    public Product add(@Validated @RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/products")
    public Product update(@Validated @RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @CrossOrigin
    @GetMapping("/products/{serialId}/faq")
    public Page<Faq> faq(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                         @PathVariable String serialId) {
        return faqService.getFaqs(serialId, pageable);
    }

    @CrossOrigin
    @GetMapping("/products/{serialId}/reviews")
    public Page<Review> review(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                               @PathVariable String serialId) {
        return reviewService.getReviewsByProductSerialId(serialId, pageable);
    }

}