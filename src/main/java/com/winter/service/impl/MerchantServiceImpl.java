package com.winter.service.impl;

import com.winter.mapper.MerchantMapper;
import com.winter.model.MerchantInfo;
import com.winter.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：heshaowei
 * @date ：Created in 2020/2/18 14:28
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    public List<MerchantInfo> selectNearMerchant(MerchantInfo info) {
        List<MerchantInfo> list = merchantMapper.selectNearMerchant(info);
        return list;
    }
}
