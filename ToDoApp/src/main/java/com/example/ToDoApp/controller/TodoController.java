package com.example.ToDoApp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ToDoApp.entity.Task;
import com.example.ToDoApp.repository.TaskRepository;

@Controller
public class TodoController {

    private final TaskRepository taskRepository;

    public TodoController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 一覧表示（検索・ソート機能付き）
    @GetMapping("/")
    public String index(Model model, 
                        @RequestParam(name = "mode", required = false) String mode) {
        List<Task> taskList;

        if ("today".equals(mode)) {
            taskList = taskRepository.findByDeadline(LocalDate.now());
        } else if ("sort".equals(mode)) {
            taskList = taskRepository.findAllByOrderByDeadlineAsc();
        } else {
            taskList = taskRepository.findAll();
        }

        model.addAttribute("tasks", taskList);
        return "todoList";
    }

    // 追加画面へ遷移
    @GetMapping("/add")
    public String showAddForm() {
        return "taskForm";
    }

    // タスク追加処理
    @PostMapping("/add")
    public String addTask(
            @RequestParam String content,
            @RequestParam String user,
            @RequestParam LocalDate deadline) {
        
        Task newTask = new Task();
        newTask.setContent(content);
        newTask.setUser(user);
        newTask.setDeadline(deadline);
        newTask.setCreatedAt(LocalDate.now());
        
        taskRepository.save(newTask);
        return "redirect:/";
    }

    // タスク削除処理
    @PostMapping("/delete")
    public String deleteTask(@RequestParam Integer id) {
        taskRepository.deleteById(id);
        return "redirect:/";
    }
    
    // タスク完了処理
    @PostMapping("/complete")
    public String completeTask(@RequestParam Integer id) {
        Task task = taskRepository.findById(id).get();
        task.setIsCompleted(true);
        taskRepository.save(task);
        return "redirect:/";
    }
}