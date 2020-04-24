package com.idhclub.controller;

import com.idhclub.pojo.Stu;
import com.idhclub.services.StuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class HelloConller {
    @Autowired
    StuServices stuServices;

    @GetMapping("/hello")
    public Object hello() {
        Stu stu =  stuServices.getById(1203);
        return stu.getName();
    }
}
