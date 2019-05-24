package cn.fuelteam.example.user.service;

import java.util.List;

import cn.fuelteam.example.user.model.User;

public interface UserService {

    List<User> findAll();

    void delAll();

    void save(String name, String description);

    List<User> findAllBySlave();

    User findFirst();

    User findFirstBySlave();
}