package com.qiyewan.core.web.api;

import com.qiyewan.core.domain.Company;
import com.qiyewan.core.service.CompanyService;
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

    @CrossOrigin
    @GetMapping("/company")
    public Company show() {
        Long userId = (Long) request.getAttribute("userId");
        return companyService.show(userId);
    }

    @CrossOrigin
    @PutMapping("/company")
    public Company update(@Validated @RequestBody Company company) {
        Long userId = (Long) request.getAttribute("userId");
        return companyService.save(userId, company);
    }
}
