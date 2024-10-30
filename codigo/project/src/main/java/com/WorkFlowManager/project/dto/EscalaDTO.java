package com.WorkFlowManager.project.dto;

import com.WorkFlowManager.project.enums.GrupoEscala;
import com.WorkFlowManager.project.model.Escala;

import lombok.Builder;

@Builder
public record EscalaDTO
(
    Long id,
    GrupoEscala    grupo,
    Long           idOrganizacao,
    Long[]         idEscalasBloqueadas,
    Long[]         idMilitares,
    String         nome,
    int            efetivo,
    int            duracaoDias,
    int            intervaloDias,
    boolean        pretaVermelha,
    boolean        bloqueioTotal,
    boolean        ativa
) {

    public static EscalaDTO publicDTO(Escala escala, Long idOrganizacao, Long[] idEscalasBloqueadas, Long[] idMilitares){

        EscalaDTO escalaDetails = EscalaDTO.builder()
            .id                 (escala.getId()           )
            .grupo              (escala.getGrupo()        )
            .idOrganizacao      (idOrganizacao            )
            .idEscalasBloqueadas(idEscalasBloqueadas      )
            .idMilitares        (idMilitares              )
            .nome               (escala.getNome()         )
            .efetivo            (escala.getEfetivo()      )
            .duracaoDias        (escala.getDuracaoDias()  )
            .intervaloDias      (escala.getIntervaloDias())
            .pretaVermelha      (escala.isPretaVermelha() )
            .bloqueioTotal      (escala.isBloqueioTotal() )
            .ativa              (escala.isAtiva()         )
            .build();

        return escalaDetails;
    }
}