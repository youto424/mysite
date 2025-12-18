package com.example.quiz.form;

import java.util.List;

import lombok.Data;

/** 10問プレイ用のForm */
@Data
public class QuizPlayForm {
    
    /** 10問分のクイズリスト */
    private List<QuizExchangeForm> quizList;
}