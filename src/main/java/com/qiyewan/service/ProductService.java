package com.qiyewan.service;

import com.qiyewan.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 产品
 */

public interface ProductService {

    Page<Product> getProductsWithClassification(String classificationName, Pageable pageable);

    Product getProduct(String serialId);

    Product saveProduct(Product product);

    Product updateProduct(Product product);

}
