package com.example.quiz.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.quiz.entity.QuizChoice;
import com.example.quiz.form.QuizChoiceForm;
import com.example.quiz.form.QuizExchangeForm;
import com.example.quiz.form.QuizPlayForm;
import com.example.quiz.service.QuizChoiceService;

/** 4択Quizコントローラー */
@Controller
@RequestMapping("/quiz/choice")
public class QuizChoiceController {
    /** DI対象 */
    @Autowired
    QuizChoiceService service;

    /** 「form-backing bean」の初期化 */
    @ModelAttribute
    public QuizChoiceForm setUpForm() {
        QuizChoiceForm form = new QuizChoiceForm();
        // ラジオボタンのデフォルト値設定 (1番を選択状態に)
        form.setAnswer(1);
        return form;
    }

    /** Quizの一覧を表示します */
    @GetMapping
    public String showList(QuizChoiceForm quizForm, Model model) {
        // 新規登録設定
        quizForm.setNewQuiz(true);
        // 一覧を取得する
        Iterable<QuizChoice> list = service.selectAll();
        // 表示用「Model」への格納
        model.addAttribute("list", list);
        model.addAttribute("title", "4択クイズ：登録用フォーム");
        return "choice_crud"; // ※後で作ります
    }

    /** Quizデータを1件挿入 */
    @PostMapping("/insert")
    public String insert(@Validated QuizChoiceForm quizForm, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) {
        // FormからEntityへの詰め替え
        QuizChoice quiz = makeQuiz(quizForm);
        // 入力チェック
        if (!bindingResult.hasErrors()) {
            service.insertQuiz(quiz);
            redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
            return "redirect:/quiz/choice";
        } else {
            // エラーがある場合は、一覧表示処理を呼びます
            return showList(quizForm, model);
        }
    }

    /** Quizデータを1件取得し、フォーム内表示する */
    @GetMapping("/{id}")
    public String showUpdate(QuizChoiceForm quizForm, @PathVariable Integer id, Model model) {
        // Quizを取得（Optionalでラップ）
        Optional<QuizChoice> quizOpt = service.selectOneById(id);
        // QuizFormへの詰め直し
        Optional<QuizChoiceForm> quizFormOpt = quizOpt.map(t -> makeQuizForm(t));
        // QuizFormがnullでなければ中身を取り出す
        if(quizFormOpt.isPresent()) {
            quizForm = quizFormOpt.get();
        }
        // 更新用のModelを作成する
        makeUpdateModel(quizForm, model);
        return "choice_crud";
    }

    /** 更新用のModelを作成する */
    private void makeUpdateModel(QuizChoiceForm quizForm, Model model) {
        model.addAttribute("id", quizForm.getId());
        quizForm.setNewQuiz(false);
        model.addAttribute("quizChoiceForm", quizForm); // ここ重要
        model.addAttribute("title", "4択クイズ：更新用フォーム");
    }

    /** idをKeyにしてデータを更新する */
    @PostMapping("/update")
    public String update(
            @Validated QuizChoiceForm quizForm,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        // QuizFormからQuizに詰め直す
        QuizChoice quiz = makeQuiz(quizForm);
        // 入力チェック
        if (!result.hasErrors()) {
            service.updateQuiz(quiz);
            redirectAttributes.addFlashAttribute("complete", "更新が完了しました");
            return "redirect:/quiz/choice/" + quiz.getId();
        } else {
            makeUpdateModel(quizForm, model);
            return "choice_crud";
        }
    }

    /** idをKeyにしてデータを削除する */
    @PostMapping("/delete")
    public String delete(
        @RequestParam("id") String id,
        Model model,
        RedirectAttributes redirectAttributes) {
        service.deleteQuizById(Integer.parseInt(id));
        redirectAttributes.addFlashAttribute("delcomplete", "削除が完了しました");
        return "redirect:/quiz/choice";
    }

    // ---------- 【以下はクイズで遊ぶ機能】 ----------
    
    /** Quizデータをランダムで1件取得し、画面に表示する */
    @GetMapping("/play")
    public String showQuiz(QuizChoiceForm quizForm, Model model) {
        Optional<QuizChoice> quizOpt = service.selectOneRandomQuiz();
        
        if(quizOpt.isPresent()) {
            Optional<QuizChoiceForm> quizFormOpt = quizOpt.map(t -> makeQuizForm(t));
            quizForm = quizFormOpt.get();
        } else {
            model.addAttribute("msg", "問題がありません・・・");
            return "choice_play"; // ※後で作ります
        }
        
        model.addAttribute("quizChoiceForm", quizForm);
        return "choice_play";
    }

    /** クイズの正解/不正解を判定する */
    @PostMapping("/check")
    public String checkQuiz(QuizChoiceForm quizForm, @RequestParam Integer answer, Model model) {
        // サービスで正解チェック (IDと回答番号を渡す)
        if (service.checkQuiz(quizForm.getId(), answer)) {
            model.addAttribute("msg", "正解です！");
        } else {
            model.addAttribute("msg", "残念、不正解です・・・");
        }
        return "choice_answer"; // ※後で作ります
    }

    // ---------- 【以下はFormとDomainObjectの詰め直し】 ----------
    
    /** QuizFormからQuizに詰め直して戻り値として返します */
    private QuizChoice makeQuiz(QuizChoiceForm quizForm) {
        QuizChoice quiz = new QuizChoice();
        quiz.setId(quizForm.getId());
        quiz.setQuestion(quizForm.getQuestion());
        quiz.setChoice1(quizForm.getChoice1());
        quiz.setChoice2(quizForm.getChoice2());
        quiz.setChoice3(quizForm.getChoice3());
        quiz.setChoice4(quizForm.getChoice4());
        quiz.setAnswer(quizForm.getAnswer());
        quiz.setAuthor(quizForm.getAuthor());
        return quiz;
    }

    /** QuizからQuizFormに詰め直して戻りとし返します */
    private QuizChoiceForm makeQuizForm(QuizChoice quiz) {
        QuizChoiceForm form = new QuizChoiceForm();
        form.setId(quiz.getId());
        form.setQuestion(quiz.getQuestion());
        form.setChoice1(quiz.getChoice1());
        form.setChoice2(quiz.getChoice2());
        form.setChoice3(quiz.getChoice3());
        form.setChoice4(quiz.getChoice4());
        form.setAnswer(quiz.getAnswer());
        form.setAuthor(quiz.getAuthor());
        form.setNewQuiz(false);
        return form;
    }
    
    /** CSVインポート処理 */
    @PostMapping("/csv")
    public String csvImport(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            // サービスを呼び出してCSVを読み込む
            service.importCsv(file);
            redirectAttributes.addFlashAttribute("complete", "CSVインポートが完了しました");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("msg", "CSVの読み込みに失敗しました");
        }
        // 一覧画面にリダイレクト
        return "redirect:/quiz/choice";
    }
    
    /** 10問連続プレイ画面の表示 */
    @GetMapping("/play10")
    public String showPlay10(Model model) {
        // 1. 10件ランダムに取得
        Iterable<QuizChoice> quizzes = service.selectRandomQuizzes(10);
        
        // 2. Formのリストに詰め替える
        QuizPlayForm playForm = new QuizPlayForm();
        List<QuizExchangeForm> exchangeList = new ArrayList<>();
        
        for (QuizChoice quiz : quizzes) {
            QuizExchangeForm exchangeForm = new QuizExchangeForm();
            exchangeForm.setId(quiz.getId());
            exchangeForm.setQuestion(quiz.getQuestion());
            exchangeForm.setChoice1(quiz.getChoice1());
            exchangeForm.setChoice2(quiz.getChoice2());
            exchangeForm.setChoice3(quiz.getChoice3());
            exchangeForm.setChoice4(quiz.getChoice4());
            exchangeForm.setAnswer(quiz.getAnswer()); // 正解も持たせておく（採点用）
            exchangeList.add(exchangeForm);
        }
        
        playForm.setQuizList(exchangeList);
        model.addAttribute("quizPlayForm", playForm);
        
        return "play_10"; // ※後で作ります
    }

    /** 10問連続プレイの答え合わせ */
    @PostMapping("/check10")
    public String checkPlay10(@ModelAttribute QuizPlayForm quizPlayForm, Model model) {
        int correctCount = 0;
        int totalCount = quizPlayForm.getQuizList().size();
        
        // リストをループして採点
        for (QuizExchangeForm form : quizPlayForm.getQuizList()) {
            // 正解(answer) と 自分の回答(myAnswer) が一致しているか
            if (form.getAnswer().equals(form.getMyAnswer())) {
                correctCount++;
            }
        }
        
        // 結果をModelに格納
        model.addAttribute("correctCount", correctCount);
        model.addAttribute("totalCount", totalCount);
        
        return "answer_10";
    }
}