package com.WorkFlowManager.project.dto;

public record OrganizacaoDTO
(
    int id,
    int[] idSubOrganizacoes,

    String nome,
    String abreviacao
) {}