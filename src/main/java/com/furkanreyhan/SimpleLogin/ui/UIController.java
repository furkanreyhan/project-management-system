package com.furkanreyhan.SimpleLogin.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    @GetMapping("/register")
    public String ui() {
        return "user-reg.html";
    }
}
