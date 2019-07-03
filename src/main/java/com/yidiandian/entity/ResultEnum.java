package com.yidiandian.entity;

/**
 * @Author: luoxian
 * @Date: 2019/5/22 16:38
 */
public enum ResultEnum {
    SUCCESS(200000,"成功"),
    FAIL(200001,"失败"),
    EXCEPTION(500000,"系统异常"),
    PARAMS_IS_NULL(200002,"参数缺失"),
    PARAMS_IS_ERROR(200003,"参数值错误"),
    RECORD_NON_EXISTENT(200004,"记录不存在");
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
