package com.WorkFlowManager.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorkFlowManager.project.model.Restricao;

@Repository
public interface RestricaoRepository extends JpaRepository<Restricao, Long> {
    
}