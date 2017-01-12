package com.qiyewan.common.runner;

import com.qiyewan.core.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lhzbxx on 2016/11/10.
 *
 * 将SimpleProductDto置入Cache中
 * List<Simple1ProductDto> -> Redis
 * List<Simple2ProductDto> -> Redis
 */
@Configuration
public class CacheProducts implements CommandLineRunner {
    @Autowired
    private CacheService cacheService;

    @Override
    public void run(String... strings) throws Exception {
        cacheService.refreshProducts();
    }
}
