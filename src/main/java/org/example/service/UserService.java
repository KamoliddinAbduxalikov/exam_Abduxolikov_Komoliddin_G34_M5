package org.example.service;

import org.example.domain.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    List<User> USER_LIST = new ArrayList<>();

    User login(String name, String password);

    void register(User user);

    void changePassword(Integer userId, String newPassword);
}
