package com.winter.controller;

import com.winter.model.MerchantInfo;
import com.winter.service.MerchantService;
import com.winter.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：heshaowei
 * @date ：Created in 2020/2/18 14:30
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping(value = "/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;


    @RequestMapping(value = "/nearMerchant")
    public Result getNearMerchant(String la, String lon) {
        Result result = new Result();
        MerchantInfo info = new MerchantInfo();
        info.setLatitude(la);
        info.setLongitude(lon);
        List<MerchantInfo> list = merchantService.selectNearMerchant(info);
        return result.success(list);
    }

}
