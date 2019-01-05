package cn.fuelteam.example.user.service;

import com.github.pagehelper.PageInfo;

import cn.fuelteam.example.user.model.Message;

public interface MessageService {

    void save(String title, String context);

    PageInfo<Message> findAll(int pageNo, int pageSize);
}
