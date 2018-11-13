package com.wenwu.beauty.ai.controller;

import com.wenwu.beauty.ai.consts.faceage.SystemVariable;
import com.wenwu.beauty.ai.consts.facedecoration.DecorationVariable;
import com.wenwu.beauty.ai.model.faceage.Result;
import com.wenwu.beauty.ai.model.facedecoration.DecorationParamModel;
import com.wenwu.beauty.ai.service.BeautyService;
import com.wenwu.beauty.ai.utils.CommonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 人年变妆
 */
@RestController
@RequestMapping("/api")
public class DecorationController {
    @Autowired
    private BeautyService beautyService;
    @PostMapping("/decoration")
    public Object decoration(@RequestParam("image_file")MultipartFile file,int decoration){
        Result result = new Result();
        Date date = new Date();
        try{
            String image = CommonTools.Base64Img(file);
            CommonTools.saveImg(file);
            DecorationParamModel paramsModel = new DecorationParamModel();
            paramsModel.setApp_id(DecorationVariable.APPID);
            paramsModel.setImage(image);
            paramsModel.setNonce_str(CommonTools.getRandStr());
            paramsModel.setTime_stamp(CommonTools.getMillTime(date));
            paramsModel.setDecoration(decoration);
            return beautyService.decorationImage(paramsModel);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return result;
        }
    }
}
