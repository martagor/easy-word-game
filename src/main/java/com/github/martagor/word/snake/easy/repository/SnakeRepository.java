package com.github.martagor.word.snake.easy.repository;

import com.github.martagor.word.snake.easy.model.PieceOfSnake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnakeRepository extends JpaRepository<PieceOfSnake, Long> {
    PieceOfSnake findFirstByOrderByTimeDesc();
}