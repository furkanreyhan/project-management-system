package com.furkanreyhan.SimpleLogin.controller;

import com.furkanreyhan.SimpleLogin.entity.Task;
import com.furkanreyhan.SimpleLogin.entity.TaskCreateRequest;
import com.furkanreyhan.SimpleLogin.service.TaskService;
import com.furkanreyhan.SimpleLogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController{
    TaskService taskService;
    UserService userService;

    @Autowired
    public TaskController(TaskService taskService,UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody TaskCreateRequest taskCreateRequest,
                           @RequestHeader(name = "Authorization") String authorizationHeader){
            //bodyden alma tokendan al id yi
        String jwtToken = authorizationHeader.substring(7);

            //jwt providera kontrol edici 2,3 metod ve onları çağıran 1 metod. buradan o üst metodu çağırabilirsin.
            //subject,signature vs.
        return taskService.createOneTask(taskCreateRequest,jwtToken);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId,
                           @RequestHeader(name = "Authorization") String authorizationHeader){

        String jwtToken = authorizationHeader.substring(7);

        taskService.deleteTaskById(taskId,jwtToken);
    }


}

