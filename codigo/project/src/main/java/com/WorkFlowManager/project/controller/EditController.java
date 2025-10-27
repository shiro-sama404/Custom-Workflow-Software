package com.WorkFlowManager.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditController {

    @GetMapping("/editar")
    public String getEditOptionsPage(Model model) {
        return "edit_section";
    }
}