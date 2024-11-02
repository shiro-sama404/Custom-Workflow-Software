package com.WorkFlowManager.project.model;

import java.time.LocalDate;

import com.WorkFlowManager.project.enums.PreviaStatus;

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
public class Previa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "escala_id")
    private Escala escala;

    @ManyToOne
    @JoinColumn(name = "militar_id")
    private Militar militar;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    private PreviaStatus   status;
    private LocalDate      dataInicio;
    private LocalDate      dataFim;
}