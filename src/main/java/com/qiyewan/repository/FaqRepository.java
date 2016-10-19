package com.qiyewan.repository;

import com.qiyewan.domain.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 常见问题
 */

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
}
