package com.example.quiz.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** ◯×クイズ：1問分の出題・回答データ */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizBooleanExchangeForm {
    
    /** 識別ID */
    private Integer id;

    /** 問題文 */
    private String question;

    /** 正解 (Boolean) */
    private Boolean answer;

    /** ユーザーの回答 (Boolean) */
    private Boolean myAnswer;
}