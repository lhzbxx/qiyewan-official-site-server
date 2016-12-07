package com.qiyewan.service;

import com.qiyewan.domain.Brand;
import com.qiyewan.dto.BrandDto;
import com.qiyewan.domain.BrandRepository;
import com.qiyewan.utils.BrandUtil;
import com.qiyewan.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 手机验证码-管理
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand fuzzyQuery(String keyword, int page) {
        Brand brand = brandRepository.findFirstByKeywordAndPage(keyword, page);
        if (brand == null) {
            BrandDto brandDto;
            try {
                brandDto = BrandUtil.fuzzyQuery(keyword, page);
                if (Integer.parseInt(brandDto.getRemainCount()) < 10) {
                    SmsUtil.send("15121085325", "商标查询-接口-剩余次数已经不足10次。");
                }
                if (brandDto.getRet() == 0) {
                    Brand b = new Brand(keyword, page, brandDto);
                    brandRepository.save(b);
                    return b;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            brand.setCount(brand.getCount() + 1);
            brandRepository.save(brand);
            return brand;
        }
    }
}
