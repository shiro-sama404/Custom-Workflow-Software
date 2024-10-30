package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.dto.RestricaoDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Escala;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.model.Restricao;
import com.WorkFlowManager.project.model.Usuario;
import com.WorkFlowManager.project.repository.RestricaoRepository;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RestricaoService {
    
    private final RestricaoRepository restricaoRepository;

    public RestricaoService(RestricaoRepository restricaoRepository){
        this.restricaoRepository = restricaoRepository;
    }

    public List<Restricao> getAllrestricoes() {
        return restricaoRepository.findAll();
    }

    public Restricao getRestricaoById(@PathVariable Long id) throws ResourceNotFoundException {
        return restricaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("restricao não encontrada. id: " + id));
    }

    public Restricao createRestricao(@RequestBody RestricaoDTO restricaoDetails, Set<Escala> escalasBloqueadas, Militar militar, Usuario usuarioAutor) {

        Restricao novaRestricao = Restricao.builder()
            .militar          (militar                          )
            .usuarioAutor     (usuarioAutor                     )
            .escalasBloqueadas(escalasBloqueadas                )
            .razao            (restricaoDetails.razao()         )
            .detalhes         (restricaoDetails .detalhes()     )
            .restricaoTotal   (restricaoDetails.restricaoTotal())
            .ativa            (restricaoDetails.ativa()         )
            .dataInicio       (restricaoDetails.dataInicio()    )
            .dataFim          (restricaoDetails.dataFim()       )
            .build();

        return restricaoRepository.save(novaRestricao);
    }

    public Restricao updateRestricao(@PathVariable Long id, @RequestBody RestricaoDTO restricaoDetails, Set<Escala> EscalasBloqueadas, Militar militar, Usuario usuarioAutor) {

        Restricao restricao = restricaoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("restrição não encontrada. id: " + id));

        restricao.setMilitar          (militar                          );
        restricao.setEscalasBloqueadas(EscalasBloqueadas                );
        restricao.setUsuarioAutor     (usuarioAutor                     );
        restricao.setDataInicio       (restricaoDetails.dataInicio()    );
        restricao.setDataFim          (restricaoDetails.dataFim()       );
        restricao.setRazao            (restricaoDetails.razao()         );
        restricao.setDetalhes         (restricaoDetails.detalhes()      );
        restricao.setRestricaoTotal   (restricaoDetails.restricaoTotal());
        restricao.setAtiva            (restricaoDetails.ativa()         );

        return restricaoRepository.save(restricao);
    }

    public void deleteRestricao(@PathVariable Long id) {
        restricaoRepository.deleteById(id);
    }
}
