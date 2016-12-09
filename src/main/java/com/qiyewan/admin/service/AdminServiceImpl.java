package com.qiyewan.admin.service;

import com.qiyewan.admin.domain.Admin;
import com.qiyewan.admin.domain.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by lhzbxx on 2016/12/9.
 *
 * 管理员
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Page<Admin> getAdmins(Long adminId, Pageable pageable) {
        return null;
    }

    @Override
    public Admin getAdmin(Long adminId) {
        return null;
    }

    @Override
    public Admin create(Long adminId, Admin admin) {
        return null;
    }

    @Override
    public Admin update(Long adminId, Admin admin) {
        return null;
    }

    @Override
    public Admin delete(Long adminId, Admin admin) {
        return null;
    }
}
