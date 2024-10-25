package com.WorkFlowManager.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.WorkFlowManager.project.model.Historico;
import com.WorkFlowManager.project.dto.HistoricoDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.repository.HistoricoRepository;

@Service
public class HistoricoService {

    private final HistoricoRepository historicoRepository;

    public HistoricoService(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    public List<Historico> getAllHistoricos() {
        return historicoRepository.findAll();
    }

    public Historico getHistoricoById(@PathVariable Long id) throws ResourceNotFoundException {
        return historicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("historico não encontrada com id: " + id));
    }

    public Historico createHistorico(@RequestBody Historico historico) {
        return historicoRepository.save(historico);
    }

    public Historico updateHistorico(@PathVariable Long id, @RequestBody HistoricoDTO historicoDetails) {

        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("historico não encontrada com id: " + id));
        
        historico.setId(historicoDetails.id());
        historico.setIdMilitar(historicoDetails.idMilitar());
        historico.setIdEscala(historicoDetails.idEscala());
        historico.setDataInicio(historicoDetails.dataInicio());
        historico.setDataFim(historicoDetails.dataFim());

        return historicoRepository.save(historico);
    }

    public void deleteHistorico(@PathVariable Long id) {
        historicoRepository.deleteById(id);
    }
}