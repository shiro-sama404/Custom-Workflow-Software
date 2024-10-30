package com.WorkFlowManager.project.repository;

import com.WorkFlowManager.project.model.Organizacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizacaoRepository extends JpaRepository<Organizacao, Long> {
    List<Organizacao> findByOrganizacaoSuperior(Organizacao organizacaoSuperior);
}