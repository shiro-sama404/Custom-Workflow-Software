package com.WorkFlowManager.project.controller;

import com.WorkFlowManager.project.dto.HistoricoDTO;
import com.WorkFlowManager.project.model.Historico;
import com.WorkFlowManager.project.service.HistoricoService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/historico")
public class HistoricoController {

    private final HistoricoService historicoService;

    public HistoricoController(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    @GetMapping
    public List<Historico> getAllHistoricos(@PathVariable Long id) {
        return historicoService.getAllHistoricos();
    }

    @GetMapping("/{id}")
    public Historico getHistoricoById(@PathVariable Long id) {
        return historicoService.getHistoricoById(id);
    }

    @PostMapping
    public Historico createHistorico(@RequestBody HistoricoDTO historicoDetails) {
        return historicoService.createHistorico(historicoDetails);
    }

    @PutMapping("/{id}")
    public Historico updateHistorico(@PathVariable Long id, @RequestBody HistoricoDTO historicoDetails) {
        return historicoService.updateHistorico(id, historicoDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteHistorico(@PathVariable Long id) {
        historicoService.deleteHistorico(id);
    }
}