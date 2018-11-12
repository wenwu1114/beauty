package com.wenwu.beauty.ai.model.faceage;

/**
 * 返回结果模型
 */
public class Result {
    /**
     * 0成功，非0失败
     */
    private int ret=-1;
    private String msg="检测失败";
    private Object data="null";
    /**
     * 图片的base64编码数据
     */
    private String img;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
