package com.WorkFlowManager.project.controller;

import com.WorkFlowManager.project.dto.MilitarDTO;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.model.Organizacao;
import com.WorkFlowManager.project.service.MilitarService;
import com.WorkFlowManager.project.service.OrganizacaoService;

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
@RequestMapping("/militares")
public class MilitarController {

    private final MilitarService militarService;
    private final OrganizacaoService organizacaoService;

    public MilitarController(MilitarService militarService, OrganizacaoService organizacaoService) {
        this.militarService = militarService;
        this.organizacaoService = organizacaoService;
    }

    @GetMapping
    public List<Militar> getAllMilitares() {
        return militarService.getAllMilitares();
    }

    @GetMapping("/{id}")
    public Militar getMilitarById(@PathVariable Long id) {
        return militarService.getMilitarById(id);
    }

    @PostMapping
    public Militar createMilitar(@RequestBody MilitarDTO militarDetails) {
        
        Organizacao organizacao = organizacaoService.getOrganizacaoById(militarDetails.idOrganizacao());

        return militarService.createMilitar(militarDetails, organizacao);
    }

    @PutMapping("/{id}")
    public Militar updateMilitar(@PathVariable Long id, @RequestBody MilitarDTO militarDetails) {

        Organizacao organizacao = organizacaoService.getOrganizacaoById(militarDetails.idOrganizacao());

        return militarService.updateMilitar(id, militarDetails, organizacao);
    }

    @DeleteMapping("/{id}")
    public void deleteMilitar(@PathVariable Long id) {
        militarService.deleteMilitar(id);
    }
}