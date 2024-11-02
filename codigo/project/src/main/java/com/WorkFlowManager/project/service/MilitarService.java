package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.dto.MilitarDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.model.Organizacao;
import com.WorkFlowManager.project.repository.MilitarRepository;
import com.WorkFlowManager.project.repository.OrganizacaoRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MilitarService {
    
    private final MilitarRepository     militarRepository;
    private final OrganizacaoRepository organizacaoRepository;

    public MilitarService(MilitarRepository militarRepository, OrganizacaoRepository organizacaoRepository) {
        this.militarRepository     = militarRepository;
        this.organizacaoRepository = organizacaoRepository;
    }

    public List<Militar> getAllMilitares() {
        return militarRepository.findAll();
    }

    public Militar getMilitarById(@PathVariable Long id) throws ResourceNotFoundException {
        return militarRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Militar não encontrada. id: " + id));
    }

    public Militar createMilitar(@RequestBody MilitarDTO militarDetails) {

        Organizacao organizacao = organizacaoRepository.findById(militarDetails.idOrganizacao())
            .orElseThrow(() -> new ResourceNotFoundException("Organização não encontrada. Id: "+ militarDetails.idOrganizacao()));
        
        Militar novoMilitar = Militar.builder()
            .identidade    (militarDetails.identidade()    )
            .nomeCompleto  (militarDetails.nomeCompleto()  )
            .nomeGuerra    (militarDetails.nomeGuerra()    )
            .postoGraduacao(militarDetails.postoGraduacao())
            .qas           (militarDetails.qas()           )
            .habilitacao   (militarDetails.habilitacao()   )
            .antiguidade   (militarDetails.antiguidade()   )
            .organizacao   (organizacao                    )
            .dataNascimento(militarDetails.dataNascimento())
            .dataPraca     (militarDetails.dataPraca()     )
            .dataPromocao  (militarDetails.dataPromocao()  )
            .cfcCasCao     (militarDetails.cfcCasCao()     )
            .ativo         (militarDetails.ativo()         )
            .build();

        return militarRepository.save(novoMilitar);
    }

    public Militar updateMilitar(@PathVariable Long id, @RequestBody MilitarDTO militarDetails) {

        Militar militar = militarRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Militar não encontrado. id: " + id));

        Organizacao organizacao = organizacaoRepository.findById(militarDetails.idOrganizacao())
            .orElseThrow(() -> new ResourceNotFoundException("Organização não encontrada. Id: "+ militarDetails.idOrganizacao()));
        
        militar.setOrganizacao   (organizacao                    );
        militar.setIdentidade    (militarDetails.identidade()    );
        militar.setNomeCompleto  (militarDetails.nomeCompleto()  );
        militar.setNomeGuerra    (militarDetails.nomeGuerra()    );
        militar.setPostoGraduacao(militarDetails.postoGraduacao());
        militar.setHabilitacao   (militarDetails.habilitacao()   );
        militar.setQas           (militarDetails.qas()           );
        militar.setDataPromocao  (militarDetails.dataPromocao()  );
        militar.setDataPraca     (militarDetails.dataPraca()     );
        militar.setDataNascimento(militarDetails.dataNascimento());
        militar.setAntiguidade   (militarDetails.antiguidade()   );
        militar.setCfcCasCao     (militarDetails.cfcCasCao()     );
        militar.setAtivo         (militarDetails.ativo()         );

        return militarRepository.save(militar);
    }

    public void deleteMilitar(@PathVariable Long id) {
        militarRepository.deleteById(id);
    }
}