package com.example.quiz.form;

import java.util.List;

import lombok.Data;

/** ◯×クイズ：10問プレイ用のForm */
@Data
public class QuizBooleanPlayForm {
    
    /** 10問分のクイズリスト */
    private List<QuizBooleanExchangeForm> quizList;
}