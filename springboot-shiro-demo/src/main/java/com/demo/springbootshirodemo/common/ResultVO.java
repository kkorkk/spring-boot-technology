package com.demo.springbootshirodemo.common;

/**
 * @Author：kkorkk.
 * @Date：2018/10/22 17:26
 * @Description：请求返回结果类
 */
public class ResultVO {

    private String retCode;

    private String retMsg;

    private Object data;

    public static ResultVO SUCCESS(Object data){
        return new ResultVO("0","",data);
    }

    public static ResultVO SUCCESS(String retMsg,Object data){
        return new ResultVO("0",retMsg,data);
    }

    public static ResultVO ERROR(String retCode,String retMsg){
        return new ResultVO(retCode,retMsg,null);
    }

    public static ResultVO ERROR(String retCode,String retMsg,Object data){
        return new ResultVO(retCode,retMsg,data);
    }

    public ResultVO(String retCode, String retMsg, Object data) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.data = data;
    }

    public ResultVO() {
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
