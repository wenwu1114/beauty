package com.wenwu.beauty.ai.service;

import com.wenwu.beauty.ai.model.ParamsModel;

public interface BeautyService {
    Object checkImage(ParamsModel paramsModel) throws Throwable;
}
