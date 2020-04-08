package com.winter.service;

import com.winter.model.MerchantInfo;

import java.util.List;

public interface MerchantService {
    List<MerchantInfo> selectNearMerchant(MerchantInfo info);
}
