package com.qiyewan.service;

import com.qiyewan.domain.Product;
import com.qiyewan.dto.SimpleProductDto;

import java.util.List;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 产品
 */

public interface ProductService {

    List<SimpleProductDto> getProducts(String regionCode);

    List<SimpleProductDto> getProductsWithClassification(String regionCode, String classificationName);

    Product getProduct(String serialId);

    Product saveProduct(Product product);

    Product updateProduct(Product product);

}
