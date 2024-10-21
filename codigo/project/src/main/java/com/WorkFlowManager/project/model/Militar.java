package com.WorkFlowManager.project.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Militar {
    private int       id;
    private String    identidade;
    private String    nomeComplemento;
    private LocalDate dataNascimento;
    private String    habilitacao;
    private int       antiguidade;
    private int       idOrganizacao;
    private boolean   ativo;

    public String    nomeGuerra;
    public String    postoGraduacao;
    public String    qas;
    public LocalDate dataPraca;
    public LocalDate dataPromocao;
    public boolean   cfcCasCao;
}
