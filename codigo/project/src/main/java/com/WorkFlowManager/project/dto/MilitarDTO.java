package com.WorkFlowManager.project.dto;

import java.time.LocalDate;

public record MilitarDTO
(
    int       id,
    String    identidade,
    String    nomeComplemento,
    LocalDate dataNascimento,
    String    habilitacao,
    int       antiguidade,
    int       idOrganizacao,
    boolean   ativo,
    String    nomeGuerra,
    String    postoGraduacao,
    String    qas,
    LocalDate dataPraca,
    LocalDate dataPromocao,
    boolean   cfcCasCao
) {}