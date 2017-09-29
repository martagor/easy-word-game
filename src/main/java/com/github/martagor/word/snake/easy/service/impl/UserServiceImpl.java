package com.github.martagor.word.snake.easy.service.impl;

import com.github.martagor.word.snake.easy.model.User;
import com.github.martagor.word.snake.easy.repository.UserRepository;
import com.github.martagor.word.snake.easy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        if (user.getId() == null) {
            userRepository.save(user);
        }
        return null;
    }
}
