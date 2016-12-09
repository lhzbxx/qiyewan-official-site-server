package com.qiyewan.core.service;

import com.qiyewan.common.configs.Constants;
import com.qiyewan.core.domain.Product;
import com.qiyewan.core.dto.Simple1ProductDto;
import com.qiyewan.core.dto.Simple2ProductDto;
import com.qiyewan.core.domain.ProductRepository;
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
    private RedisTemplate<String, List<Simple1ProductDto>> redis1Template;
    @Autowired
    private RedisTemplate<String, List<Simple2ProductDto>> redis2Template;

    @Override
    public List<Simple1ProductDto> getProducts(String regionCode) {
        return redis1Template.opsForValue().get(Constants.REDIS_PREFIX_PRODUCTS + regionCode);
    }

    @Override
    public List<Simple2ProductDto> getProductsWithClassification(String regionCode, String classificationName) {
        return redis2Template.opsForValue().get(Constants.REDIS_PREFIX_PRODUCTS + regionCode + ":" + classificationName);
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
