package com.WorkFlowManager.project.dto;

import com.WorkFlowManager.project.enums.CategoriaHabilitacao;
import com.WorkFlowManager.project.enums.PostoGraduacao;
import com.WorkFlowManager.project.enums.Qas;
import com.WorkFlowManager.project.model.Militar;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record MilitarDTO
(
    Long                 id,
    Long                 idOrganizacao,
    PostoGraduacao       postoGraduacao,
    CategoriaHabilitacao habilitacao,
    Qas                  qas,
    String               identidade,
    String               nomeCompleto,
    String               nomeGuerra,
    LocalDate            dataPromocao,
    LocalDate            dataPraca,
    LocalDate            dataNascimento,
    int                  antiguidade,
    boolean              cfcCasCao,
    boolean              ativo
) {

    public static MilitarDTO publicDTO(Militar militar, Long idOrganizacao){

        MilitarDTO militarDetails = MilitarDTO.builder()
            .id            (militar.getId()            )
            .idOrganizacao (idOrganizacao              )
            .postoGraduacao(militar.getPostoGraduacao())
            .qas           (militar.getQas()           )
            .nomeGuerra    (militar.getNomeGuerra()    )
            .dataPromocao  (militar.getDataPromocao()  )
            .cfcCasCao     (militar.isCfcCasCao()      )
            .build();
        
        return militarDetails;
    }

    public static MilitarDTO privateDTO(Militar militar, Long idOrganizacao){

        MilitarDTO militarDetails = MilitarDTO.builder()
            .id            (militar.getId()            )
            .idOrganizacao (idOrganizacao              )
            .postoGraduacao(militar.getPostoGraduacao())
            .habilitacao   (militar.getHabilitacao()   )
            .qas           (militar.getQas()           )
            .identidade    (militar.getIdentidade()    )
            .nomeCompleto  (militar.getNomeCompleto()  )
            .nomeGuerra    (militar.getNomeGuerra()    )
            .dataPromocao  (militar.getDataPromocao()  )
            .dataPraca     (militar.getDataPraca()     )
            .dataNascimento(militar.getDataNascimento())
            .antiguidade   (militar.getAntiguidade()   )
            .cfcCasCao     (militar.isCfcCasCao()      )
            .ativo         (militar.isAtivo()          )
            .build();
        
        return militarDetails;
    }
}