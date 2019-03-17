package com.old.time.handle;

import com.old.time.domain.Result;
import com.old.time.exception.JSGNoSuchElementException;
import com.old.time.exception.JSGRuntimeException;
import com.old.time.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class Exceptionhandle {

    private static final Logger logger = LoggerFactory.getLogger(Exceptionhandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof JSGRuntimeException) {
            JSGRuntimeException jSGRuntimeException = (JSGRuntimeException) e;

            return ResultUtil.error(jSGRuntimeException.getStatus(), jSGRuntimeException.getMessage());
        } else if (e instanceof JSGNoSuchElementException) {
            JSGNoSuchElementException noSuchElementException = (JSGNoSuchElementException) e;

            return ResultUtil.error(noSuchElementException.getStatus(), noSuchElementException.getMessage());
        } else {
            logger.error("【系统异常】{}", e.getMessage());

            return ResultUtil.error(-500, e.getMessage());
        }
    }
}
