package com.WorkFlowManager.project.repository;

import com.WorkFlowManager.project.model.Escala;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscalaRepository extends JpaRepository<Escala, Long> {
    
}