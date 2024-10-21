package com.WorkFlowManager.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/historico")
public class HistoricoControler {

    @GetMapping()
    public String getHistorico(@RequestParam String param) {
        return "historico";
    }
}