package com.WorkFlowManager.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorkFlowManager.project.model.Escala;

@Repository
public interface EscalaRepository extends JpaRepository<Escala, Long> {
    
}