package com.idhclub.services;

import com.idhclub.pojo.Stu;
import com.idhclub.pojo.Users;
import com.idhclub.pojo.bo.UserBo;

public interface UserServices {
    /**
     * 判断用户谁否存在
     * @param userName
     * @return
     */
    public boolean queryUseExist(String userName);

    /**
     * 创建用户
     * @param userBo
     * @return
     */
    public Users createUsers(UserBo userBo);
}
