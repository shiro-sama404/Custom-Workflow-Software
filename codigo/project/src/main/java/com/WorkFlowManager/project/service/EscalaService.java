package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.dto.EscalaDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Escala;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.model.Organizacao;
import com.WorkFlowManager.project.repository.EscalaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EscalaService {
    
    private final EscalaRepository escalaRepository;

    public EscalaService(EscalaRepository escalaRepository) {
        this.escalaRepository = escalaRepository;
    }

    public List<Escala> getAllEscalas() {
        return escalaRepository.findAll();
    }

    public Set<Escala> getEscalasById(Long[] ids) throws ResourceNotFoundException {

        List<Long> idList = Arrays.asList(ids);

        Set<Escala> escalas = new HashSet<>(escalaRepository.findAllById(idList));

        return escalas;
    }

    public Escala getEscalaById(@PathVariable Long id) throws ResourceNotFoundException {
        return escalaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Escala não encontrada. id: " + id));
    }

    public Escala createEscala(@RequestBody EscalaDTO escalaDetails, Set<Militar> militares, Organizacao organizacao) {

        Set<Escala> escalasBloqueadas = new HashSet<>();

        for(Long idEscalaBloqueada : escalaDetails.idEscalasBloqueadas()){
            Escala escalaBloqueada = escalaRepository.findById(idEscalaBloqueada)
            .orElseThrow(() -> new ResourceNotFoundException("Escala não encontrada. id: " + idEscalaBloqueada));

            escalasBloqueadas.add(escalaBloqueada);
        }

        Escala novaEscala = Escala.builder()
            .organizacao      (organizacao                  )
            .militares        (militares                    )
            .escalasBloqueadas(escalasBloqueadas            )
            .nome             (escalaDetails.nome()         )
            .efetivo          (escalaDetails.efetivo()      )
            .duracaoDias      (escalaDetails.duracaoDias()  )
            .intervaloDias    (escalaDetails.intervaloDias())
            .pretaVermelha    (escalaDetails.pretaVermelha())
            .bloqueioTotal    (escalaDetails.bloqueioTotal())
            .ativa            (escalaDetails.ativa()        )
            .build();

        return escalaRepository.save(novaEscala);
    }

    public Escala updateEscala(@PathVariable Long id, @RequestBody EscalaDTO escalaDetails, Set<Militar> militares, Organizacao organizacao) {

        Escala escala = escalaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Escala não encontrada. id: " + id));

        Set<Escala> escalasBloqueadas = getEscalasById(escalaDetails.idEscalasBloqueadas());
        
        escala.setOrganizacao      (organizacao                  );
        escala.setMilitares        (militares                    );
        escala.setEscalasBloqueadas(escalasBloqueadas            );
        escala.setNome             (escalaDetails.nome()         );
        escala.setGrupo            (escalaDetails.grupo()        );
        escala.setBloqueioTotal    (escalaDetails.bloqueioTotal());
        escala.setIntervaloDias    (escalaDetails.intervaloDias());
        escala.setDuracaoDias      (escalaDetails.duracaoDias()  );
        escala.setEfetivo          (escalaDetails.efetivo()      );
        escala.setPretaVermelha    (escalaDetails.pretaVermelha());
        escala.setAtiva            (escalaDetails.ativa()        );

        return escalaRepository.save(escala);
    }

    public void deleteEscala(@PathVariable Long id) {
        escalaRepository.deleteById(id);
    }
}