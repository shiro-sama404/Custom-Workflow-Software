package com.WorkFlowManager.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorkFlowManager.project.model.Organizacao;

@Repository
public interface OrganizacaoRepository extends JpaRepository<Organizacao, Long> {
    
}
