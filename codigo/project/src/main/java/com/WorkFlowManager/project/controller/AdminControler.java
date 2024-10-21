package com.WorkFlowManager.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminControler {
    
    @GetMapping("/edit")
    public String getEdit(){
        return "Edit";
    }
}
