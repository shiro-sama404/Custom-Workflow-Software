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
public class Previa {
    private int       idMilitar;
    private int       idEscala;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
