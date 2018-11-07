package com.wenwu.beauty.ai.service.impl;


import com.wenwu.beauty.ai.consts.SystemVariable;
import com.wenwu.beauty.ai.model.ParamsModel;
import com.wenwu.beauty.ai.service.BeautyService;
import com.wenwu.beauty.ai.utils.CommonTools;
import com.wenwu.beauty.ai.utils.HttpTools;
import org.springframework.stereotype.Service;

@Service
public class BeautyServiceImpl implements BeautyService {
    @Override
    public String checkImage(ParamsModel paramsModel) throws Throwable {
        String sign = CommonTools.getReqSign(paramsModel);
        paramsModel.setSign(sign);
        HttpTools httpTools = new HttpTools();
        String result = httpTools.getResponsentity(SystemVariable.URL, paramsModel);
        return result;
    }
}
