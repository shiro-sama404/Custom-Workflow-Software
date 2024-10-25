package com.WorkFlowManager.project.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.WorkFlowManager.project.dto.OrganizacaoDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Organizacao;
import com.WorkFlowManager.project.repository.OrganizacaoRepository;

public class OrganizacaoService {
        private final OrganizacaoRepository organizacaoRepository;

    public OrganizacaoService(OrganizacaoRepository organizacaoRepository) {
        this.organizacaoRepository = organizacaoRepository;
    }

    public List<Organizacao> getAllOrganizacoes() {
        return organizacaoRepository.findAll();
    }

    public Organizacao getOrganizacaoById(@PathVariable Long id) throws ResourceNotFoundException {
        return organizacaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("organizacao não encontrada com id: " + id));
    }

    public Organizacao createOrganizacao(@RequestBody Organizacao organizacao) {
        return organizacaoRepository.save(organizacao);
    }

    public Organizacao updateOrganizacao(@PathVariable Long id, @RequestBody OrganizacaoDTO organizacaoDetails) {

        Organizacao organizacao = organizacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("organizacao não encontrada com id: " + id));
        
        organizacao.setId(organizacaoDetails.id());
        organizacao.setNome(organizacaoDetails.nome());
        organizacao.setIdSubOrganizacoes(organizacaoDetails.idSubOrganizacoes());
        organizacao.setAbreviacao(organizacaoDetails.abreviacao());

        return organizacaoRepository.save(organizacao);
    }

    public void deleteOrganizacao(@PathVariable Long id) {
        organizacaoRepository.deleteById(id);
    }
}
