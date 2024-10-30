package com.WorkFlowManager.project.model;

import com.WorkFlowManager.project.enums.GrupoEscala;

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
public class Escala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;

    @ManyToOne
    @JoinColumn(name = "organizacao_id")
    private Organizacao organizacao;

    @Builder.Default
    @ManyToMany
    @JoinTable(
        name = "escala_bloqueada",
        joinColumns = @JoinColumn(name = "escala_id"),
        inverseJoinColumns = @JoinColumn(name = "escala_bloqueada_id")
    )
    private Set<Escala> escalasBloqueadas = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(
        name = "escala_militar",
        joinColumns = @JoinColumn(name = "escala_id"),
        inverseJoinColumns = @JoinColumn(name = "militar_id")
    )
    private Set<Militar> militares = new HashSet<>();

    private GrupoEscala grupo;
    
    private String  nome;
    private int     intervaloDias;
    private int     duracaoDias;
    private int     efetivo;
    private boolean pretaVermelha;
    private boolean bloqueioTotal;
    private boolean ativa;
}