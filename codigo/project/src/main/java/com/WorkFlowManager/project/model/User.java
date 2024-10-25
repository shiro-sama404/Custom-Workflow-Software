package com.WorkFlowManager.project.model;

import java.util.Set;
import java.util.stream.Collectors;

import com.WorkFlowManager.project.enums.Role;

import java.time.LocalDateTime;

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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idOrganizacao;
    private String username;
    private String passwordHash;
    private String salt;  // Salt para a senha
    private String nomeCompleto;
    private String email;
    private int telefone;
    private int tentativasFalhasLogin;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimoLogin;
    private Boolean contaAtiva;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "militar_id")
    private Militar militar;

    public Set<Role> getAuthorities() {
        return roles.stream().collect(Collectors.toSet());
    }
}