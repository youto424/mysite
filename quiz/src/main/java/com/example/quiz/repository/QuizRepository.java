package com.example.quiz.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.quiz.entity.Quiz;


/** Quizテーブル：RepositoryImpl */
public interface QuizRepository extends CrudRepository<Quiz, Integer> {
	@Query("SELECT id FROM quiz ORDER BY RANDOM() limit 1")
	Integer getRandomId();
	
	// ↓↓↓ ランダムに指定件数取得 ↓↓↓
	@Query("SELECT * FROM quiz ORDER BY RANDOM() LIMIT :limit")
    Iterable<Quiz> getRandomQuizzes(@Param("limit") Integer limit);
}
