package com.WorkFlowManager.project.dto;

import com.WorkFlowManager.project.model.Organizacao;

import lombok.Builder;

@Builder
public record OrganizacaoDTO
(
    Long   id,
    Long   idOrganizacaoSuperior,
    Long[] idsSubOrganizacoes,
    String nome,
    String abreviacao
) {

    public static OrganizacaoDTO publicDTO(Organizacao organizacao, Long idOrganizacaoSuperior, Long[] idsSubOrganizacoes){

        OrganizacaoDTO organizacaoDetails = OrganizacaoDTO.builder()
            .id(organizacao.getId())
            .idOrganizacaoSuperior(idOrganizacaoSuperior)
            .idsSubOrganizacoes(idsSubOrganizacoes)
            .nome(organizacao.getNome())
            .abreviacao(organizacao.getAbreviacao())
            .build();

        return organizacaoDetails;
    }
}