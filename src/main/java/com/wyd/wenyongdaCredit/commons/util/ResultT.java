package com.wyd.wenyongdaCredit.commons.util;

public class ResultT<T> {
    private Integer code;
    private String msg;
    private T data;
    private String type;

    public ResultT(Integer code, String msg, String type){
        this.code = code;
        this.msg = msg;
        this.type = type;
    }

    public static <T> ResultT<T> ok(){
        return new <T> ResultT<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), "success");
    }

    public static <T> ResultT<T> error(ResultEnum status){
        return new <T> ResultT<T>(status.getCode(), status.getMsg(), "error");
    }

    public ResultT<T> data(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
