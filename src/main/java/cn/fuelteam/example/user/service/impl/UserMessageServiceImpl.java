package cn.fuelteam.example.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fuelteam.example.user.dao.MessageMapper;
import cn.fuelteam.example.user.dao.UserMapper;
import cn.fuelteam.example.user.model.Message;
import cn.fuelteam.example.user.model.User;
import cn.fuelteam.example.user.service.UserMessageService;

@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @SuppressWarnings("unused")
    @Override
    @Transactional("userex")
    public void save(User user, Message message) throws Exception {
        try {
            userMapper.insertSelective(user);
            int x = 1 / 0;
            messageMapper.insertSelective(message);
        } catch (Exception ex) {
            throw ex;
        }
    }

}
