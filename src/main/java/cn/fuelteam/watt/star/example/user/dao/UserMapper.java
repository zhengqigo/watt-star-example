package cn.fuelteam.watt.star.example.user.dao;

import cn.fuelteam.watt.star.example.user.model.User;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>, InsertListMapper<User> {}