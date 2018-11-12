package com.wenwu.beauty.ai.service.impl;


import com.wenwu.beauty.ai.consts.ContentType;
import com.wenwu.beauty.ai.consts.faceage.SystemVariable;
import com.wenwu.beauty.ai.httpsdk.HttpReqUtils;
import com.wenwu.beauty.ai.model.faceage.ParamsModel;
import com.wenwu.beauty.ai.service.BeautyService;
import com.wenwu.beauty.ai.utils.CommonTools;
import org.springframework.stereotype.Service;

@Service
public class BeautyServiceImpl implements BeautyService {
    @Override
    public String checkImage(ParamsModel paramsModel) throws Throwable {
        String sign = CommonTools.getReqSign(paramsModel,SystemVariable.APP_KEY);
        paramsModel.setSign(sign);
        HttpReqUtils httpReqUtils =new HttpReqUtils();
        String result = httpReqUtils.sendHttpPostReq(SystemVariable.URL, paramsModel, ContentType.FORM_URLENCODED);
        return result;
    }
}
