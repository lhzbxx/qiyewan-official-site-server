package com.qiyewan.admin.service;

import com.qiyewan.admin.domain.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lhzbxx on 2016/12/9.
 *
 * 管理员
 */
public interface AdminService {
    Page<Admin> getAdmins(Long adminId, Pageable pageable);

    Admin getAdmin(Long adminId);
    
    Admin create(Long adminId, Admin admin);

    Admin update(Long adminId, Admin admin);

    Admin delete(Long adminId, Admin admin);
}
