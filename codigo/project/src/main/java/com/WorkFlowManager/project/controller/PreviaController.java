package com.WorkFlowManager.project.controller;

import com.WorkFlowManager.project.dto.PreviaDTO;
import com.WorkFlowManager.project.model.Previa;
import com.WorkFlowManager.project.service.PreviaService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping({"/", "/previa", "home"})
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
    public Optional<List<Previa>> getAllPreviasByEscala(@PathVariable Long userId, @PathVariable Long escalaId) {
        return previaService.getRascunho(userId, escalaId);
    }

    @GetMapping("/edit/{escalaId}")
    public Optional<List<Previa>> getAllPreviasSalvasByUsuario(@PathVariable Long userId, @PathVariable Long escalaId) {
        return previaService.getRascunho(userId, escalaId);
    }

    @PostMapping("/edit/{escalaId}")
    public List<Previa> createPrevia(@RequestBody List<PreviaDTO> previaDetails) {
        return previaService.createPrevia(previaDetails);
    }

    @PutMapping("/edit/{escalaId}")
    public List<Previa> updatePrevia(@PathVariable Long id, @RequestBody List<PreviaDTO> previaDetails) {
        return previaService.updatePrevia(id, previaDetails);
    }

    @DeleteMapping("/edit/{id}")
    public void deletePrevia(@PathVariable Long id) {
        previaService.deletePrevia(id);
    }
}