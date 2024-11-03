package com.WorkFlowManager.project.model;

import com.WorkFlowManager.project.enums.Role;

import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "militar_id")
    private Militar militar;

    @ManyToOne
    @JoinColumn(name = "organizacao_id")
    private Organizacao organizacao;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    private String        username;
    private String        passwordHash;
    private String        email;
    private int           telefone;
    private int           tentativasFalhasLogin;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimoLogin;
    private boolean       ativo;

    public Set<Role> getAuthorities() {
        return roles
            .stream()
            .collect(Collectors.toSet());
    }
}