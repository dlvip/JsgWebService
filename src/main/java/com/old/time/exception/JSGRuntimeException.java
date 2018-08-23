package com.old.time.exception;


import com.old.time.enums.ResultEnum;

public class JSGRuntimeException extends RuntimeException {

    private Integer status;

    public JSGRuntimeException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.status = resultEnum.getStatus();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer code) {
        this.status = status;
    }
}
