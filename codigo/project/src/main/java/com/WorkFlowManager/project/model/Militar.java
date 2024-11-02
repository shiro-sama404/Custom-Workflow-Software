package com.WorkFlowManager.project.model;

import com.WorkFlowManager.project.enums.CategoriaHabilitacao;
import com.WorkFlowManager.project.enums.PostoGraduacao;
import com.WorkFlowManager.project.enums.Qas;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Militar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;
    
    @ManyToOne
    @JoinColumn(name = "id_organizacao")
    private Organizacao organizacao;

    private String               identidade;
    private String               nomeCompleto;
    private String               nomeGuerra;
    private PostoGraduacao       postoGraduacao;
    private CategoriaHabilitacao habilitacao;
    private Qas                  qas;
    private LocalDate            dataPromocao;
    private LocalDate            dataPraca;
    private LocalDate            dataNascimento;
    private int                  antiguidade;
    private boolean              cfcCasCao;
    private boolean              ativo;
}