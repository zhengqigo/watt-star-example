package cn.fuelteam.example.user.dao;

import cn.fuelteam.example.user.model.User;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UserMapper extends Mapper<User>, MySqlMapper<User> {}