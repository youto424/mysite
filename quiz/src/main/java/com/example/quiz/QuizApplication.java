package com.example.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.quiz.entity.Quiz;
import com.example.quiz.service.QuizService;

@SpringBootApplication
public class QuizApplication {
	/** 起動メソッド */
	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}
	/** 注入 */
	@Autowired
	QuizService service;
	/** 実行メソッド */
	private void execute() {
		//登録処理
		// setup();
		// 全件取得
		// showList();
		// 1件取得
		// showOne();
		// 更新処理
		// updateQuiz();
		// 削除処理
		// deleteQuiz();
		// クイズを実行する
		// doQuiz();
	}
	/** === クイズを5件登録します === */
	private void setup() {
		System.out.println("--- 登録処理開始 ---");
		// エンティティ生成
		Quiz quiz1 = new Quiz(null, "「Java」はオブジェクト指向言語である。", true, "登録太郎");
		Quiz quiz2 = new Quiz(null, "「Spring Data」はデータアクセスに対する" + "機能を提供する。", true, "登録太郎");
		Quiz quiz3 = new Quiz(null, "プログラムが沢山配置されているサーバーの" + "ことを「ライブラリ」という。", false, "登録太郎");
		Quiz quiz4 = new Quiz(null, "「@Component」はインスタンス生成アノテーション" + "である。", true, "登録太郎");
		Quiz quiz5 = new Quiz(null, "「Spring MVC」が実装している「デザインパターン」で" + "すべてのリクエストを1つのコントローラで受け取るパターンは" + "「シングルコントローラ・パターン」である。", false, "登録太郎");
		// リストにエンティティを格納
		List<Quiz> quizList = new ArrayList<>();
		// 第一引数に格納先、第二引数は可変長引数なので、エンティティを記述
		Collections.addAll(quizList, quiz1, quiz2, quiz3, quiz4, quiz5);
		// 登録実行
		for (Quiz quiz : quizList) {
			// 登録実行
			service.insertQuiz(quiz);
		}
		System.out.println("--- 登録処理完了 ---");
	}
	/** === 全件取得 === */
	private void showList() {
		System.out.println("--- 全件取得開始 ---");
		// リポジトリを使用して全件取得を実施、結果を取得
		Iterable<Quiz> quizzes= service.selectAll();
		for (Quiz quiz : quizzes) {
			System.out.println(quiz);
		}
		System.out.println("--- 全件取得終了 ---");
	}
	/** === 1件取得 === */
	private void showOne() {
		System.out.println("--- 1件取得開始 ---");
		// リポジトリを使用して1件取得を実施、結果を取得(戻り値はOptional）
		Optional<Quiz> quizOpt = service.selectOneById(1);
		// 値存在チェック
		if (quizOpt.isPresent()) {
			System.out.println(quizOpt.get());
		} else {
			System.out.println("該当する問題が存在しません・・・");
		}
		System.out.println("--- 1件取得完了 ---");
	}
	/** === 更新処理 === */
	private void updateQuiz() {
		System.out.println("--- 更新処理開始 ---");
		// 変更したいエンティティを生成する
		Quiz quiz1 = new Quiz(1, "「スプリング」はフレームワークですか？", true, "変更タロウ");
		// 更新実行
		service.updateQuiz(quiz1);
		System.out.println("--- 更新処理完了 ---");
	}
	/** 削除処理 */
	private void deleteQuiz() {
		System.out.println("--- 削除処理開始 ---");
		// 削除実行
		service.deleteQuizById(2);
		System.out.println("--- 削除処理完了 ---");
	}
	/** === ランダムでクイズを取得して、クイズの正解/不正解を判定する === */
	private void doQuiz() {
		System.out.println("--- クイズ1件取得開始 ---");
		// リポジトリを使用して1件取得を実施、結果を取得（戻り値はOptional）
		Optional<Quiz> quizOpt = service.selectOneRandomQuiz();
		// 値存在チェック
		if (quizOpt.isPresent()) {
			System.out.println(quizOpt.get());
		} else {
			System.out.println("該当する問題が存在しません・・・");
		}
		System.out.println("--- クイズ1件取得完了 ---");
		// 解答を実施
		Boolean myAnswer = false;
		Integer id = quizOpt.get().getId();
		if (service.checkQuiz(id, myAnswer) ) {
			System.out.println("正解です！");
		} else {
			System.out.println("不正解です・・・");
		}
	}
}
