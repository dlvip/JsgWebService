package com.old.time.enums;

public enum ResultEnum {

    //通用提示信息
    CURRENCY_MSG_NON_PERMISSION(-9000, "操作失败，权限不够"),
    CURRENCY_MSG_NON_DATE(-9001, "操作失败，数据不存在"),
    CURRENCY_MSG_PARAMETER_ERROR(-9002, "参数错误"),


    //用户提示信息
    USER_NON_EXISTENT(-1000, "用户不存在"),
    USER_ALREADY_EXISTENT(-1001, "用户已经存在，请登录"),

    //验证码提示信息
    MOBILE_CODE_ERROR(-1020, "验证码有误，请重新输入"),
    MOBILE_CODE_INVALID(-1021, "验证码已失效，请重试"),
    MOBILE_CODE_SEND(-1022, "验证码已发送至手机，2分钟内有效"),

    //地址提示信息
    USER_ADDRESS_NON(-1030, "地址不存在"),

    //课程提示信息
    USER_COURSE_NON(-1040, "专辑不存在"),

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
