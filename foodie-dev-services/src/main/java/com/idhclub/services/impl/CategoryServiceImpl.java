package com.idhclub.services.impl;

import com.idhclub.mapper.CategoryMapper;
import com.idhclub.pojo.Carousel;
import com.idhclub.pojo.Category;
import com.idhclub.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);
        List<Category> result = categoryMapper.selectByExample(example);
        return result;
    }

}
