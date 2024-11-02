package com.WorkFlowManager.project.repository;

import com.WorkFlowManager.project.model.Restricao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestricaoRepository extends JpaRepository<Restricao, Long> {
    
}