package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.model.Escala;
import com.WorkFlowManager.project.model.Historico;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.dto.HistoricoDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.repository.EscalaRepository;
import com.WorkFlowManager.project.repository.HistoricoRepository;
import com.WorkFlowManager.project.repository.MilitarRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class HistoricoService {

    private final HistoricoRepository historicoRepository;
    private final EscalaRepository    escalaRepository;
    private final MilitarRepository   militarRepository;

    public HistoricoService(HistoricoRepository historicoRepository, EscalaRepository    escalaRepository, MilitarRepository   militarRepository) {
        this.historicoRepository = historicoRepository;
        this.escalaRepository    = escalaRepository;
        this.militarRepository   = militarRepository;
    }

    public List<Historico> getAllHistoricos() {
        return historicoRepository.findAll();
    }

    public Historico getHistoricoById(@PathVariable Long id) throws ResourceNotFoundException {
        return historicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Historico não encontrada. id: " + id));
    }

    public Historico createHistorico(@RequestBody HistoricoDTO historicoDetails) {

        Escala escala = escalaRepository.findById(historicoDetails.idEscala())
            .orElseThrow(() -> new ResourceNotFoundException("Escala não encontrada. id: " + historicoDetails.idEscala()));
        
        Militar militar = militarRepository.findById(historicoDetails.idMilitar())
            .orElseThrow(() -> new ResourceNotFoundException("Militar não encontrado. id: " + historicoDetails.idMilitar()));

        Historico novoHistorico = Historico.builder()
            .escala    (escala                       )
            .militar   (militar                      )
            .dataInicio(historicoDetails.dataInicio())
            .dataFim   (historicoDetails.dataFim()   )
            .build();

        return historicoRepository.save(novoHistorico);
    }

    public Historico updateHistorico(@PathVariable Long id, @RequestBody HistoricoDTO historicoDetails) {

        Historico historico = historicoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Historico não encontrada. id: " + id));
        
        Escala escala = escalaRepository.findById(historicoDetails.idEscala())
            .orElseThrow(() -> new ResourceNotFoundException("Escala não encontrada. id: " + historicoDetails.idEscala()));
        
        Militar militar = militarRepository.findById(historicoDetails.idMilitar())
            .orElseThrow(() -> new ResourceNotFoundException("Militar não encontrado. id: " + historicoDetails.idMilitar()));

        historico.setEscala    (escala                       );
        historico.setMilitar   (militar                      );
        historico.setDataInicio(historicoDetails.dataInicio());
        historico.setDataFim   (historicoDetails.dataFim()   );

        return historicoRepository.save(historico);
    }

    public void deleteHistorico(@PathVariable Long id) {
        historicoRepository.deleteById(id);
    }
}