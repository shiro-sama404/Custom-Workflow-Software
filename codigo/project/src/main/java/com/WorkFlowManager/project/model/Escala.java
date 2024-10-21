package com.WorkFlowManager.project.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Escala {
    private int     id;
    private String  nome;
    private int     grupo;
    private int     idOrganizacao;
    private boolean bloqueioTotal;
    private int[]   idEscalasBloqueadas;
    private int     intervaloDias;
    private int     duracaoDias;
    private int     efetivo;
    private int[]   idEfetivo;
    private boolean ativa;
}
