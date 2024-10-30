package com.WorkFlowManager.project.model;

import java.time.LocalDate;
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
public class Restricao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long      id;

    @Builder.Default
    @ManyToMany
    @JoinTable(
        name = "restricao_escala",
        joinColumns = @JoinColumn(name = "restricao_id"),
        inverseJoinColumns = @JoinColumn(name = "escala_id")
        )
    private Set<Escala> escalasBloqueadas = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name = "militar_id")
    private Militar militar;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioAutor;

    private String    razao;
    private String    detalhes;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean   restricaoTotal;
    private boolean   ativa;
}