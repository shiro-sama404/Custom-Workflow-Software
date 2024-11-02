package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.dto.OrganizacaoDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Organizacao;
import com.WorkFlowManager.project.repository.OrganizacaoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class OrganizacaoService {
        
    private final OrganizacaoRepository organizacaoRepository;

    public OrganizacaoService(OrganizacaoRepository organizacaoRepository) {
        this.organizacaoRepository = organizacaoRepository;
    }

    public List<Organizacao> getAllOrganizacoes() {
        return organizacaoRepository.findAll();
    }

    public Organizacao getOrganizacaoById(@PathVariable Long id) throws ResourceNotFoundException {
        return organizacaoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("organizacao não encontrada. id: " + id));
    }

    public Organizacao createOrganizacao(@RequestBody OrganizacaoDTO organizacaoDetails) {
        
        Set<Organizacao> subOrganizacoes = new HashSet<>(organizacaoRepository.findAllById(Arrays.asList(organizacaoDetails.idsSubOrganizacoes())));
        
        Organizacao novaOrganizacao = Organizacao.builder()
            .subOrganizacoes(subOrganizacoes                )
            .nome           (organizacaoDetails.nome()      )
            .abreviacao     (organizacaoDetails.abreviacao())
            .build();

        return organizacaoRepository.save(novaOrganizacao);
    }

    public Organizacao updateOrganizacao(@PathVariable Long id, @RequestBody OrganizacaoDTO organizacaoDetails) {

        Organizacao organizacao = organizacaoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("organizacao não encontrada. id: " + id));

        Organizacao organizacaoSuperior = null;
        
        if(organizacaoDetails.idOrganizacaoSuperior() != null){
            organizacaoSuperior = organizacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("organizacao não encontrada. id: " + id));
        }

        List<Organizacao> subOrganizacoesAtuais = organizacaoRepository.findByOrganizacaoSuperior(organizacao);
        Set<Organizacao> novasSubOrganizacoes = new HashSet<>(organizacaoRepository.findAllById(Arrays.asList(organizacaoDetails.idsSubOrganizacoes())));

        for(Organizacao subOrganizacao : subOrganizacoesAtuais){
            if(!novasSubOrganizacoes.contains(subOrganizacao)){
                subOrganizacao.setOrganizacaoSuperior(null);
                organizacaoRepository.save(subOrganizacao);
            }
        }
        
        organizacao.setOrganizacaoSuperior(organizacaoSuperior            );
        organizacao.setSubOrganizacoes    (novasSubOrganizacoes           );
        organizacao.setNome               (organizacaoDetails.nome()      );
        organizacao.setAbreviacao         (organizacaoDetails.abreviacao());

        return organizacaoRepository.save(organizacao);
    }

    public void deleteOrganizacao(@PathVariable Long id) {
        organizacaoRepository.deleteById(id);
    }

    public Set<Organizacao> getAllSubOrganizacoes(Organizacao organizacaoSuperior) {

        Set<Organizacao> subOrganizacoes = new HashSet<>();
        List<Organizacao> subOrganizacoesDiretas = organizacaoRepository.findByOrganizacaoSuperior(organizacaoSuperior);

        for (Organizacao subOrganizacao : subOrganizacoesDiretas) {
            subOrganizacoes.add(subOrganizacao);
            subOrganizacoes.addAll(getAllSubOrganizacoes(subOrganizacao)); // Recursão para subníveis
        }

        return subOrganizacoes;
    }
}