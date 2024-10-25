package com.WorkFlowManager.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.WorkFlowManager.project.dto.EscalaDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Escala;
import com.WorkFlowManager.project.repository.EscalaRepository;

@Service
public class EscalaService {
    
    private final EscalaRepository escalaRepository;

    public EscalaService(EscalaRepository escalaRepository) {
        this.escalaRepository = escalaRepository;
    }

    public List<Escala> getAllEscalas() {
        return escalaRepository.findAll();
    }

    public Escala getEscalaById(@PathVariable Long id) throws ResourceNotFoundException {
        return escalaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Escala não encontrada com id: " + id));
    }

    public Escala createEscala(@RequestBody Escala escala) {
        return escalaRepository.save(escala);
    }

    public Escala updateEscala(@PathVariable Long id, @RequestBody EscalaDTO escalaDetails) {

        Escala escala = escalaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Escala não encontrada com id: " + id));
        
        escala.setId(escalaDetails.id());
        escala.setNome(escalaDetails.nome());
        escala.setGrupo(escalaDetails.grupo());
        escala.setIdOrganizacao(escalaDetails.idOrganizacao());
        escala.setBloqueioTotal(escalaDetails.bloqueioTotal());
        escala.setIdEscalasBloqueadas(escalaDetails.idEscalasBloqueadas());
        escala.setIntervaloDias(escalaDetails.intervaloDias());
        escala.setDuracaoDias(escalaDetails.duracaoDias());
        escala.setEfetivo(escalaDetails.efetivo());
        escala.setPretaVermelha(escalaDetails.pretaVermelha());
        escala.setIdEfetivo(escalaDetails.idEfetivo());
        escala.setAtiva(escalaDetails.ativa());

        return escalaRepository.save(escala);
    }

    public void deleteEscala(@PathVariable Long id) {
        escalaRepository.deleteById(id);
    }
}