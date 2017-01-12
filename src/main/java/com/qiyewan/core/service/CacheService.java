package com.qiyewan.core.service;

import com.qiyewan.common.configs.Constants;
import com.qiyewan.common.enums.CityCode;
import com.qiyewan.common.enums.Classification;
import com.qiyewan.core.domain.ProductRepository;
import com.qiyewan.core.other.dto.Simple1ProductDto;
import com.qiyewan.core.other.dto.Simple2ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 缓存-服务
 */
@Component
public class CacheService {
    @Autowired
    private RedisTemplate<String, List<Simple1ProductDto>> redis1Template;
    @Autowired
    private RedisTemplate<String, List<Simple2ProductDto>> redis2Template;
    @Autowired
    private ProductRepository productRepository;

    public void refreshProducts() {
        for (CityCode code : CityCode.values()) {
            redis1Template.opsForValue().set(Constants.REDIS_PRODUCTS_CITY(code.getCode()),
                    productRepository.findAllByRegionCode(code.getCode())
                            .stream().map(Simple1ProductDto::new).collect(Collectors.toList()));
            for (String cName : productRepository.findDistinctClassificationName()) {
                redis2Template.opsForValue().set(Constants.REDIS_PRODUCTS_CITY_CLASSIFICATION(code.getCode(), cName),
                        productRepository.findAllByClassificationNameAndRegionCode(cName, code.getCode())
                                .stream().map(Simple2ProductDto::new).collect(Collectors.toList()));
            }
            for (Classification c : Classification.values()) {
                redis2Template.opsForValue().set(Constants.REDIS_PRODUCTS_CITY_CLASSIFICATION(code.getCode(), c.name()),
                        productRepository.findAllByClassificationCodeAndRegionCode(c.name(), code.getCode())
                                .stream().map(Simple2ProductDto::new).collect(Collectors.toList()));
            }
        }
    }
}
