package com.example.demo.Service;

import com.example.demo.Entity.User;

//UserService.java
public interface UserService {
    User registerUser(String login, String password, int session);
    User authenticateUser(String login, String password, int session);
}