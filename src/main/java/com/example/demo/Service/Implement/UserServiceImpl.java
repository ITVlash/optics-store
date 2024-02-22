package com.example.demo.Service.Implement;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// UserServiceImplement.java
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String login, String password, int session) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }

    @Override
    public User authenticateUser(String login, String password, int session) {
        User user = userRepository.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            user.setSession(1);
            return user;
        }
        return null;
    }
}
