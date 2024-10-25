package com.WorkFlowManager.project.model;

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
public class Escala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    private String  nome;
    private Long    idOrganizacao;
    private Long[]  idEscalasBloqueadas;
    private Long[]  idEfetivo;
    private int     grupo;
    private int     intervaloDias;
    private int     duracaoDias;
    private int     efetivo;
    private boolean pretaVermelha;
    private boolean bloqueioTotal;
    private boolean ativa;
}