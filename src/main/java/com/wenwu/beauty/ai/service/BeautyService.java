package com.wenwu.beauty.ai.service;

import com.wenwu.beauty.ai.model.faceage.ParamsModel;
import com.wenwu.beauty.ai.model.facedecoration.DecorationParamModel;

public interface BeautyService {
    Object checkImage(ParamsModel paramsModel) throws Throwable;

    Object decorationImage(DecorationParamModel paramModel) throws Throwable;
}
