package com.github.martagor.word.snake.easy.service.impl;


import com.github.martagor.word.snake.easy.exceptions.FirstLetterNotMatchException;
import com.github.martagor.word.snake.easy.exceptions.ForbiddenWordException;
import com.github.martagor.word.snake.easy.model.PieceOfSnake;
import com.github.martagor.word.snake.easy.model.User;
import com.github.martagor.word.snake.easy.repository.SnakeRepository;
import com.github.martagor.word.snake.easy.repository.UserRepository;
import com.github.martagor.word.snake.easy.service.SnakeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    UserRepository userRepository;

    @Override
    public void addWord(String word) {
        validate(word);

        checkWordMatchingExistingSnake(word);

        PieceOfSnake lastWord = snakeRepository.findFirstByOrderByTimeDesc();
        PieceOfSnake snake = new PieceOfSnake();
        User loggedUser = userRepository.findOne(getLoggedUserId());
        snake.setUser(loggedUser);
        if (lastWord == null) {
            snake.setWord(word);
            snake.setTime(LocalDateTime.now());
            snakeRepository.save(snake);
        } else {
            String nextWord = formatWord(word);
            snake.setWord(nextWord);
            snake.setTime(LocalDateTime.now());
            snakeRepository.save(snake);
        }
    }

    private String formatWord(String word) {
        int length = word.length();
        return word.substring(0, 1).toUpperCase()
                + word.substring(1, length - 1)
                + word.substring(length - 1, length).toUpperCase();
    }

    private void checkWordMatchingExistingSnake(String word) {
        PieceOfSnake lastWord = snakeRepository.findFirstByOrderByTimeDesc();
        if (lastWord == null) {
            return;
        }

        String oldWord = lastWord.getWord();
        if (word.isEmpty()) {
            throw new FirstLetterNotMatchException();
        }
        String firstLetterOfNewWord = word.substring(0, 1);

        if (StringUtils.endsWithIgnoreCase(oldWord, firstLetterOfNewWord)) {
            return;
        }
        throw new FirstLetterNotMatchException();
    }

    private void validate(String word) {
        if (word.toLowerCase().endsWith("y")) {
            throw new ForbiddenWordException();
        }
    }

    private Long getLoggedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof User)) {
            throw new RuntimeException();
        }
        User user = (User)principal;
        return user.getId();
    }

    @Override
    public List<String> findAll() {
        return snakeRepository.findAllByUserIdOrderByTimeDesc(getLoggedUserId()).stream()
                .sorted(comparing(PieceOfSnake::getTime))
                .map(PieceOfSnake::getWord)
                .collect(toList());
    }


}
