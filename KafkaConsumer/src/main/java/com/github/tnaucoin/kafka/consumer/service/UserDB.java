package com.github.tnaucoin.kafka.consumer.service;

import com.github.tnaucoin.kafka.consumer.enums.UserId;
import com.github.tnaucoin.kafka.consumer.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDB {
    private static final Map<String, User> userMap = new HashMap<>();
    static {
        userMap.put(UserId.A1B2C3.toString(),new User(UserId.A1B2C3));
        userMap.put(UserId.ABC123.toString(), new User(UserId.ABC123));
        userMap.put(UserId.ABC321.toString(), new User(UserId.ABC321));
        userMap.put(UserId.CBA123.toString(), new User(UserId.CBA123));
        userMap.put(UserId.CBA321.toString(), new User(UserId.CBA321));
    }

    public User findById(String id){
        return userMap.get(id);
    }

    public void save(User user){
        userMap.put(user.getUserId().toString(),user);
    }
}
