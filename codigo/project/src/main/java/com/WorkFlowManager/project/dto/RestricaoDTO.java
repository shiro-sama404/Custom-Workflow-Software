package com.WorkFlowManager.project.dto;

import com.WorkFlowManager.project.model.Restricao;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record RestricaoDTO
(
    Long      id,
    Long      idMilitar,
    Long      idUsuarioAutor,
    Long[]    idsEscalasBloqueadas,
    String    razao,
    String    detalhes,
    LocalDate dataInicio,
    LocalDate dataFim,
    boolean   restricaoTotal,
    boolean   ativa
) {

    public static RestricaoDTO publicDTO(Restricao restricao, Long[] idsEscalasBloqueadas, Long idMilitar, Long idUsuario){

        RestricaoDTO restricaoDetails = RestricaoDTO.builder()
            .id                  (restricao.getId()           )
            .idsEscalasBloqueadas(idsEscalasBloqueadas        )
            .idMilitar           (idMilitar                   )
            .razao               (restricao.getRazao()        )
            .dataInicio          (restricao.getDataInicio()   )
            .dataFim             (restricao.getDataFim()      )
            .restricaoTotal      (restricao.isRestricaoTotal())
            .build();

        return restricaoDetails;
    }

    public static RestricaoDTO privateDTO(Restricao restricao, Long[] idsEscalasBloqueadas, Long idMilitar, Long idUsuarioAutor){

        RestricaoDTO restricaoDetails = RestricaoDTO.builder()
            .id                  (restricao.getId()           )
            .idsEscalasBloqueadas(idsEscalasBloqueadas        )
            .idMilitar           (idMilitar                   )
            .idUsuarioAutor      (idUsuarioAutor              )
            .razao               (restricao.getRazao()        )
            .detalhes            (restricao.getDetalhes()     )
            .dataInicio          (restricao.getDataInicio()   )
            .dataFim             (restricao.getDataFim()      )
            .restricaoTotal      (restricao.isRestricaoTotal())
            .ativa               (restricao.isAtiva()         )
            .build();

        return restricaoDetails;
    }
}