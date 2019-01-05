package cn.fuelteam.example.user.service;

import cn.fuelteam.example.user.model.Message;
import cn.fuelteam.example.user.model.User;

public interface UserMessageService {

    void save(User user, Message message) throws Exception;
}
