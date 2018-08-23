package com.old.time.enums;

public enum ResultEnum {

    USER_NON_EXISTENT(-1000, "用户不存在，请注册"),
    USER_ALREADY_EXISTENT(-1001, "用户已经存在，请登录"),

    MOBILE_CODE_ERROR(-1020, "验证码有误，请重新输入"),
    MOBILE_CODE_INVALID(-1021, "验证码已失效，请重试"),
    MOBILE_CODE_SEND(-1022, "验证码已发送至手机，2分钟内有效"),

    SUCCESS(0, "成功"),
    SYSTEM_ERROR(-1, "系统异常"),
    NULL_DATA_ERROR(-100, "内容不存在"),
    DATA_FORMAT_ERROR(-200, "数据格式有误");


    private Integer status;

    private String msg;

    ResultEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;

    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
