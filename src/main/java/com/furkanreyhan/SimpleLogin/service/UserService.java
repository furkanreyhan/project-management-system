package com.furkanreyhan.SimpleLogin.service;

import com.furkanreyhan.SimpleLogin.entity.Dummy;
import com.furkanreyhan.SimpleLogin.exception.UnauthorizedException;
import com.furkanreyhan.SimpleLogin.exception.UserAlreadyExistsException;
import com.furkanreyhan.SimpleLogin.exception.UserNotFoundException;
import com.furkanreyhan.SimpleLogin.entity.User;
import com.furkanreyhan.SimpleLogin.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    UserRepository userRepository;
    DummyService dummyService;

    @Autowired
    public UserService(UserRepository userRepository,DummyService dummyService) {
        this.userRepository = userRepository;
        this.dummyService = dummyService;
    }

    public void saveUser(User user) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent())
            throw new UserAlreadyExistsException("Bu isimde bir kullanici zaten var!");

        Random random = new Random();
        int randomInt = random.nextInt(10);


        for (int i = 0; i < randomInt ; i++){
            int randomInt2 = random.nextInt(24);
            Dummy dummyItem = dummyService.getDummyItem(String.valueOf(randomInt2));
            System.out.println(dummyItem);
        }
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean authenticateUser(User userLogin) {
        Optional<User> userOptional = userRepository.findByUsername(userLogin.getUsername());
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            return validatePassword(user, userLogin);
        } else {
            throw new UserNotFoundException("Kullanici Bulunamadi");
        }
    }

    public boolean validatePassword(User user, User userLogin) {
        if (user.getPassword() != userLogin.getPassword().hashCode()) {
            throw new UnauthorizedException("Sifre yanlis");
        }

        return true;
    }


}
