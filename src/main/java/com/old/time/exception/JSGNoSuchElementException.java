package com.old.time.exception;

import com.old.time.enums.ResultEnum;

import java.util.NoSuchElementException;

public class JSGNoSuchElementException extends NoSuchElementException {

    private Integer status;

    public JSGNoSuchElementException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.status = resultEnum.getStatus();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer code) {
        this.status = code;
    }
}
