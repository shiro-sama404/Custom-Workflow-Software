package com.WorkFlowManager.project.dto;

import java.time.LocalDate;

public record RestricaoDTO
(
    int     idMilitar,
    boolean restricaoTotal,
    int[]   idEscalasBloqueadas,
    String  detalhes,
    LocalDate dataInicio,
    LocalDate dataFim,
    String    razao
) {}