package com.WorkFlowManager.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TutorialController {

    @GetMapping("/tutorial")
    public String getPreviaPage(Model model) {
        return "tutorial";
    }
}