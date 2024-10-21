package com.WorkFlowManager.project.dto;

import java.time.LocalDate;

public record HistoricoDTO
(
    int       idMilitar,
    int       idEscala,
    LocalDate dataInicio,
    LocalDate dataFim
) {}