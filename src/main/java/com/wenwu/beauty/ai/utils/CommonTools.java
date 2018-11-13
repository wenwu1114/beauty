package com.wenwu.beauty.ai.utils;

import com.wenwu.beauty.ai.consts.faceage.SystemVariable;
import com.wenwu.beauty.ai.model.faceage.ParamsModel;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

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
     * @param
     * @return sign
     */
    public static String getReqSign(Map<String,Object> paramsMap,String app_key) throws Throwable {
        StringBuffer sb=new StringBuffer();
       // Map<String, Object> paramsMap = convertEntityToTreeMap(paramsModel);
        for( Map.Entry<String,Object> entry:paramsMap.entrySet()){
            Object value = entry.getValue();
            if (value!=null)
                sb.append(entry.getKey()).append("=").append(PhpURLEncoder(entry.getValue().toString()+"","UTF-8")).append("&");
        }
        sb.append("app_key=").append(app_key);
        System.out.println("拼接字符串"+sb.toString());
        String sign = DigestUtils.md5DigestAsHex(sb.toString().getBytes("UTF-8")).toUpperCase();
        System.out.println(sign);
        System.out.println(sign.getBytes("UTF-8").length);
        return sign;
    }

    /**
     * 图片转成base64编码
     * @param file
     * @return
     */
    public static String Base64Img(MultipartFile file) throws Throwable {
        byte[] bytes = file.getBytes();
        String string = Base64.getEncoder().encodeToString(bytes);
        System.out.println("图片的base64编码："+string);
        return  string;
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

    /**
     * 获取提交的数据
     * @param paramsMap
     * @return
     */
    public static List<NameValuePair> getPostData(Map<String,Object> paramsMap){
        List<NameValuePair> pairs = new ArrayList<>();
        if (paramsMap==null)
            return null;
        Set<Map.Entry<String, Object>> entrySet = paramsMap.entrySet();

        for(Map.Entry<String,Object> entry:entrySet){
            pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()+""));
        }
        return pairs;
    }

    /**
     * 标准url编码
     * @param s
     * @param charSet
     * @return
     * @throws Throwable
     */
    public static String PhpURLEncoder(String s,String charSet) throws Throwable{
        String encode = URLEncoder.encode(s, charSet);
        return  encode.replace("*","%2A");
    }

    /**
     * 实体属性转为treeMap对象
     * @param obj
     * @return
     */
    public static Map<String,Object> convertEntityToTreeMap(Object obj) throws  Throwable{
        Map<String,Object> map=new TreeMap<>();
        if (obj==null)
            return null;
        Class<?> objClass = obj.getClass();
        if (objClass!=null){
            Field[] fields = objClass.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field f=objClass.getDeclaredField(fields[i].getName());
                f.setAccessible(true);
                Object o = f.get(obj);
                map.put(fields[i].getName(),o);
            }
        }
        return map;
    }

    /**
     * 保存图片
     * @param multipartFile
     */
    public static void saveImg(MultipartFile multipartFile) throws Throwable{
        Properties properties = System.getProperties();
        String osname = properties.getProperty("os.name");
        String path;
        if ("Linux".equals(osname)){
            path = SystemVariable.PHOTO_PATH_LINUX;
        }else {
            path = SystemVariable.PHOTO_PATH_WINDOWS;
        }
        File targetFile = new File(path);
        if (!targetFile.exists()){
            targetFile.mkdir();
        }
        byte[] bytes = multipartFile.getBytes();
        Date date=new Date();
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        FileOutputStream outputStream = new FileOutputStream(path+time+multipartFile.getOriginalFilename());
        outputStream.write(bytes);
        outputStream.flush();
    }

    public static String getRandStr(){
        return UUID.randomUUID().toString().substring(0,30).replace("-","0");
    }
}
