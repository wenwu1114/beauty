package com.wenwu.beauty.ai.controller;

import ch.qos.logback.core.util.FileUtil;
import com.wenwu.beauty.ai.consts.SystemVariable;
import com.wenwu.beauty.ai.model.ParamsModel;
import com.wenwu.beauty.ai.model.Result;
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
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 *
 */
@RestController
public class BeautyController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BeautyService beautyService;
    @PostMapping("/")
    public Object checkImage(@RequestParam("image_file")MultipartFile file){
        logger.info("开始上传图片");
        Result result = new Result();
        String fileName = file.getOriginalFilename();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        Date date = new Date();
        String time = sdf.format(date);
        fileName = time+fileName;
        long size = file.getSize();
        String filePath = "/root/images";
        File dest = new File(filePath+"/"+fileName);
        try{
        String image = CommonTools.Base64Img(file);
            result.setImg(image);
            if (!dest.getParentFile().exists())
                dest.getParentFile().mkdir();
            file.transferTo(dest);
            ParamsModel paramsModel = new ParamsModel();
            paramsModel.setApp_id(SystemVariable.APPID);
            paramsModel.setImage(image);
            String nonce_str= UUID.randomUUID().toString().replace("-","0");
            paramsModel.setNonce_str(nonce_str);
            paramsModel.setTime_stamp(CommonTools.getMillTime(date));
            return beautyService.checkImage(paramsModel);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return result;
        }

    }
}
