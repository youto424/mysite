package com.example.quiz.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.quiz.entity.QuizChoice;
import com.example.quiz.repository.QuizChoiceRepository;

@Service
@Transactional
public class QuizChoiceServiceImpl implements QuizChoiceService {

	/** Repository：注入 */
	@Autowired
	QuizChoiceRepository repository;

	@Override
	public Iterable<QuizChoice> selectAll() {
		return repository.findAll();
	}

	@Override
	public Optional<QuizChoice> selectOneById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Optional<QuizChoice> selectOneRandomQuiz() {
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
	public Boolean checkQuiz(Integer id, Integer myAnswer) {
		Boolean check = false;
		// 対象のクイズを取得
		Optional<QuizChoice> optQuiz = repository.findById(id);
		
		// 値存在チェック
		if (optQuiz.isPresent()) {
			QuizChoice quiz = optQuiz.get();
			// クイズの解答チェック (数値同士の比較)
			if (quiz.getAnswer().equals(myAnswer)) {
				check = true;
			}
		}
		return check;
	}

	@Override
	public void insertQuiz(QuizChoice quiz) {
		repository.save(quiz);
	}

	@Override
	public void updateQuiz(QuizChoice quiz) {
		repository.save(quiz);
	}

	@Override
	public void deleteQuizById(Integer id) {
		repository.deleteById(id);
	}
	
	/**
     * CSVファイルを読み込み、DBに保存する
     */
    @Override
    public void importCsv(MultipartFile file) throws IOException {
        // ファイルの中身を読み込むための準備 (文字コードはUTF-8)
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            // 1行ずつ読み込む
            while ((line = br.readLine()) != null) {
                // カンマで区切って配列にする
                // CSV形式: 問題文,選択肢1,選択肢2,選択肢3,選択肢4,正解番号,作成者
                String[] split = line.split(",");

                // データが足りない行はスキップ（エラー回避）
                if (split.length < 7) {
                    continue; 
                }

                // Entityにセットする
                QuizChoice quiz = new QuizChoice();
                quiz.setQuestion(split[0]);
                quiz.setChoice1(split[1]);
                quiz.setChoice2(split[2]);
                quiz.setChoice3(split[3]);
                quiz.setChoice4(split[4]);
                quiz.setAnswer(Integer.parseInt(split[5])); // 数値に変換
                quiz.setAuthor(split[6]);

                // 保存
                repository.save(quiz);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("CSVの読み込みに失敗しました", e);
        }
    }
    @Override
    public Iterable<QuizChoice> selectRandomQuizzes(Integer limit) {
    		return repository.getRandomQuizzes(limit);
    }
}