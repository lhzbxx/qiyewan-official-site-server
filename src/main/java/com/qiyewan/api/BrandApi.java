package com.qiyewan.api;

import com.qiyewan.domain.Brand;
import com.qiyewan.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lhzbxx on 2016/11/25.
 * <p>
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
