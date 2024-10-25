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
public class Previa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long       id;
    private Long       idMilitar;
    private Long       idEscala;
    private Long       idUser;
    private LocalDate  dataInicio;
    private LocalDate  dataFim;
    private boolean    rascunho;
    private boolean    confirmada;
}