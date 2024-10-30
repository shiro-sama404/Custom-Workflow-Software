package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.dto.PreviaDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Escala;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.model.Previa;
import com.WorkFlowManager.project.model.Usuario;
import com.WorkFlowManager.project.repository.PreviaRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PreviaService {
    
    private final PreviaRepository previaRepository;

    public PreviaService(PreviaRepository previaRepository) {
        this.previaRepository = previaRepository;
    }

    public Previa getPreviaById(@PathVariable Long id) throws ResourceNotFoundException {
        return previaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("previa não encontrada. id: " + id));
    }

    public Previa createPrevia(@RequestBody PreviaDTO previaDetails, Escala escala, Militar militar, Usuario usuario) {

        Previa novaPrevia = Previa.builder()
            .status    (previaDetails.status()    )
            .escala    (escala                    )
            .militar   (militar                   )
            .usuario   (usuario                   )
            .dataInicio(previaDetails.dataInicio())
            .dataFim   (previaDetails.dataFim()   )
            .build();

        return previaRepository.save(novaPrevia);
    }

    public Previa updatePrevia(@PathVariable Long id, @RequestBody PreviaDTO previaDetails, Escala escala, Militar militar, Usuario usuario) {

        Previa previa = previaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("previa não encontrada. id: " + id));
        
        previa.setStatus       (previaDetails.status()    );
        previa.setMilitar      (militar                   );
        previa.setEscala       (escala                    );
        previa.setUsuario      (usuario                   );
        previa.setDataInicio   (previaDetails.dataInicio());
        previa.setDataFim      (previaDetails.dataFim()   );

        return previaRepository.save(previa);
    }

    public void deletePrevia(@PathVariable Long id) {
        previaRepository.deleteById(id);
    }
}