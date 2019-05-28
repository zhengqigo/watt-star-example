package cn.fuelteam.watt.star.example.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.fuelteam.watt.star.example.base.model.Base;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface BaseMapper extends Mapper<Base>, MySqlMapper<Base> {

    @Select("select a.* from base a")
    List<Base> findAll();
}