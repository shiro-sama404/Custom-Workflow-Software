package com.WorkFlowManager.project.service;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.WorkFlowManager.project.dto.PreviaDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Previa;
import com.WorkFlowManager.project.repository.PreviaRepository;

public class PreviaService {
    
    private final PreviaRepository previaRepository;

    public PreviaService(PreviaRepository previaRepository) {
        this.previaRepository = previaRepository;
    }

    public Optional<Previa> getRascunho(long userID, Long escalaId) {
        return previaRepository.findByEscalaIdAndUsuarioIdAndRascunho(userID, escalaId, true);
    }

    public Previa getPreviaById(@PathVariable Long id) throws ResourceNotFoundException {
        return previaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("previa não encontrada com id: " + id));
    }

    public Previa createPrevia(@RequestBody Previa previa) {
        return previaRepository.save(previa);
    }

    public Previa updatePrevia(@PathVariable Long id, @RequestBody PreviaDTO previaDetails) {

        Previa previa = previaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("previa não encontrada com id: " + id));
        
        previa.setId(previaDetails.id());
        previa.setIdMilitar(previaDetails.idMilitar());
        previa.setIdEscala(previaDetails.idEscala());
        previa.setIdUser(previaDetails.idUser());
        previa.setDataInicio(previaDetails.dataInicio());
        previa.setDataFim(previaDetails.dataFim());
        previa.setRascunho(previaDetails.rascunho());
        previa.setConfirmada(previaDetails.confirmada());

        return previaRepository.save(previa);
    }

    public void deletePrevia(@PathVariable Long id) {
        previaRepository.deleteById(id);
    }
}