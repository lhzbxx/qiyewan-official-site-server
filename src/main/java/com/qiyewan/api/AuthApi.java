package com.qiyewan.api;

import com.qiyewan.domain.LoginHistory;
import com.qiyewan.service.CompanyService;
import com.qiyewan.service.LoginHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 用户相关
 */

@RestController
public class AuthApi {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginHistoryService loginHistoryService;

    @Autowired
    private CompanyService companyService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/login-history")
    public Page<LoginHistory> showLoginHistory(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Long userId = (Long) request.getAttribute("userId");
        return loginHistoryService.show(userId, pageable);
    }

}
