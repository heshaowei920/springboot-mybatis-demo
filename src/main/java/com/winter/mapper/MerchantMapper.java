package com.winter.mapper;


import com.winter.model.MerchantInfo;

import java.util.List;


public interface MerchantMapper {

    List<MerchantInfo> selectNearMerchant(MerchantInfo info);

}
