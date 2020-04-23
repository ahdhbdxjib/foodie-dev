package com.idhclub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloConller {
    @GetMapping("/hello")
    public Object hello (){
        return "Hello";
    }
}
