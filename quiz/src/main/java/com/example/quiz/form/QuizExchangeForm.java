package com.example.quiz.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 1問分の出題・回答データを扱うクラス */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizExchangeForm {
    
    /** 識別ID */
    private Integer id;

    /** 問題文 */
    private String question;

    /** 選択肢1 */
    private String choice1;
    /** 選択肢2 */
    private String choice2;
    /** 選択肢3 */
    private String choice3;
    /** 選択肢4 */
    private String choice4;

    /** 正解の番号 (DBから取得した正解) */
    private Integer answer;

    /** ユーザーの回答 (画面で選択された値) */
    private Integer myAnswer;
}