package com.yidiandian.utils;

import lombok.Data;

/**
 * @Author: luoxian
 * @Date: 2019/5/22 18:10
 */
@Data
public class MyException extends RuntimeException {

    private Integer code;
    public MyException(String msg){
        super(msg);
    }
    public MyException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
}