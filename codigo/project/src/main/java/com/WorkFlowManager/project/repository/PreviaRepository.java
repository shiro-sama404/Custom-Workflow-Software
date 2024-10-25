package com.WorkFlowManager.project.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorkFlowManager.project.model.Previa;

@Repository
public interface PreviaRepository extends JpaRepository<Previa, Long> {
    
    Optional<Previa> findByEscalaIdAndUsuarioIdAndRascunho(Long escalaId, Long usuarioId, boolean rascunho);
}
