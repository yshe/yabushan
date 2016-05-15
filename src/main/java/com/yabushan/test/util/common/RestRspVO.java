package com.yabushan.test.util.common;

/**
 * @create 
 * @author yabushan
 *
 * @param <T>
 */
public class RestRspVO<T> extends BaseRespVO {

    public T result;
    public RestRspVO(){
        super();
    }
    public RestRspVO(Integer code, String message){
        super(code,message);
    }
    public RestRspVO(Integer code, String message, T result) {
        super(code, message);
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
