package com.WorkFlowManager.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.WorkFlowManager.project.dto.MilitarDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.repository.MilitarRepository;

@Service
public class MilitarService {
        private final MilitarRepository militarRepository;

    public MilitarService(MilitarRepository militarRepository) {
        this.militarRepository = militarRepository;
    }

    public List<Militar> getAllMilitares() {
        return militarRepository.findAll();
    }

    public Militar getMilitarById(@PathVariable Long id) throws ResourceNotFoundException {
        return militarRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("militar não encontrada com id: " + id));
    }

    public Militar createMilitar(@RequestBody Militar militar) {
        return militarRepository.save(militar);
    }

    public Militar updateMilitar(@PathVariable Long id, @RequestBody MilitarDTO militarDetails) {

        Militar militar = militarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("militar não encontrada com id: " + id));
        
        militar.setId(militarDetails.id());
        militar.setIdentidade(militarDetails.identidade());
        militar.setNomeCompleto(militarDetails.nomeComplemento());
        militar.setNomeGuerra(militarDetails.nomeGuerra());
        militar.setPostoGraduacao(militarDetails.postoGraduacao());
        militar.setQas(militarDetails.qas());
        militar.setHabilitacao(militarDetails.habilitacao());
        militar.setAntiguidade(militarDetails.antiguidade());
        militar.setIdOrganizacao(militarDetails.idOrganizacao());
        militar.setDataNascimento(militarDetails.dataNascimento());
        militar.setDataPraca(militarDetails.dataPraca());
        militar.setDataPromocao(militarDetails.dataPromocao());
        militar.setCfcCasCao(militarDetails.cfcCasCao());
        militar.setAtivo(militarDetails.ativo());

        return militarRepository.save(militar);
    }

    public void deleteMilitar(@PathVariable Long id) {
        militarRepository.deleteById(id);
    }
}