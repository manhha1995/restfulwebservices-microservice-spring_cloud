package com.microservices.restfulwebservices.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.microservices.restfulwebservices.repository.entity.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final List<User> userList = new ArrayList<User>();

    private static int userCount = 2;

    static {
        userList.add(new User(1, "Ann", new Date(0)));
        userList.add(new User(2, "Bob", new Date(1)));
    }

    public List<User> findAll() {
        return userList;
    }

    public User findOne(int id) {
        for (User user : userList) {
            if (user.getUserId() == id) {
                return user;
            } 
        }
        return null;
    }

    public User saveAll(User user) {
        if (user.getUserId() != null) {
            user.setUserId(++userCount);
        }
        userList.add(user);
        return user;
    }

    public User deleteById(int id) {
        Iterator<User> iter = userList.iterator();
        while (iter.hasNext()) {
            User user = iter.next();
            if(user.getUserId() == id) {
                iter.remove();
            }
        }
        return null;
    }

}
