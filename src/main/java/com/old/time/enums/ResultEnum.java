package com.old.time.enums;

public enum ResultEnum {
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
