package com.github.martagor.word.snake.easy.controller;

import com.github.martagor.word.snake.easy.service.SnakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/snake")
public class SnakeController {

    @Autowired
    SnakeService gameService;

    @GetMapping
    public String info() { return "No problems detected."; }

    @PostMapping("/{word}")
    public List<String> addWord(@PathVariable String word) {
        gameService.addWord(word);
        return gameService.findAll();
    }
}
