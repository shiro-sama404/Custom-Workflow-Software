package com.WorkFlowManager.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginControler {
    
    @GetMapping("/login")
    public String getLogin(){
        return "Login";
    }

    @PostMapping("/login")
    public String verify(){
        return "Login";
    }
}
