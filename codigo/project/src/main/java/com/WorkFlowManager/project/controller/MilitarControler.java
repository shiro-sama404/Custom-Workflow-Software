package com.WorkFlowManager.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/militares")
public class MilitarControler {

    @GetMapping()
    public String getMilitares(@RequestParam String param) {
        return "militares";
    }
}