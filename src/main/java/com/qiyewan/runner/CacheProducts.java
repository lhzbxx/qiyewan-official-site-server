package com.qiyewan.runner;

import com.qiyewan.config.Constants;
import com.qiyewan.domain.Product;
import com.qiyewan.dto.SimpleProductDto;
import com.qiyewan.enums.RegionCode;
import com.qiyewan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lhzbxx on 2016/11/10.
 *
 * 将SimpleProductDto置入Cache中
 * List<SimpleProductDto> -> Redis
 */

@Configuration
public class CacheProducts implements CommandLineRunner {

    @Autowired
    private RedisTemplate<String, List<SimpleProductDto>> redisTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... strings) throws Exception {
        for (RegionCode code: RegionCode.values()) {
            redisTemplate.opsForValue().set(Constants.REDIS_PRFIX_PRODUCTS + code,
                    productRepository.findAllByRegionCode(code.getCode())
                            .stream().map(SimpleProductDto::new).collect(Collectors.toList()));
            for (String cName: productRepository.findDistinctClassificationName()) {
                redisTemplate.opsForValue().set(Constants.REDIS_PRFIX_PRODUCTS + code + ":" + cName,
                        productRepository.findAllByClassificationNameAndRegionCode(cName, code.getCode())
                                .stream().map(SimpleProductDto::new).collect(Collectors.toList()));
            }
        }
    }
}
