package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.dto.RestricaoDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Escala;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.model.Restricao;
import com.WorkFlowManager.project.model.Usuario;
import com.WorkFlowManager.project.repository.EscalaRepository;
import com.WorkFlowManager.project.repository.MilitarRepository;
import com.WorkFlowManager.project.repository.RestricaoRepository;
import com.WorkFlowManager.project.repository.UsuarioRepository;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RestricaoService {
    
    private final RestricaoRepository restricaoRepository;
    private final EscalaRepository    escalaRepository;
    private final MilitarRepository   militarRepository;
    private final UsuarioRepository   usuarioRepository;

    public RestricaoService(RestricaoRepository restricaoRepository, EscalaRepository  escalaRepository, MilitarRepository militarRepository, UsuarioRepository usuarioRepository){
        this.restricaoRepository = restricaoRepository;
        this.escalaRepository    = escalaRepository;
        this.militarRepository   = militarRepository;
        this.usuarioRepository   = usuarioRepository;
    }

    public List<Restricao> getAllrestricoes() {
        return restricaoRepository.findAll();
    }

    public Restricao getRestricaoById(@PathVariable Long id) throws ResourceNotFoundException {
        return restricaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restricao não encontrada. id: " + id));
    }

    public Restricao createRestricao(@RequestBody RestricaoDTO restricaoDetails) {

        Set<Escala> escalasBloqueadas = new HashSet<>(escalaRepository.findAllById(Arrays.asList(restricaoDetails.idsEscalasBloqueadas())));

        Militar militar = militarRepository.findById(restricaoDetails.idMilitar())
            .orElseThrow(() -> new ResourceNotFoundException("Militar não encontrado. id: "+restricaoDetails.idMilitar()));

        Usuario usuarioAutor = usuarioRepository.findById(restricaoDetails.idUsuarioAutor())
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. id: "+restricaoDetails.idUsuarioAutor()));
        
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

    public Restricao updateRestricao(@PathVariable Long id, @RequestBody RestricaoDTO restricaoDetails) {

        Restricao restricao = restricaoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Restrição não encontrada. id: " + id));

        Set<Escala> escalasBloqueadas = new HashSet<>(escalaRepository.findAllById(Arrays.asList(restricaoDetails.idsEscalasBloqueadas())));

        Militar militar = militarRepository.findById(restricaoDetails.idMilitar())
            .orElseThrow(() -> new ResourceNotFoundException("Militar não encontrado. id: "+restricaoDetails.idMilitar()));

        Usuario usuarioAutor = usuarioRepository.findById(restricaoDetails.idUsuarioAutor())
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. id: "+restricaoDetails.idUsuarioAutor()));

        restricao.setMilitar          (militar                          );
        restricao.setEscalasBloqueadas(escalasBloqueadas                );
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