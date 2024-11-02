package com.WorkFlowManager.project.controller;

import com.WorkFlowManager.project.dto.EscalaDTO;
import com.WorkFlowManager.project.model.Escala;
import com.WorkFlowManager.project.service.EscalaService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/escala")
public class EscalaController {

    private final EscalaService escalaService;

    public EscalaController(EscalaService escalaService) {
        this.escalaService = escalaService;
    }

    @GetMapping
    public List<Escala> getAllEscalas() {
        return escalaService.getAllEscalas();
    }

    @GetMapping("/{id}")
    public Escala getEscalaById(@PathVariable Long id) {
        return escalaService.getEscalaById(id);
    }

    @PostMapping
    public Escala createEscala(@RequestBody EscalaDTO escalaDetails) {
        return escalaService.createEscala(escalaDetails);
    }

    @PutMapping("/{id}")
    public Escala updateEscala(@PathVariable Long id, @RequestBody EscalaDTO escalaDetails) {
        return escalaService.updateEscala(id, escalaDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEscala(@PathVariable Long id) {
        escalaService.deleteEscala(id);
    }
}