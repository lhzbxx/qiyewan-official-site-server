package com.qiyewan.service;

import com.qiyewan.domain.Brand;

/**
 * Created by lhzbxx on 2016/11/25.
 *
 * 商标查询-缓存。
 */

public interface BrandService {
    Brand fuzzyQuery(String keyword, int page);
}
