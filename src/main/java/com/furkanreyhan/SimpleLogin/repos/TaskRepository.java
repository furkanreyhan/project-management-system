package com.furkanreyhan.SimpleLogin.repos;

import com.furkanreyhan.SimpleLogin.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {

}
