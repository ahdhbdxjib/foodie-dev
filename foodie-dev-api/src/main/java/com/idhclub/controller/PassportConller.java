package com.idhclub.controller;

import com.idhclub.pojo.Users;
import com.idhclub.pojo.bo.UserBo;
import com.idhclub.services.UserServices;
import com.idhclub.utils.CookieUtils;
import com.idhclub.utils.JSONResult;
import com.idhclub.utils.JsonUtils;
import com.idhclub.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;

@Api(value = "注册登录", tags = "用于注册登录")
@RestController
@RequestMapping("passport")
public class PassportConller {
    final static Logger logger =  LoggerFactory.getLogger(PassportConller.class);

    @Autowired
    UserServices userServices;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public JSONResult usernameIsExit(@RequestParam String username) {
        if (StringUtils.isBlank(username)) {
            return JSONResult.errorMsg("用户名存在");
        }
        boolean isExit = userServices.queryUseExist(username);
        if (isExit) {
            return JSONResult.errorMsg("用户名存在");
        }
        return JSONResult.ok();

    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public JSONResult regist(@RequestBody UserBo userBo, HttpServletRequest request, HttpServletResponse response) {
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPassword = userBo.getConfirmPassword();
//        不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }
//        exist判断
        boolean isExit = userServices.queryUseExist(username);
        if (isExit) {
            return JSONResult.errorMsg("用户名已经存在");
        }
//        两次密码是否一致
        if (password.length() < 6) {
            return JSONResult.errorMsg("密码长度小于6");

        }
//        密码长度>6
        if (!password.equals(confirmPassword)) {
            return JSONResult.errorMsg("密码不一致");

        }
       Users user =  userServices.createUsers(userBo);

        user = setNullProperty(user);
//        将数据放到cokkie中
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user)
                ,true);
//        注册
        return JSONResult.ok();

    }


    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public JSONResult login(@RequestBody UserBo userBo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPassword = userBo.getConfirmPassword();
//        不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }
        Users user = userServices.queryUserForLogin(username, MD5Utils.getMD5Str(password));


        if (user == null) {
            return JSONResult.errorMsg("用户名密码错误");
        }
        user = setNullProperty(user);
//        将数据放到cokkie中
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user)
                ,true);

        return JSONResult.ok(user);
    }

    private Users setNullProperty(Users user) {
        user.setPassword(null);
        user.setRealname(null);
        user.setMobile(null);
        user.setCreatedTime(null);
        user.setUpdatedTime(null);
        user.setBirthday(null);
        return user;
    }


    @ApiOperation(value = "用户退出", notes = "用户退出", httpMethod = "POST")
    @PostMapping("/logout")
    public JSONResult login(@RequestParam String userId,HttpServletRequest request, HttpServletResponse response)  {
//        清除cookie
        CookieUtils.deleteCookie(request,response,"user");

//        TODO logout时候需要清空购物车
//        TODO 分布式会话需要清楚用户数据

        return JSONResult.ok();

    }

    }
