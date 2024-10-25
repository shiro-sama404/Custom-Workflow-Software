package com.WorkFlowManager.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorkFlowManager.project.model.Militar;

@Repository
public interface MilitarRepository extends JpaRepository<Militar, Long> {
    
}
