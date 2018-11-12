package com.wenwu.beauty.ai.controller;

import com.wenwu.beauty.ai.consts.faceage.SystemVariable;
import com.wenwu.beauty.ai.model.faceage.ParamsModel;
import com.wenwu.beauty.ai.model.faceage.Result;
import com.wenwu.beauty.ai.service.BeautyService;
import com.wenwu.beauty.ai.utils.CommonTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.UUID;

/**
 *
 */
@RestController
public class BeautyController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BeautyService beautyService;
    @PostMapping("/fileupload")
    public Object checkImage(@RequestParam("image_file")MultipartFile file){
        logger.info("开始上传图片");
        Result result = new Result();
        Date date = new Date();
        try{
            String image = CommonTools.Base64Img(file);
            CommonTools.saveImg(file);
            ParamsModel paramsModel = new ParamsModel();
            paramsModel.setApp_id(SystemVariable.APPID);
            paramsModel.setImage(image);
            paramsModel.setNonce_str(CommonTools.getRandStr());
            paramsModel.setTime_stamp(CommonTools.getMillTime(date));
            return beautyService.checkImage(paramsModel);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return result;
        }

    }


}
