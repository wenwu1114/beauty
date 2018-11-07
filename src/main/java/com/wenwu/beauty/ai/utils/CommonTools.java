package com.wenwu.beauty.ai.utils;

import com.wenwu.beauty.ai.consts.SystemVariable;
import com.wenwu.beauty.ai.model.ParamsModel;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 自定义工具包
 */
public class CommonTools {
    /**
     * 对参数签名(将<key, value>请求参数对按key进行字典升序排序)
     *
     * 签名算法
     * 1. 计算步骤
     * 用于计算签名的参数在不同接口之间会有差异，但算法过程固定如下4个步骤。
     *
     * 将<key, value>请求参数对按key进行字典升序排序，得到有序的参数对列表N
     * 将列表N中的参数对按URL键值对的格式拼接成字符串，得到字符串T（如：key1=value1&key2=value2），URL键值拼接过程value部分需要URL编码，URL编码算法用大写字母，例如%E8，而不是小写%e8
     * 将应用密钥以app_key为键名，组成URL键值拼接到字符串T末尾，得到字符串S（如：key1=value1&key2=value2&app_key=密钥)
     * 对字符串S进行MD5运算，将得到的MD5值所有字符转换成大写，得到接口请求签名
     * 2. 注意事项
     * 不同接口要求的参数对不一样，计算签名使用的参数对也不一样
     * 参数名区分大小写，参数值为空不参与签名
     * URL键值拼接过程value部分需要URL编码
     * 签名有效期5分钟，需要请求接口时刻实时计算签名信息
     * 更多注意事项，请查看常见问题
     *
     * @param paramsModel
     * @return sign
     */
    public static String getReqSign(ParamsModel paramsModel) throws Throwable {
        String s;
        // 字段比较少，手动排序了，按首字母排序
        String app_idURLCode = URLEncoder.encode(paramsModel.getApp_id()+"","UTF-8").toUpperCase();
        String imageURLCode = URLEncoder.encode(paramsModel.getImage()+"","UTF-8").toUpperCase();
        String nonce_strURLCode = URLEncoder.encode(paramsModel.getNonce_str()+"","UTF-8").toUpperCase();
        String time_stampURLCode = URLEncoder.encode(paramsModel.getTime_stamp()+"","UTF-8").toUpperCase();
        s="app_id="+app_idURLCode+"&image="+imageURLCode+"&nonce_str="+nonce_strURLCode+"$time_stamp="+time_stampURLCode;
        s=s+"&app_key="+ SystemVariable.APP_KEY;
        String sign = DigestUtils.md5DigestAsHex(s.getBytes()).toUpperCase();
        return sign;
    }

    /**
     * 图片转成base64编码
     * @param file
     * @return
     */
    public static String Base64Img(MultipartFile file) throws Throwable {
        byte[] bytes = file.getBytes();
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    /**
     * 获取秒级时间戳
     * @return
     */
    public static int getMillTime(Date date){
        if (date == null)
            return 0;
        String timestamp = String.valueOf(date.getTime()/1000);
        return Integer.valueOf(timestamp);

    }
}
