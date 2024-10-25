package com.WorkFlowManager.project.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Restricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long      id;
    private Long      idMilitar;
    private Long      idUser;
    private Long[]    idEscalasBloqueadas;
    private String    razao;
    private String    detalhes;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean   restricaoTotal;
}