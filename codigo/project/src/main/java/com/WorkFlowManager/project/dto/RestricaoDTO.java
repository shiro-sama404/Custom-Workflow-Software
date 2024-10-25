package com.WorkFlowManager.project.dto;

import java.time.LocalDate;

public record RestricaoDTO
(
    Long      id,
    Long      idMilitar,
    Long      idUser,
    Long[]    idEscalasBloqueadas,
    String    razao,
    String    detalhes,
    LocalDate dataInicio,
    LocalDate dataFim,
    boolean   restricaoTotal
) {}