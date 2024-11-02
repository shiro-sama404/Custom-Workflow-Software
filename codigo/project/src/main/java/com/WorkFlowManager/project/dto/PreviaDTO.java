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

    public static PreviaDTO publicDTO(Previa previa){

        PreviaDTO previaDetails = PreviaDTO.builder()
            .id        (previa.getId()             )
            .idEscala  (previa.getEscala().getId() )
            .idMilitar (previa.getMilitar().getId())
            .idUsuario (previa.getUsuario().getId())
            .dataInicio(previa.getDataInicio()     )
            .dataFim   (previa.getDataFim()        )
            .status    (previa.getStatus()         )
            .build();

        return previaDetails;
    }

    public static PreviaDTO publicDTO(Long id, Long idEscala, Long idMilitar, Long idUsuario, LocalDate dataInicio, LocalDate dataFim, PreviaStatus status){

        PreviaDTO previaDetails = PreviaDTO.builder()
            .id        (id        )
            .idEscala  (idEscala  )
            .idMilitar (idMilitar )
            .idUsuario (idUsuario )
            .dataInicio(dataInicio)
            .dataFim   (dataFim   )
            .status    (status    )
            .build();

        return previaDetails;
    }
}