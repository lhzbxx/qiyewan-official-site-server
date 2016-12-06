package com.qiyewan.service;

import com.qiyewan.domain.Faq;
import com.qiyewan.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by lhzbxx on 2016/10/28.
 *
 * 常见问题
 */
@Service
public class FaqServiceImpl implements FaqService {
    @Autowired
    private FaqRepository faqRepository;

    @Override
    public Page<Faq> getFaqs(String serialId, Pageable pageable) {
        return faqRepository.findBySerialId(serialId, pageable);
    }
}
