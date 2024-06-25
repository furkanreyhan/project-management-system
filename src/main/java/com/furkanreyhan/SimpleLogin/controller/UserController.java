package com.furkanreyhan.SimpleLogin.controller;

import com.furkanreyhan.SimpleLogin.entity.Task;
import com.furkanreyhan.SimpleLogin.responses.ResponseDto;
import com.furkanreyhan.SimpleLogin.entity.User;
import com.furkanreyhan.SimpleLogin.exception.UserNotFoundException;
import com.furkanreyhan.SimpleLogin.jwt.JwtProvider;
import com.furkanreyhan.SimpleLogin.responses.JwtResponse;
import com.furkanreyhan.SimpleLogin.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    UserService userService;
    JwtProvider jwtProvider;

    @Autowired
    public UserController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register-user")
    public ResponseEntity<ResponseDto> createUser(@RequestBody User userRegister) {
        User user = new User();

        user.setUsername(userRegister.getUsername());
        user.setEmail(userRegister.getEmail());
        user.setPassword(userRegister.getPassword().hashCode());

        userService.saveUser(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Kayit Basarili"));
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody User userLogin) {
        boolean isAuthenticated = userService.authenticateUser(userLogin);

        if (isAuthenticated) {
            return new JwtResponse(
                    jwtProvider.generateToken(userLogin.getUsername()),
                    "Giris Basarili",
                    userLogin.getUsername()
                    );
        } else {
            throw new UserNotFoundException("Kullanıcı Bulunamadı");
        }
    }

    @PutMapping("/assignTask")
    public User assignTaskToUser (@RequestParam Long userId, @RequestParam Long taskId){
        return userService.assignTaskToUser(userId,taskId);
    }


}
