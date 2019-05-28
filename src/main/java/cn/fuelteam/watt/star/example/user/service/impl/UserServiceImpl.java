package cn.fuelteam.watt.star.example.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.fuelteam.watt.star.annotation.DataSourceRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.pagehelper.PageHelper;

import cn.fuelteam.watt.star.example.user.dao.UserMapper;
import cn.fuelteam.watt.star.example.user.enums.State;
import cn.fuelteam.watt.star.example.user.model.User;
import cn.fuelteam.watt.star.example.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional("user")
    public void delAll() {
        User user = new User();
        user.setState(State.enable.intCode());
        int num = userMapper.delete(user);
        logger.info("deleted {} records！", num);
    }

    @Override
    @Transactional("user")
    @DataSourceRouter(slave = false) // 在主库执行更新操作
    public void save(String name, String description) {
        User record = new User();
        record.setName(name);
        record.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        record.setState(State.enable.intCode());
        record.setDescription(description);
        logger.info("{}", record);
        int num = userMapper.insertSelective(record);
        Assert.isTrue(num == 1, "save user failed");
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    @DataSourceRouter(slave = true)
    public List<User> findAllBySlave() {
        return this.findAll();
    }

    @Override
    public User findFirst() {
        User user = new User();
        PageHelper.startPage(1, 1, " name desc");
        return userMapper.selectOne(user);
    }

    @Override
    @DataSourceRouter(slave = true)
    public User findFirstBySlave() {
        return this.findFirst();
    }

    @Override
    public void saves(String... names) {
        List<User> recordList = new ArrayList<>();
        for (String name : names) {
            User record = new User();
            record.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            record.setName(name);
            record.setState(State.disable.intCode());
            record.setDescription("batch inserted");
            recordList.add(record);
        }
        int num = userMapper.insertList(recordList);
        logger.info("saved {} records", num);
    }
}