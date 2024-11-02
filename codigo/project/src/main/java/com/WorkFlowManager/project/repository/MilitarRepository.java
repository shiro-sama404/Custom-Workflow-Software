package com.WorkFlowManager.project.repository;

import com.WorkFlowManager.project.model.Militar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilitarRepository extends JpaRepository<Militar, Long> {
    
}