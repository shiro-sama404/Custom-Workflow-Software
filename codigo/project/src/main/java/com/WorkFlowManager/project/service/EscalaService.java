package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.dto.EscalaDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Escala;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.model.Organizacao;
import com.WorkFlowManager.project.repository.EscalaRepository;
import com.WorkFlowManager.project.repository.MilitarRepository;
import com.WorkFlowManager.project.repository.OrganizacaoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EscalaService {
    
    private final EscalaRepository      escalaRepository;
    private final OrganizacaoRepository organizacaoRepository;
    private final MilitarRepository     militarRepository;

    public EscalaService(EscalaRepository escalaRepository, OrganizacaoRepository organizacaoRepository, MilitarRepository militarRepository) {
        this.escalaRepository      = escalaRepository;
        this.organizacaoRepository = organizacaoRepository;
        this.militarRepository     = militarRepository;
    }

    public List<Escala> getAllEscalas() {
        return escalaRepository.findAll();
    }

    public Escala getEscalaById(@PathVariable Long id) throws ResourceNotFoundException {
        return escalaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Escala não encontrada. id: " + id));
    }

    public Escala createEscala(@RequestBody EscalaDTO escalaDetails) {

        Organizacao organizacao = organizacaoRepository.findById(escalaDetails.idOrganizacao())
            .orElseThrow(() -> new ResourceNotFoundException("Organização não encontrada. id: " + escalaDetails.idOrganizacao()));

        Set<Escala> escalasBloqueadas = new HashSet<>(escalaRepository.findAllById(Arrays.asList(escalaDetails.idEscalasBloqueadas())));

        if(escalasBloqueadas.size() != escalaDetails.idEscalasBloqueadas().length){
            throw new ResourceNotFoundException("Erro na associação de escalas bloqueadas. Uma ou mais escalas não foram encontradas.");
        }

        Set<Militar> militares = new HashSet<>(militarRepository.findAllById(Arrays.asList(escalaDetails.idMilitares())));

        if(militares.size() != escalaDetails.idMilitares().length){
            throw new ResourceNotFoundException("Erro na associação de militares. Um ou mais militares não foram encontrados.");
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

    public Escala updateEscala(@PathVariable Long id, @RequestBody EscalaDTO escalaDetails) {

        Escala escala = escalaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Escala não encontrada. id: " + id));

        Organizacao organizacao = organizacaoRepository.findById(escalaDetails.idOrganizacao())
            .orElseThrow(() -> new ResourceNotFoundException("Organização não encontrada. id: " + escalaDetails.idOrganizacao()));

        Set<Escala> escalasBloqueadas = new HashSet<>(escalaRepository.findAllById(Arrays.asList(escalaDetails.idEscalasBloqueadas())));

        if(escalasBloqueadas.size() != escalaDetails.idEscalasBloqueadas().length){
            throw new ResourceNotFoundException("Erro na associação de escalas bloqueadas. Uma ou mais escalas não foram encontradas.");
        }

        Set<Militar> militares = new HashSet<>(militarRepository.findAllById(Arrays.asList(escalaDetails.idMilitares())));

        if(militares.size() != escalaDetails.idMilitares().length){
            throw new ResourceNotFoundException("Erro na associação de militares. Um ou mais militares não foram encontrados.");
        }
        
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