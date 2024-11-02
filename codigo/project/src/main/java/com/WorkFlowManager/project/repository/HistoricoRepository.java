package com.WorkFlowManager.project.repository;

import com.WorkFlowManager.project.model.Historico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    
}