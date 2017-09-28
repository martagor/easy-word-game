package com.github.martagor.word.snake.easy.service.impl;


import com.github.martagor.word.snake.easy.model.PieceOfSnake;
import com.github.martagor.word.snake.easy.repository.SnakeRepository;
import com.github.martagor.word.snake.easy.service.SnakeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

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

    @Override
    public List<String> findAll() {
        return snakeRepository.findAll().stream()
                .sorted(comparing(PieceOfSnake::getTime))
                .map(PieceOfSnake::getWord)
                .collect(toList());
    }

}
