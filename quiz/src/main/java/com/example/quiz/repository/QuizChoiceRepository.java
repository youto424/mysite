package com.example.quiz.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.quiz.entity.QuizChoice;

/** 4択Quizテーブル：Repository */
public interface QuizChoiceRepository extends CrudRepository<QuizChoice, Integer> {
    
    @Query("SELECT id FROM quiz_choice ORDER BY RANDOM() limit 1")
    Integer getRandomId();
    
    @Query("SELECT * FROM quiz_choice ORDER BY RANDOM() LIMIT :limit")
    Iterable<QuizChoice> getRandomQuizzes(@Param("limit") Integer limit);
}