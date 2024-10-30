package com.WorkFlowManager.project.controller;

import com.WorkFlowManager.project.dto.HistoricoDTO;
import com.WorkFlowManager.project.model.Escala;
import com.WorkFlowManager.project.model.Historico;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.service.EscalaService;
import com.WorkFlowManager.project.service.HistoricoService;
import com.WorkFlowManager.project.service.MilitarService;

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
    private final EscalaService    escalaService;
    private final MilitarService   militarService;

    public HistoricoController(HistoricoService historicoService, EscalaService escalaService, MilitarService militarService) {
        this.historicoService = historicoService;
        this.escalaService    = escalaService;
        this.militarService   = militarService;
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

        Escala escala   = escalaService.getEscalaById(historicoDetails.idEscala());
        Militar militar = militarService.getMilitarById(historicoDetails.idMilitar());

        return historicoService.createHistorico(historicoDetails, escala, militar);
    }

    @PutMapping("/{id}")
    public Historico updateHistorico(@PathVariable Long id, @RequestBody HistoricoDTO historicoDetails) {
        
        Escala escala   = escalaService.getEscalaById(historicoDetails.idEscala());
        Militar militar = militarService.getMilitarById(historicoDetails.idMilitar());

        return historicoService.updateHistorico(id, historicoDetails, escala, militar);
    }

    @DeleteMapping("/{id}")
    public void deleteHistorico(@PathVariable Long id) {
        historicoService.deleteHistorico(id);
    }
}