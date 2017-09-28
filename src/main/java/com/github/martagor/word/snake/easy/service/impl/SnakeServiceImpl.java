package com.github.martagor.word.snake.easy.service.impl;


import com.github.martagor.word.snake.easy.model.PieceOfSnake;
import com.github.martagor.word.snake.easy.repository.SnakeRepository;
import com.github.martagor.word.snake.easy.service.SnakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SnakeServiceImpl implements SnakeService {

    @Autowired
    SnakeRepository snakeRepository;


    @Override
    public void addWord(String word) {
        PieceOfSnake lastWord = snakeRepository.findFirstByOrderByTimeDesc();
        if (lastWord == null) {
            PieceOfSnake snake = new PieceOfSnake();
            snake.setWord(word);
            snake.setTime(LocalDateTime.now());
            snakeRepository.save(snake);
        }
    }

}
