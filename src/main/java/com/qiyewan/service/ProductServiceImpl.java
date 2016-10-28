package com.qiyewan.service;

import com.qiyewan.domain.Product;
import com.qiyewan.repository.ProductInfoRepository;
import com.qiyewan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lhzbxx on 2016/10/28.
 *
 * 产品
 */

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return null;
    }

    @Override
    public Product getProduct(Long productId) {
        return null;
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }
}
