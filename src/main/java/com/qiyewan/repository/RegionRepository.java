package com.qiyewan.repository;

import com.qiyewan.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 区域
 */

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
