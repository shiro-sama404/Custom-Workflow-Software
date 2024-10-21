package com.WorkFlowManager.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/restricoes")
public class RestricaoControler {

    @GetMapping()
    public String getRestricoes(@RequestParam String param) {
        return "restricoes";
    }
    
}