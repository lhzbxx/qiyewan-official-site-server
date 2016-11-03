package com.qiyewan.service;

import com.qiyewan.domain.Product;
import com.qiyewan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by lhzbxx on 2016/10/28.
 *
 * 产品
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProduct(String serialId) {
        return productRepository.findBySerialId(serialId);
    }

    @Override
    public Page<Product> getProductsWithClassification(String classificationName, Pageable pageable) {
        return productRepository.findByClassificationName(classificationName, pageable);
    }

    @Override
    public Product saveProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        productRepository.save(product);
        return product;
    }

}
