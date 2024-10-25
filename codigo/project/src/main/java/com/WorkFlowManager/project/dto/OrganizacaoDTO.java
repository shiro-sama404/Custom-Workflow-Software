package com.WorkFlowManager.project.dto;

public record OrganizacaoDTO
(
    Long id,
    Long[] idSubOrganizacoes,
    String nome,
    String abreviacao
) {}