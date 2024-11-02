package com.WorkFlowManager.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit-menu")
public class EditController {

    @GetMapping
    public String getEditOptionsPage() {
        return "menu";
    }
}