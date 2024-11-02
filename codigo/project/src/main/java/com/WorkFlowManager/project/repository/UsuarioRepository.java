package com.WorkFlowManager.project.repository;

import com.WorkFlowManager.project.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByUsername(String username);
    public Optional<Usuario> findByEmail(String email);
}