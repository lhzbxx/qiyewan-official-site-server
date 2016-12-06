package com.qiyewan.service;

import com.qiyewan.domain.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 常见问题
 */
public interface FaqService {
    Page<Faq> getFaqs(String serialId, Pageable pageable);
}
