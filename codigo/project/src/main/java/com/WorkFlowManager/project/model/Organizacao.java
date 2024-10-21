package com.WorkFlowManager.project.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Organizacao {
    private int id;
    private int[] idSubOrganizacoes;

    public String nome;
    public String abreviacao;
}
