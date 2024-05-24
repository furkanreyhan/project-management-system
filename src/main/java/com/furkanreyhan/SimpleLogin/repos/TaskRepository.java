package com.furkanreyhan.SimpleLogin.repos;

import com.furkanreyhan.SimpleLogin.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByUserId(Long userId);

}
