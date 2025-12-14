package com.example.ToDoApp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ToDoApp.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    
    // 期限検索用
    List<Task> findByDeadline(LocalDate deadline);

    // 期限ソート用
    List<Task> findAllByOrderByDeadlineAsc();
}