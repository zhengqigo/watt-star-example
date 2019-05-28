package cn.fuelteam.watt.star.example.message.dao;

import org.apache.ibatis.annotations.Param;

import cn.fuelteam.watt.star.example.message.model.Message;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface MessageMapper extends Mapper<Message>, MySqlMapper<Message>, InsertListMapper<Message> {

    void createIfNotExists(@Param("dayStr") String dayStr);
}