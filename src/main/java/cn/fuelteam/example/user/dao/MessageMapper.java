package cn.fuelteam.example.user.dao;

import cn.fuelteam.example.user.model.Message;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MessageMapper extends Mapper<Message>, MySqlMapper<Message> {}