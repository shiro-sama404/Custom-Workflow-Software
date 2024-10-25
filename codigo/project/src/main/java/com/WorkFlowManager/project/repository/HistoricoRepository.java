package com.WorkFlowManager.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorkFlowManager.project.model.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    
}
