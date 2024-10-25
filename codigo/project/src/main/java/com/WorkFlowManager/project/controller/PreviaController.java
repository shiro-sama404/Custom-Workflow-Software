package com.WorkFlowManager.project.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.WorkFlowManager.project.dto.PreviaDTO;
import com.WorkFlowManager.project.model.Previa;
import com.WorkFlowManager.project.service.PreviaService;

@Controller
@RequestMapping("/edit/previa")
public class PreviaController {

    private final PreviaService previaService;

    public PreviaController(PreviaService previaService) {
        this.previaService = previaService;
    }

    @GetMapping("")
    public String escalaPrevia(@PathVariable Long userId, @PathVariable Long escalaId) {
        return "previa";
    }

    @GetMapping("/{escalaId}")
    public Optional<Previa> getAllpreviasByUserId(@PathVariable Long userId, @PathVariable Long escalaId) {
        return previaService.getRascunho(userId, escalaId);
    }

    @PostMapping
    public Previa createprevia(@RequestBody Previa previa) {
        return previaService.createPrevia(previa);
    }

    @PutMapping("/{id}")
    public Previa updateprevia(@PathVariable Long id, @RequestBody PreviaDTO previa) {
        return previaService.updatePrevia(id, previa);
    }

    @DeleteMapping("/{id}")
    public void deleteprevia(@PathVariable Long id) {
        previaService.deletePrevia(id);
    }
    
}