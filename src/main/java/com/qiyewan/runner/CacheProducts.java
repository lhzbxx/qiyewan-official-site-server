package com.qiyewan.runner;

import com.qiyewan.config.Constants;
import com.qiyewan.dto.Simple1ProductDto;
import com.qiyewan.dto.Simple2ProductDto;
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
 * List<Simple1ProductDto> -> Redis
 */

@Configuration
public class CacheProducts implements CommandLineRunner {

    @Autowired
    private RedisTemplate<String, List<Simple1ProductDto>> redis1Template;

    @Autowired
    private RedisTemplate<String, List<Simple2ProductDto>> redis2Template;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... strings) throws Exception {
        for (RegionCode code: RegionCode.values()) {
            redis1Template.opsForValue().set(Constants.REDIS_PRODUCTS_CITY(code.getCode()),
                    productRepository.findAllByRegionCode(code.getCode())
                            .stream().map(Simple1ProductDto::new).collect(Collectors.toList()));
            for (String cName: productRepository.findDistinctClassificationName()) {
                redis2Template.opsForValue().set(Constants.REDIS_PRODUCTS_CITY_CLASSIFICATION(code.getCode(), cName),
                        productRepository.findAllByClassificationNameAndRegionCode(cName, code.getCode())
                                .stream().map(Simple2ProductDto::new).collect(Collectors.toList()));
            }
        }
    }
}
