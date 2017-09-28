package com.github.martagor.word.snake.easy.service.impl;


import com.github.martagor.word.snake.easy.model.PieceOfSnake;
import com.github.martagor.word.snake.easy.repository.SnakeRepository;
import com.github.martagor.word.snake.easy.service.SnakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnakeServiceImpl implements SnakeService {

    @Autowired
    SnakeRepository snakeRepository;


    @Override
    public PieceOfSnake addWord(String word) {
        PieceOfSnake lastWord = snakeRepository.findFirstByOrderByTimeDesc();
        if (lastWord == null) {
            lastWord.setWord(word);
            return lastWord;
        }
        return lastWord;
    }

}
