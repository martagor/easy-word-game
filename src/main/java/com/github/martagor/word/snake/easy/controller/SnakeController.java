package com.github.martagor.word.snake.easy.controller;

import com.github.martagor.word.snake.easy.service.SnakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/snake")
public class SnakeController {

    @Autowired
    SnakeService gameService;

    @PutMapping


    @GetMapping
    public String info() { return "No problems detected."; }

}
