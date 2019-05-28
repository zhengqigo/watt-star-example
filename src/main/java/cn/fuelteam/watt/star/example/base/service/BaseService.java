package cn.fuelteam.watt.star.example.base.service;

import com.github.pagehelper.PageInfo;

import cn.fuelteam.watt.star.example.base.model.Base;

public interface BaseService {

    void save(String name);

    PageInfo<Base> findAll(int pageNo, int pageSize);
}