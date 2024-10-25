package com.WorkFlowManager.project.dto;

public record EscalaDTO
(
    Long    id,
    String  nome,
    Long    idOrganizacao,
    Long[]  idEscalasBloqueadas,
    Long[]  idEfetivo,
    int     efetivo,
    int     grupo,
    int     intervaloDias,
    int     duracaoDias,
    boolean pretaVermelha,
    boolean bloqueioTotal,
    boolean ativa
) {}