package com.WorkFlowManager.project.dto;

import com.WorkFlowManager.project.enums.PreviaStatus;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

import lombok.Builder;

@Builder
public record PreviaFilterDTO
(
    Long[]       idMilitar,
    Long[]       idEscala,
    @NotNull
    LocalDate    dataInicio,
    @NotNull
    LocalDate    dataFim,
    PreviaStatus status
) {

    public static PreviaFilterDTO publicDTO(Long[] idEscala, Long[] idMilitar, Long idUsuario, LocalDate dataInicio, LocalDate dataFim, PreviaStatus status){

        PreviaFilterDTO previaFilterDetails = PreviaFilterDTO.builder()
            .idEscala  (idEscala  )
            .idMilitar (idMilitar )
            .dataInicio(dataInicio)
            .dataFim   (dataFim   )
            .status    (status    )
            .build();

        return previaFilterDetails;
    }

    public static boolean validate(PreviaFilterDTO filter){

        if(filter.dataInicio().isAfter(filter.dataFim())){
            return false;
        }

        if((filter.idEscala == null || filter.idEscala.length == 0) && (filter.idMilitar == null || filter.idMilitar.length == 0)){
            return false;
        }

        return true;
    }
}
