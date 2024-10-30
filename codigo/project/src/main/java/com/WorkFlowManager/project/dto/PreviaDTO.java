package com.WorkFlowManager.project.dto;

import com.WorkFlowManager.project.enums.PreviaStatus;
import com.WorkFlowManager.project.model.Previa;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record PreviaDTO
(
    Long         id,
    Long         idMilitar,
    Long         idEscala,
    Long         idUsuario,
    LocalDate    dataInicio,
    LocalDate    dataFim,
    PreviaStatus status
) {

    public static PreviaDTO publicDTO(Previa previa, Long idEscala, Long idMilitar, Long idUsuario){

        PreviaDTO previaDetails = PreviaDTO.builder()
            .id(previa.getId())
            .idEscala(idEscala)
            .idMilitar(idMilitar)
            .idUsuario(idUsuario)
            .dataInicio(previa.getDataInicio())
            .dataFim(previa.getDataFim())
            .status(previa.getStatus())
            .build();

        return previaDetails;
    }
}