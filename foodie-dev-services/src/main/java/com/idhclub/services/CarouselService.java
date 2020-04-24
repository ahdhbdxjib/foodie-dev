package com.idhclub.services;


import com.idhclub.pojo.Carousel;

import java.util.List;

public interface CarouselService {
    /**
     * 查询轮播图
     * @param isShow
     * @return
     */
    public List<Carousel> queryAll(Integer isShow);
}
