package cn.fuelteam.example.user.service.impl;

import java.util.List;

import org.fuelteam.watt.star.annotation.MasterSlaveRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.fuelteam.example.user.dao.UserMapper;
import cn.fuelteam.example.user.model.User;
import cn.fuelteam.example.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional("userex")
    public void delAll() {
        User user = new User();
        user.setState("0");
        int num = userMapper.delete(user);
        if (num > 0) logger.info("删除{}条数据！", num);
    }

    @Override
    @Transactional("userex")
    @MasterSlaveRouter(slave = false) // 更新操作必须在主库执行
    public void save(String name, String description) {
        User record = new User();
        record.setName(name);
        record.setState("1");
        record.setDescription(description);
        userMapper.insertSelective(record);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    @MasterSlaveRouter(slave = true)
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
    @MasterSlaveRouter(slave = true)
    public User findFirstBySlave() {
        return this.findFirst();
    }
}
