package com.WorkFlowManager.project.dto;

import com.WorkFlowManager.project.model.Historico;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record HistoricoDTO
(
    Long      id,
    Long      idEscala,
    Long      idMilitar,
    LocalDate dataInicio,
    LocalDate dataFim
) {

    public static HistoricoDTO publicDTO(Historico historico, Long idEscala, Long idMilitar){
        HistoricoDTO historicoDetails = HistoricoDTO.builder()
            .id        (historico.getId()        )
            .idEscala  (idEscala                 )
            .idMilitar (idMilitar                )
            .dataInicio(historico.getDataInicio())
            .dataFim   (historico.getDataFim()   )
            .build();

        return historicoDetails;
    }
}