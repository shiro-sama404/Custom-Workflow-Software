package com.WorkFlowManager.project.dto;

import java.time.LocalDate;

public record PreviaDTO(
    Long      id,
    Long      idMilitar,
    Long      idEscala,
    Long      idUser,
    LocalDate dataInicio,
    LocalDate dataFim,
    boolean   rascunho,
    boolean   confirmada
) {}