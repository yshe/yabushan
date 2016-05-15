package com.yabushan.test.util.common;

/**
 * Created by Vincent on 2015/1/3.
 */
public class BaseRespVO {

    private  Integer code;
    private String message;

    public BaseRespVO() {
    	
    }

    public BaseRespVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
