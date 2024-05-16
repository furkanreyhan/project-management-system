package com.furkanreyhan.SimpleLogin.service;

import com.furkanreyhan.SimpleLogin.entity.Task;
import com.furkanreyhan.SimpleLogin.entity.TaskCreateRequest;
import com.furkanreyhan.SimpleLogin.entity.User;
import com.furkanreyhan.SimpleLogin.jwt.JwtProvider;
import com.furkanreyhan.SimpleLogin.repos.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TaskService {
    TaskRepository taskRepository;
    JwtProvider jwtProvider;
    UserService userService;

    @Autowired
    public TaskService(JwtProvider jwtProvider,TaskRepository taskRepository,UserService userService) {
        this.jwtProvider = jwtProvider;
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public Task createOneTask(TaskCreateRequest taskCreateRequest,String jwtToken){
        User user = userService.getUserById(taskCreateRequest.getUserId());
        if (user != null && jwtProvider.getUsernameToken(jwtToken).equals(user.getUsername())){
            Task taskToCreate = new Task();
            //taskToCreate.setId(taskCreateRequest.getId());
            taskToCreate.setTitle(taskCreateRequest.getTitle());
            taskToCreate.setDescription(taskCreateRequest.getDescription());
            taskToCreate.setUser(user);
            taskToCreate.setDueDate(new Date(7));
            return taskRepository.save(taskToCreate);
        }
        else
            return null;


    }

    public void deleteTaskById(Long id,String jwtToken){
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()){
            User user = userService.getUserById(task.get().getId());

            if (user!=null && jwtProvider.getUsernameToken(jwtToken).equals(user.getUsername())){
                taskRepository.deleteById(id);
            }

        }
    }
}
