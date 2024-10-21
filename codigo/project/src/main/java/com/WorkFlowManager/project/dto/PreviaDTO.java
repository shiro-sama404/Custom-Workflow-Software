package com.WorkFlowManager.project.dto;

import java.time.LocalDate;

public record PreviaDTO(
    int       idMilitar,
    int       idEscala,
    LocalDate dataInicio,
    LocalDate dataFim
) {}