package com.github.martagor.word.snake.easy.service.impl;


import com.github.martagor.word.snake.easy.exceptions.FirstLetterNotMatchException;
import com.github.martagor.word.snake.easy.exceptions.ForbiddenWordException;
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
        validate(word);
        PieceOfSnake lastWord = snakeRepository.findFirstByOrderByTimeDesc();
        if (lastWord == null) {
            PieceOfSnake snake = new PieceOfSnake();
            snake.setWord(word);
            snake.setTime(LocalDateTime.now());
            snakeRepository.save(snake);
        }
    }

    private void checkWordMatchingExistingSnake(String word) {
        String lastWord = snakeRepository.findFirstByOrderByTimeDesc().getWord();
        if (lastWord == null) {
            return;
        }
        char lastLetter = lastWord.toLowerCase().charAt(lastWord.length()-1);
        if (word.charAt(0) == lastLetter) {
            return;
        }
        throw new FirstLetterNotMatchException();
    }

    private void validate(String word) {
        if (word.toLowerCase().endsWith("y")) {
            throw new ForbiddenWordException();
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
