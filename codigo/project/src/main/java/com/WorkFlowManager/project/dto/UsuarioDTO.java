package com.WorkFlowManager.project.dto;

import com.WorkFlowManager.project.enums.Role;
import com.WorkFlowManager.project.model.Usuario;

import java.util.Set;

import lombok.Builder;

@Builder
public record UsuarioDTO
(
    Long       id,
    Long       idMilitar,
    Long       idOrganizacao,
    Set<Role>  roles,
    String     username,
    String     password,
    String     email,
    int        telefone,
    boolean    ativo
) {

    public static UsuarioDTO publicDTO(Usuario usuario, Long idMilitar, Long idOrganizacao){

        UsuarioDTO usuarioDetails = UsuarioDTO.builder()
            .id           (usuario.getId()      )
            .idMilitar    (idMilitar            )
            .idOrganizacao(idOrganizacao        )
            .roles        (usuario.getRoles()   )
            .username     (usuario.getUsername())
            .ativo        (usuario.isAtivo()    )
            .build();

        return usuarioDetails;
    }

    public static UsuarioDTO privateDTO(Usuario usuario, Long idMilitar, Long idOrganizacao){

        UsuarioDTO usuarioDetails = UsuarioDTO.builder()
            .id           (usuario.getId()      )
            .idMilitar    (idMilitar            )
            .idOrganizacao(idOrganizacao        )
            .roles        (usuario.getRoles()   )
            .username     (usuario.getUsername())
            .email        (usuario.getEmail()   )
            .telefone     (usuario.getTelefone())
            .ativo        (usuario.isAtivo()    )
            .build();

        return usuarioDetails;
    }
}