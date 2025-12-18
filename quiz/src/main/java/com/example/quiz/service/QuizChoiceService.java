package com.example.quiz.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.quiz.entity.QuizChoice;

/** 4択クイズ用：Serviceインターフェース */
public interface QuizChoiceService {
	/** クイズ情報を全件取得します */
	Iterable<QuizChoice> selectAll();
	
	/** クイズ情報を、idをキーに1件取得します */
	Optional<QuizChoice> selectOneById(Integer id);
	
	/** クイズ情報をランダムで1件取得します */
	Optional<QuizChoice> selectOneRandomQuiz();
	
	/** クイズの正解/不正解を判定します */
	Boolean checkQuiz(Integer id, Integer myAnswer);
	
	/** クイズを登録します */
	void insertQuiz(QuizChoice quiz);
	
	/** クイズを更新します */
	void updateQuiz(QuizChoice quiz);
	
	/** クイズを削除します */
	void deleteQuizById(Integer id);
	
	/** CSVインポート機能 */
    void importCsv(MultipartFile file) throws IOException;
    
    /** クイズをランダムで複数件取得します */
    Iterable<QuizChoice> selectRandomQuizzes(Integer limit);
}