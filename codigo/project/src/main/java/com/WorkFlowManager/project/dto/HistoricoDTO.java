package com.WorkFlowManager.project.dto;

import java.time.LocalDate;

public record HistoricoDTO
(
    Long      id,
    Long      idMilitar,
    Long      idEscala,
    LocalDate dataInicio,
    LocalDate dataFim
) {}