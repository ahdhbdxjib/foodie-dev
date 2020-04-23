package com.idhclub.services.impl;

import com.idhclub.mapper.StuMapper;
import com.idhclub.pojo.Stu;
import com.idhclub.services.StuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StuServiceImpl implements StuServices {

    @Autowired
    StuMapper stuMapper;

    @Override
    public Stu getById(int id) {

        return stuMapper.selectByPrimaryKey(id);

    }
}
