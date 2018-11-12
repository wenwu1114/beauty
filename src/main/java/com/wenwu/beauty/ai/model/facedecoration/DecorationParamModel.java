package com.wenwu.beauty.ai.model.facedecoration;

/**
 * 人脸变妆参数模型
 */
public class DecorationParamModel {
    private int app_id;
    /**
     * 请求时间戳
     */
    private int time_stamp;
    /**
     * 随机字符串非空上限32字节
     */
    private String nonce_str;
    /**
     * 图片的base64编码(原图大小上限500k)
     */
    private String image;

    /**
     * 变妆编码
     */
    private int decorationCode;
    /**
     * 签名信息
     */
    private String sign;

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public int getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(int time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getDecorationCode() {
        return decorationCode;
    }

    public void setDecorationCode(int decorationCode) {
        this.decorationCode = decorationCode;
    }

    public String getPostData(){
        StringBuffer sb=new StringBuffer();
        sb.append("app_id=").append(getApp_id());
        sb.append("&time_stamp=").append(getTime_stamp());
        sb.append("&nonce_str=").append(getNonce_str());
        sb.append("&decoration=").append(getDecorationCode());
        sb.append("&image=").append(getImage());
        sb.append("&sign=").append(getSign());
        return sb.toString();
    }
}