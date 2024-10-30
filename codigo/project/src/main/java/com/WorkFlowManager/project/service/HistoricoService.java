package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.model.Escala;
import com.WorkFlowManager.project.model.Historico;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.dto.HistoricoDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.repository.HistoricoRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
        return historicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("historico não encontrada. id: " + id));
    }

    public Historico createHistorico(@RequestBody HistoricoDTO historicoDetails, Escala escala, Militar militar) {
        Historico novoHistorico = Historico.builder()
            .escala    (escala                       )
            .militar   (militar                      )
            .dataInicio(historicoDetails.dataInicio())
            .dataFim   (historicoDetails.dataFim()   )
            .build();

        return historicoRepository.save(novoHistorico);
    }

    public Historico updateHistorico(@PathVariable Long id, @RequestBody HistoricoDTO historicoDetails, Escala escala, Militar militar) {

        Historico historico = historicoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("historico não encontrada. id: " + id));

        historico.setEscala(escala);
        historico.setMilitar(militar);
        historico.setDataInicio(historicoDetails.dataInicio());
        historico.setDataFim(historicoDetails.dataFim());

        return historicoRepository.save(historico);
    }

    public void deleteHistorico(@PathVariable Long id) {
        historicoRepository.deleteById(id);
    }
}