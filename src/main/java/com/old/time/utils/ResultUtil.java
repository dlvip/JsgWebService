package com.old.time.utils;


import com.old.time.domain.Result;

public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setStatus(0);
        result.setMsg("成功");
        result.setData(object);

        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.setStatus(0);
        result.setMsg("成功");

        return result;
    }

    public static Result error(Integer status, String msg) {
        Result result = new Result();
        result.setStatus(status);
        result.setMsg(msg);

        return result;
    }
}
