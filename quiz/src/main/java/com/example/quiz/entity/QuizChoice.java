package com.example.quiz.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 4択クイズ用：Entity */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("quiz_choice") 
public class QuizChoice {
    /** 識別ID */
    @Id
    private Integer id;
    /** クイズの内容 */
    private String question;
    /** 選択肢1 */
    private String choice1;
    /** 選択肢2 */
    private String choice2;
    /** 選択肢3 */
    private String choice3;
    /** 選択肢4 */
    private String choice4;
    /** 正解の番号 (1~4) */
    private Integer answer;
    /** 作成者 */
    private String author;
}