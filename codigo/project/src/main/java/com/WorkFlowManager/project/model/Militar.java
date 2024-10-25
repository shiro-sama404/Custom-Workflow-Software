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
public class Militar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long      id;
    private String    identidade;
    private String    nomeCompleto;
    private String    nomeGuerra;
    private String    postoGraduacao;
    private String    qas;
    private String    habilitacao;
    private int       antiguidade;
    private int       idOrganizacao;
    private LocalDate dataNascimento;
    private LocalDate dataPraca;
    private LocalDate dataPromocao;
    private boolean   cfcCasCao;
    private boolean   ativo;
}