package com.wyd.wenyongdaCredit.commons.util;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {

    private Integer code;
    private String msg;
    private Map<Object,Object> data = new HashMap<>();
    private String type;

    public ResultUtil(Integer code, String msg, String type){
        this.code = code;
        this.msg = msg;
        this.type = type;
    }

    public static ResultUtil ok(){
        return new ResultUtil(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), "success");
    }

    public static ResultUtil error(ResultEnum status){
        return new ResultUtil(status.getCode(), status.getMsg(), "error");
    }


    public ResultUtil data(Map<Object,Object> data){
        this.data = data;
        return this;
    }

    public ResultUtil data(String key,Object value){
        this.data.put(key, value);
        return this;
    }


    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Map<Object,Object>  data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Map<Object,Object>  getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
