package com.qiyewan.core.web.api;

import com.qiyewan.core.domain.Brand;
import com.qiyewan.core.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lhzbxx on 2016/11/25.
 *
 * 商标查询
 */
@RestController
public class BrandApi {
    @Autowired
    private BrandService brandService;

    @CrossOrigin
    @GetMapping("/brand")
    public Brand fuzzyQuery(@RequestParam String keyword,
                            @RequestParam int page) {
        return brandService.fuzzyQuery(keyword, page);
    }
}
