package com.WorkFlowManager.project.dto;

public record EscalaDTO
(
    int     id,
    String  nome,
    int     grupo,
    int     idOrganizacao,
    boolean bloqueioTotal,
    int[]   idEscalasBloqueadas,
    int     intervaloDias,
    int     duracaoDias,
    int     efetivo,
    int[]   idEfetivo,
    boolean ativa
) {}