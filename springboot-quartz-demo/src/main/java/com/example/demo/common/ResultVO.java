package com.example.demo.common;

/**
 * @Author：kkorkk.
 * @Date：2018/10/11 18:35
 * @Description：controller层请求调用返回结果类
 */
public class ResultVO {

    /**
     * 结果状态码
     * */
    private String retCode;

    /**
     * 数据
     * */
    private Object data;

    /**
     * 结果消息
     * */
    private String retMsg;

    public static ResultVO SUCCESS(Object data){
        return new ResultVO("0","SUCCESS",data);
    }

    public static ResultVO ERROR(String retCode, String retMsg){
        return new ResultVO(retCode,retMsg,null);
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public ResultVO(String retCode, String retMsg, Object data) {
        this.retCode = retCode;
        this.data = data;
        this.retMsg = retMsg;
    }

    public ResultVO() {

    }
}
