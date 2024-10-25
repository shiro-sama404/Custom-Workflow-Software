package com.WorkFlowManager.project.dto;

import java.time.LocalDateTime;

import com.WorkFlowManager.project.model.Militar;

public record UserDTO(
    Long id,
    Long idOrganizacao,
    String username,
    String passwordHash,
    String salt,
    String nomeCompleto,
    String email,
    int telefone,
    int tentativasFalhasLogin,
    LocalDateTime dataCriacao,
    LocalDateTime dataUltimoLogin,
    Boolean contaAtiva,
    Militar militar
) {}