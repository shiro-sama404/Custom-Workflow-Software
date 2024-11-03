package com.WorkFlowManager.project.repository;

import com.WorkFlowManager.project.enums.PreviaStatus;
import com.WorkFlowManager.project.model.Previa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PreviaRepository extends JpaRepository<Previa, Long>, JpaSpecificationExecutor<Previa> {
    
    List<Previa> findByEscalaIdAndUsuarioIdAndStatus(Long escalaId, Long usuarioId, PreviaStatus status);
    List<Previa> findByEscalaId(Long escalaId);
}