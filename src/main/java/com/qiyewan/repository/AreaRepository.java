package com.qiyewan.repository;

import com.qiyewan.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 区域
 */

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
}
