package com.WorkFlowManager.project.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.WorkFlowManager.project.dto.RestricaoDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Restricao;
import com.WorkFlowManager.project.repository.RestricaoRepository;

public class RestricaoService {
        private final RestricaoRepository restricaoRepository;

    public RestricaoService(RestricaoRepository restricaoRepository){
        this.restricaoRepository = restricaoRepository;
    }

    public List<Restricao> getAllrestricoes() {
        return restricaoRepository.findAll();
    }

    public Restricao getRestricaoById(@PathVariable Long id) throws ResourceNotFoundException {
        return restricaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("restricao não encontrada com id: " + id));
    }

    public Restricao createRestricao(@RequestBody Restricao restricao) {
        return restricaoRepository.save(restricao);
    }

    public Restricao updateRestricao(@PathVariable Long id, @RequestBody RestricaoDTO restricaoDetails) {

        Restricao restricao = restricaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("restricao não encontrada com id: " + id));
        
        restricao.setId(restricaoDetails.id());
        restricao.setIdMilitar(restricaoDetails.idMilitar());
        restricao.setIdUser(restricaoDetails.idUser());
        restricao.setIdEscalasBloqueadas(restricaoDetails.idEscalasBloqueadas());
        restricao.setRazao(restricaoDetails.razao());
        restricao.setDetalhes(restricaoDetails.detalhes());
        restricao.setDataInicio(restricaoDetails.dataInicio());
        restricao.setDataFim(restricaoDetails.dataFim());
        restricao.setRestricaoTotal(restricaoDetails.restricaoTotal());

        return restricaoRepository.save(restricao);
    }

    public void deleteRestricao(@PathVariable Long id) {
        restricaoRepository.deleteById(id);
    }
}
