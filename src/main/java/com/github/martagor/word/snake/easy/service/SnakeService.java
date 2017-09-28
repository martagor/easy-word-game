package com.github.martagor.word.snake.easy.service;

import com.github.martagor.word.snake.easy.model.PieceOfSnake;

import java.util.List;

public interface SnakeService {
    void addWord(String word);
    List<String> findAll();

}
