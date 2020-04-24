package com.idhclub.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户对象BO",description = "封装前端的用户信息")
public class UserBo {
    @ApiModelProperty(value = "用户名",name = "username",example = "idhclib",required = true)
    private String username;
    @ApiModelProperty(value = "密码",name = "passsword",example = "123456",required = true)
    private String password;
    @ApiModelProperty(value = "确认密码",name = "confrimpasssword",example = "123456",required = true)
    private String confirmPassword;

}
