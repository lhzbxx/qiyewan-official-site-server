package com.qiyewan.api;

import com.qiyewan.domain.Company;
import com.qiyewan.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 公司/企业
 */

@RestController
public class CompanyApi {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CompanyService companyService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/company")
    public Company show() {
        Long userId = (Long) request.getAttribute("userId");
        return companyService.show(userId);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/company")
    public Company update(@Validated @RequestBody Company company) {
        Long userId = (Long) request.getAttribute("userId");
        return companyService.save(userId, company);
    }

}
