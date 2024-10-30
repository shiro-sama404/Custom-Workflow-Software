package com.WorkFlowManager.project.controller;

import com.WorkFlowManager.project.dto.EscalaDTO;
import com.WorkFlowManager.project.model.Escala;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.model.Organizacao;
import com.WorkFlowManager.project.service.EscalaService;
import com.WorkFlowManager.project.service.MilitarService;
import com.WorkFlowManager.project.service.OrganizacaoService;

import java.util.List;
import java.util.Set;

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
    private final MilitarService militarService;
    private final OrganizacaoService organizacaoService;

    public EscalaController(EscalaService escalaService, MilitarService militarService, OrganizacaoService organizacaoService) {
        this.escalaService = escalaService;
        this.militarService = militarService;
        this.organizacaoService = organizacaoService;
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
        Set<Militar> militares = militarService.getMilitaresById(escalaDetails.idMilitares());
        Organizacao organizacao = organizacaoService.getOrganizacaoById(escalaDetails.idOrganizacao());
        return escalaService.createEscala(escalaDetails, militares, organizacao);
    }

    @PutMapping("/{id}")
    public Escala updateEscala(@PathVariable Long id, @RequestBody EscalaDTO escalaDetails) {
        Set<Militar> militares = militarService.getMilitaresById(escalaDetails.idMilitares());
        Organizacao organizacao = organizacaoService.getOrganizacaoById(escalaDetails.idOrganizacao());
        return escalaService.updateEscala(id, escalaDetails, militares, organizacao);
    }

    @DeleteMapping("/{id}")
    public void deleteEscala(@PathVariable Long id) {
        escalaService.deleteEscala(id);
    }
}