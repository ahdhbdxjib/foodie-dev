package com.idhclub.controller;

import com.idhclub.services.UserServices;
import com.idhclub.utils.JSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("passport")
public class PassportConller {
    @Autowired
    UserServices userServices;

    @GetMapping("/usernameIsExit")
    public JSONResult usernameIsExit(@RequestParam String username) {
        if(StringUtils.isBlank(username)){
            return JSONResult.errorMsg("用户名不能为空");
        }
        boolean isExit = userServices.queryUseExist(username);
        if(isExit){
            return JSONResult.errorMsg("用户名不能为空");
        }
        return JSONResult.ok();

    }
}
