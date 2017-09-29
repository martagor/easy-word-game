package com.github.martagor.word.snake.easy.repository;

import com.github.martagor.word.snake.easy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
