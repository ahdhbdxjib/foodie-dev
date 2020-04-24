package com.idhclub.services.impl;

import com.idhclub.enums.Sex;
import com.idhclub.mapper.UsersMapper;
import com.idhclub.pojo.Users;
import com.idhclub.pojo.bo.UserBo;
import com.idhclub.services.UserServices;
import com.idhclub.utils.*;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserServices {


    @Autowired
    UsersMapper usersMapper;

    @Autowired
    Sid sid;

    public static final String USER_FACE="http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUseExist(String userName) {
        Example userExample = new Example(Users.class);
        Example.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andEqualTo("username", userName);
        Users users = usersMapper.selectOneByExample(userExample);
        return users == null ? false : true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Users createUsers(UserBo userBo) {
        String userid = sid.nextShort();
        Users user = new Users();
        user.setId(userid);
        user.setUsername(userBo.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        默认用户昵称
        user.setNickname(userBo.getUsername());
//        默认头像
        user.setFace(USER_FACE);
//        默认生日
        user.setBirthday(DateUtil.stringToDate("1970-01-01"));

        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());

        user.setUpdatedTime(new Date());


        usersMapper.insert(user);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andEqualTo("username", username);
        userExampleCriteria.andEqualTo("password", password);

        Users user = usersMapper.selectOneByExample(userExample);
        return user;
    }

}
