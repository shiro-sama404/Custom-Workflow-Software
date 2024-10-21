package com.WorkFlowManager.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("previa")
public class PreviaControler {

    @GetMapping("")
    public String getPrevia(@RequestParam String param) {
        return "previa";
    }
    
}