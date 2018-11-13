package com.wenwu.beauty.ai.service.impl;


import com.wenwu.beauty.ai.consts.ContentType;
import com.wenwu.beauty.ai.consts.faceage.SystemVariable;
import com.wenwu.beauty.ai.consts.facedecoration.DecorationVariable;
import com.wenwu.beauty.ai.httpsdk.HttpReqUtils;
import com.wenwu.beauty.ai.model.faceage.ParamsModel;
import com.wenwu.beauty.ai.model.facedecoration.DecorationParamModel;
import com.wenwu.beauty.ai.service.BeautyService;
import com.wenwu.beauty.ai.utils.CommonTools;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BeautyServiceImpl implements BeautyService {
    @Override
    public String checkImage(ParamsModel paramsModel) throws Throwable {
        Map<String, Object> paramsMap  = CommonTools.convertEntityToTreeMap(paramsModel);
        String sign = CommonTools.getReqSign(paramsMap,SystemVariable.APP_KEY);
        paramsMap.put("sign",sign);
        HttpReqUtils httpReqUtils =new HttpReqUtils();
        String result = httpReqUtils.sendHttpPostReq(SystemVariable.URL, paramsMap, ContentType.FORM_URLENCODED);
        return result;
    }

    @Override
    public Object decorationImage(DecorationParamModel paramsModel) throws Throwable {
        Map<String, Object> paramsMap  = CommonTools.convertEntityToTreeMap(paramsModel);
        String sign = CommonTools.getReqSign(paramsMap, DecorationVariable.APP_KEY);
        paramsMap.put("sign",sign);
        HttpReqUtils httpReqUtils = new HttpReqUtils();
        String result = httpReqUtils.sendHttpPostReq(DecorationVariable.URL,paramsMap,ContentType.FORM_URLENCODED);
        return result;
    }
}
