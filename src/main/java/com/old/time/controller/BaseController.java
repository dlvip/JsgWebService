package com.old.time.controller;


import com.old.time.domain.Result;
import com.old.time.service.JSGuangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class BaseController {

    @Autowired
    public JSGuangService jSGuangService;

    public  Result getControllerList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize){

        return null;
    }

}
