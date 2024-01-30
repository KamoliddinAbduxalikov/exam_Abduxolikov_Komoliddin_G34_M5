package org.example.service.impl;

import org.example.domain.User;
import org.example.service.UserService;

public class UserServiceImpl implements UserService {

    {
        USER_LIST.add(User.builder().name("Jek").age(17).password("123").build());
        USER_LIST.add(User.builder().name("Smit").age(17).password("777").build());
        USER_LIST.add(User.builder().name("Piter").age(17).password("333").build());
    }

    @Override
    public User login(String name, String password) {
        for (User user : USER_LIST) {
            if (user.getName().equals(name) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void register(User user) {
        if (existUser(user.getName(),user.getPassword())){
            System.out.println("Bunday foydalanuvchi mavjud !!!");
            return;
        }
        USER_LIST.add(user);
    }

    @Override
    public void changePassword(Integer userId, String newPassword) {
        getUserById(userId).setPassword(newPassword);
    }


    private boolean existUser(String name, String password){
        return USER_LIST.stream()
                 .anyMatch(user -> user.getName().equals(name)
                 || user.getPassword().equals(password));
    }

    private static User getUserById(Integer userId){
        for (User user : USER_LIST){
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    private static UserServiceImpl userService;

    public static UserServiceImpl getInstance(){
        if (userService == null){
            userService = new UserServiceImpl();
            return userService;
        }
       return userService;
    }
}
