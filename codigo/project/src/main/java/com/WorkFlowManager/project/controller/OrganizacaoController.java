package com.WorkFlowManager.project.controller;

import com.WorkFlowManager.project.dto.OrganizacaoDTO;
import com.WorkFlowManager.project.model.Organizacao;
import com.WorkFlowManager.project.service.OrganizacaoService;

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
@RequestMapping("/organizacoes")
public class OrganizacaoController {

    private final OrganizacaoService organizacaoService;

    public OrganizacaoController(OrganizacaoService organizacaoService) {
        this.organizacaoService = organizacaoService;
    }

    @GetMapping
    public List<Organizacao> getAllOrganizacoes() {
        return organizacaoService.getAllOrganizacoes();
    }

    @GetMapping("/{id}")
    public Organizacao getOrganizacaoById(@PathVariable Long id) {
        return organizacaoService.getOrganizacaoById(id);
    }

    @PostMapping
    public Organizacao createOrganizacao(@RequestBody OrganizacaoDTO organizacaoDTO) {
        return organizacaoService.createOrganizacao(organizacaoDTO);
    }

    @PutMapping("/{id}")
    public Organizacao updateOrganizacao(@PathVariable Long id, @RequestBody OrganizacaoDTO organizacaoDTO) {
        return organizacaoService.updateOrganizacao(id, organizacaoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrganizacao(@PathVariable Long id) {
        organizacaoService.deleteOrganizacao(id);
    }
}