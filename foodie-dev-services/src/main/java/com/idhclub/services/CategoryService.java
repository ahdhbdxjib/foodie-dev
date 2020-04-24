package com.idhclub.services;


import com.idhclub.pojo.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 查询一级分类
     * @return
     */
    public List<Category> queryAllRootLevelCat();
}
