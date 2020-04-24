package com.idhclub.controller;

import com.idhclub.enums.YesOrNo;
import com.idhclub.pojo.Carousel;
import com.idhclub.pojo.Category;
import com.idhclub.pojo.Stu;
import com.idhclub.services.CarouselService;
import com.idhclub.services.CategoryService;
import com.idhclub.services.StuServices;
import com.idhclub.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("index")
@Api(value = "首页展示", tags = "用于首页展示")
public class IndexConller {
    @Autowired
    CarouselService carouselService;

    @ApiOperation(value = "获取首页的轮播图", notes = "获取首页的轮播图", httpMethod = "GET")
    @GetMapping("/carousel")
    public JSONResult carousel() {
        List<Carousel> res = carouselService.queryAll(YesOrNo.YES.type);
        return JSONResult.ok(res);
    }


    @Autowired
    CategoryService categoryService;

    @ApiOperation(value = "获取首页一级分类", notes = "获取首页一级分类", httpMethod = "GET")
    @GetMapping("/cats")
    public JSONResult cats() {
        List<Category> res = categoryService.queryAllRootLevelCat();
        return JSONResult.ok(res);
    }
}
