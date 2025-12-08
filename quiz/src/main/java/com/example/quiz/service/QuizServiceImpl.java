package com.example.quiz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizRepository;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {
	/** Repository：注入 */
	@Autowired
	QuizRepository repository;
	@Override
	public Iterable<Quiz> selectAll() {
		return repository.findAll();
	}
	@Override
	public Optional<Quiz> selectOneById(Integer id) {
		return repository.findById(id);
	}
	@Override
	public Optional<Quiz> selectOneRandomQuiz() {
		// ランダムでidの値を取得する
		Integer randId = repository.getRandomId();
		// 問題がない場合
		if (randId == null) {
			// 空のOptionalインスタンスを返します。
			return Optional.empty();
			
		}
		return repository.findById(randId);
	}
	@Override
	public Boolean checkQuiz(Integer id, Boolean myAnswer) {
		// クイズの正解/不正解を判定用変数
		Boolean check = false;
		// 対象のクイズを取得
		Optional<Quiz> optQuiz = repository.findById(id);
		// 値存在チェック
		if (optQuiz.isPresent()) {
			Quiz quiz = optQuiz.get();
			// クイズの解答チェック
			if(quiz.getAnswer().equals(myAnswer)) {
				check = true;
			}
		}
		return check;
	}
	@Override
	public void insertQuiz(Quiz quiz) {
		repository.save(quiz);

	}
	@Override
	public void updateQuiz(Quiz quiz) {
		repository.save(quiz);
	}
	@Override
	public void deleteQuizById(Integer id) {
		repository.deleteById(id);
	}

}
