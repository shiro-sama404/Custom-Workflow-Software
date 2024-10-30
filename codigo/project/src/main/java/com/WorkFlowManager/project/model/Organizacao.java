package com.WorkFlowManager.project.model;

import java.util.Set;
import java.util.HashSet;

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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Organizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organizacao_superior_id")
    private Organizacao organizacaoSuperior;

    @Builder.Default
    @ManyToMany
    @JoinTable(
        name = "hierarquia",
        joinColumns = @JoinColumn(name = "organizacao_id"),
        inverseJoinColumns = @JoinColumn(name = "suborganizacao_id")
    )
    private Set<Organizacao> subOrganizacoes = new HashSet<>();

    private String nome;
    private String abreviacao;
}