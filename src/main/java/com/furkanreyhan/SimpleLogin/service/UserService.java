package com.furkanreyhan.SimpleLogin.service;

import com.furkanreyhan.SimpleLogin.entity.Task;
import com.furkanreyhan.SimpleLogin.exception.UnauthorizedException;
import com.furkanreyhan.SimpleLogin.exception.UserNotFoundException;
import com.furkanreyhan.SimpleLogin.entity.User;
import com.furkanreyhan.SimpleLogin.repos.TaskRepository;
import com.furkanreyhan.SimpleLogin.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    TaskRepository taskRepository;

    @Autowired
    public UserService(UserRepository userRepository,TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }
    public void saveUser(User user) {
        userRepository.save(user);

    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public boolean authenticateUser(User userLogin) {
        Optional<User> userOptional = userRepository.findByUsername(userLogin.getUsername());
        User user;
        if (userOptional.isPresent()){
            user = userOptional.get();
            return validatePassword(user,userLogin);
        }
        else {
            //kullanıcı adı bulunamadı
            throw new UserNotFoundException("Kullanici Bulunamadi");
        }
    }
    public boolean validatePassword(User user,User userLogin){
        if( user.getPassword() != userLogin.getPassword().hashCode()) {

            throw new UnauthorizedException("Sifre yanlis");
        }

        return true;
    }


    public User assignTaskToUser(Long userId, Long taskId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalUser.isPresent() && optionalTask.isPresent() ){
            User user = optionalUser.get();
            Task task = optionalTask.get();

            if (!user.getTaskList().contains(task)){
                user.getTaskList().add(task);
                userRepository.save(user);
            }
            else {
                throw new RuntimeException("Task is already assigned to this user");
            }

            return user;
        }

        throw new RuntimeException("Task or User not found!");


    }
}
