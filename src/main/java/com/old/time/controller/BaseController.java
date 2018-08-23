package com.old.time.controller;


import com.old.time.domain.Result;
import com.old.time.service.JSGuangService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {

    @Autowired
    public JSGuangService jSGuangService;

    public abstract Result getControllerList();


}
