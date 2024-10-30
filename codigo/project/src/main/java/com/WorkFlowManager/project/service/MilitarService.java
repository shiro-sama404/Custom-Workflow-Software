package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.dto.MilitarDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.model.Organizacao;
import com.WorkFlowManager.project.repository.MilitarRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MilitarService {
    
    private final MilitarRepository militarRepository;

    public MilitarService(MilitarRepository militarRepository) {
        this.militarRepository = militarRepository;
    }

    public List<Militar> getAllMilitares() {
        return militarRepository.findAll();
    }

    public Set<Militar> getMilitaresById(@PathVariable Long[] ids) throws ResourceNotFoundException {

        List<Long> militaresIds = Arrays.asList(ids);

        Set<Militar> Militares = new HashSet<>(militarRepository.findAllById(militaresIds));

        return Militares;
    }

    public Militar getMilitarById(@PathVariable Long id) throws ResourceNotFoundException {
        return militarRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("militar não encontrada. id: " + id));
    }

    public Militar createMilitar(@RequestBody MilitarDTO militarDetails, @RequestBody Organizacao organizacao) {
        
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

    public Militar updateMilitar(@PathVariable Long id, @RequestBody MilitarDTO militarDetails,  @RequestBody Organizacao organizacao) {

        Militar militar = militarRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("militar não encontrado. id: " + id));
        
        militar.setIdentidade    (militarDetails.identidade()    );
        militar.setNomeCompleto  (militarDetails.nomeCompleto()  );
        militar.setNomeGuerra    (militarDetails.nomeGuerra()    );
        militar.setPostoGraduacao(militarDetails.postoGraduacao());
        militar.setQas           (militarDetails.qas()           );
        militar.setHabilitacao   (militarDetails.habilitacao()   );
        militar.setAntiguidade   (militarDetails.antiguidade()   );
        militar.setOrganizacao   (organizacao                    );
        militar.setDataNascimento(militarDetails.dataNascimento());
        militar.setDataPraca     (militarDetails.dataPraca()     );
        militar.setDataPromocao  (militarDetails.dataPromocao()  );
        militar.setCfcCasCao     (militarDetails.cfcCasCao()     );
        militar.setAtivo         (militarDetails.ativo()         );

        return militarRepository.save(militar);
    }

    public void deleteMilitar(@PathVariable Long id) {
        militarRepository.deleteById(id);
    }
}