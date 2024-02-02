package com.itm.task8.dao;

import com.itm.task8.model.User;

import java.util.List;

public interface UserDao {

    User getUserById(Long id);

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);
}
