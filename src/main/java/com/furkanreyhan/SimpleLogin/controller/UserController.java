package com.furkanreyhan.SimpleLogin.controller;

import com.furkanreyhan.SimpleLogin.entity.Task;
import com.furkanreyhan.SimpleLogin.responses.ResponseDto;
import com.furkanreyhan.SimpleLogin.entity.User;
import com.furkanreyhan.SimpleLogin.exception.UserNotFoundException;
import com.furkanreyhan.SimpleLogin.jwt.JwtProvider;
import com.furkanreyhan.SimpleLogin.responses.JwtResponse;
import com.furkanreyhan.SimpleLogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserService userService;
    JwtProvider jwtProvider;

    @Autowired
    public UserController(UserService userService,JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register-user")
    public ResponseEntity<ResponseDto> createUser(@RequestBody User userRegister){
        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setEmail(userRegister.getEmail());
        user.setPassword(userRegister.getPassword().hashCode());

        userService.saveUser(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(),"Kayit Basarili"));
    }

    //tokeni string olarak döndürürken sıkıntı yok, responseentity dönünce hata veriyor.
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User userLogin) {
        boolean isAuthenticated = userService.authenticateUser(userLogin);

        if (isAuthenticated) {
            JwtResponse jwtResponse = new JwtResponse();

            jwtResponse.setAccessToken(jwtProvider.generateToken(userLogin.getUsername()));
            jwtResponse.setUsername(userLogin.getUsername());
            jwtResponse.setMessage("Giris Basarili");

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + jwtResponse.getAccessToken());

            System.out.println(jwtResponse.getAccessToken());

            return new ResponseEntity<>("Giriş Başarılı", headers, HttpStatus.OK);
        } else {
                throw new UserNotFoundException("Kullanıcı Bulunamadı");
        }
    }


}