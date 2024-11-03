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
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "organizacao_id")
    private Organizacao organizacao;

    @Enumerated(EnumType.STRING)
    private CategoriaHabilitacao habilitacao;
    
    @Enumerated(EnumType.STRING)
    private PostoGraduacao postoGraduacao;

    @Enumerated(EnumType.STRING)
    private Qas qas;

    private String               identidade;
    private String               nomeCompleto;
    private String               nomeGuerra;
    private LocalDate            dataPromocao;
    private LocalDate            dataPraca;
    private LocalDate            dataNascimento;
    private int                  antiguidade;
    private boolean              cfcCasCao;
    private boolean              ativo;
}