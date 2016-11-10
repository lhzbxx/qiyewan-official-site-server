package com.qiyewan.service;

import com.qiyewan.domain.Product;
import com.qiyewan.dto.SimpleProductDto;
import com.qiyewan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lhzbxx on 2016/10/28.
 *
 * 产品
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisTemplate<String, List<SimpleProductDto>> redisTemplate;

    @Override
    public List<SimpleProductDto> getProducts(String regionCode) {
        return null;
    }

    @Override
    public List<SimpleProductDto> getProductsWithClassification(String regionCode, String classificationName) {
        return null;
    }

    @Override
    public Product getProduct(String serialId) {
        return productRepository.findBySerialId(serialId);
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
