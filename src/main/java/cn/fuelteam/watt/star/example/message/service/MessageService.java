package cn.fuelteam.watt.star.example.message.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.fuelteam.watt.star.example.message.model.Message;

public interface MessageService {

    void save(String title, String context);

    PageInfo<Message> findAll(int pageNo, int pageSize);

    void createIfNotExists();

    void saves(List<Message> messages);
}