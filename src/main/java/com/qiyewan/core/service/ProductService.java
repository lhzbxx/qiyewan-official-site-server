package com.qiyewan.core.service;

import com.qiyewan.core.domain.Product;
import com.qiyewan.core.other.dto.Simple1ProductDto;
import com.qiyewan.core.other.dto.Simple2ProductDto;

import java.util.List;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 产品
 */
public interface ProductService {
    List<Simple1ProductDto> getProducts(String regionCode);

    List<Simple2ProductDto> getProductsWithClassification(String regionCode, String classificationName);

    Product getProduct(String serialId);

    Product saveProduct(Product product);

    Product updateProduct(Product product);
}
