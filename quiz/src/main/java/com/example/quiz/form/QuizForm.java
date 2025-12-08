package com.example.quiz.form;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Form */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizForm {
	/** 識別ID */
	private Integer id;
	/** クイズの内容 */
	@NotBlank
	private String question;
	/** クイズの解答 */
	private Boolean answer;
	/** 作成者 */
	@NotBlank
	private String author;
	/** 「登録」or「変更」判定用 */
	private Boolean newQuiz;
}
