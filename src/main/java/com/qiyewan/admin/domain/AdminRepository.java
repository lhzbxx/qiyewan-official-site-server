package com.qiyewan.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 管理员
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
