package com.qiyewan.repository;

import com.qiyewan.domain.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 短信
 */
@Repository
public interface SmsRepository extends JpaRepository<Sms, Long> {
}
