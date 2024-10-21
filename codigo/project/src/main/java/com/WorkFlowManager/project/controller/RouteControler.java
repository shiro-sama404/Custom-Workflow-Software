package com.WorkFlowManager.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//import java.time.LocalDate;

@Controller
public class RouteControler {

    @GetMapping("/previa")
    public String getPrevia(){
        return "previa";
    }

    @GetMapping("/historico")
    public String getHistorico(){
        return "historico";
    }

    @GetMapping("/militares")
    public String getMilitares(){
        return "militares";
    }

    @GetMapping("/restricoes")
    public String getRestricoes(){
        return "restricoes";
    }

    @GetMapping("/tutorial")
    public String getTutorial(){
        return "tutorial";
    }
}
