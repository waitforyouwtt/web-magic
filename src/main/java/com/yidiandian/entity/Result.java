package com.yidiandian.entity;

/**
 * @Author: luoxian
 * @Date: 2019/5/21 10:57
 */
public class Result<T> {
    private boolean flag = true;
    private Integer code;
    private String message;
    private T data;

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }
    public static <T> Result<T> success(T data) {
        Result<T> response = new Result();
        response.setCode(StateCode.OK.getCode());
        response.setMessage(StateCode.OK.getMessage());
        response.setData(data);
        return response;
    }
    public static <T> Result<T> error(T data) {
        Result<T> response = new Result();
        response.setCode(StateCode.ERROR.getCode());
        response.setMessage(StateCode.ERROR.getMessage());
        response.setData(data);
        return response;
    }

    public static <T> Result<T> error() {
        Result<T> response = new Result();
        response.setCode(StateCode.ERROR.getCode());
        response.setMessage(StateCode.ERROR.getMessage());
        response.setData(null);
        return response;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "flag=" + flag +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
