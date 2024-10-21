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
public class Restricao {
    private int     idMilitar;
    private boolean restricaoTotal;
    private int[]   idEscalasBloqueadas;
    private String  detalhes;

    public LocalDate dataInicio;
    public LocalDate dataFim;
    public String    razao;
}