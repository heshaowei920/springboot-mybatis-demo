package com.winter.mapper;


import com.winter.model.MerchantInfo;

import java.util.List;


public interface MerchantInfoMapper{

    List<MerchantInfo> selectNearMerchant(MerchantInfo req);

}
