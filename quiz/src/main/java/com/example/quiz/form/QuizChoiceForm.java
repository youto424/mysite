package com.example.quiz.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 4択用 Form */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizChoiceForm {
    /** 識別ID */
    private Integer id;

    /** クイズの内容 */
    @NotBlank
    private String question;

    /** 選択肢1 */
    @NotBlank
    private String choice1;

    /** 選択肢2 */
    @NotBlank
    private String choice2;

    /** 選択肢3 */
    @NotBlank
    private String choice3;

    /** 選択肢4 */
    @NotBlank
    private String choice4;

    /** 正解の番号 (1~4) */
    @NotNull
    @Min(1)
    @Max(4)
    private Integer answer;

    /** 作成者 */
    @NotBlank
    private String author;

    /** 「登録」or「変更」判定用 */
    private Boolean newQuiz;
}