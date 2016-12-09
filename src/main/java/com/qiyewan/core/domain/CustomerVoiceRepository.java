package com.qiyewan.core.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 客户之声
 */
@Repository
public interface CustomerVoiceRepository extends JpaRepository<CustomerVoice, Long> {
}
