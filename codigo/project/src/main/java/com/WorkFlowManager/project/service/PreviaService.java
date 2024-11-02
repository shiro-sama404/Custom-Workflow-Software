package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.dto.PreviaDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Escala;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.model.Previa;
import com.WorkFlowManager.project.model.Usuario;
import com.WorkFlowManager.project.repository.EscalaRepository;
import com.WorkFlowManager.project.repository.MilitarRepository;
import com.WorkFlowManager.project.repository.PreviaRepository;
import com.WorkFlowManager.project.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PreviaService {
    
    private final PreviaRepository  previaRepository;
    private final EscalaRepository  escalaRepository;
    private final MilitarRepository militarRepository;
    private final UsuarioRepository usuarioRepository;

    public PreviaService(PreviaRepository previaRepository, EscalaRepository  escalaRepository, MilitarRepository militarRepository, UsuarioRepository usuarioRepository) {
        this.previaRepository  = previaRepository;
        this.escalaRepository  = escalaRepository;
        this.militarRepository = militarRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Previa getPreviaById(@PathVariable Long id) throws ResourceNotFoundException {
        return previaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("previa não encontrada. id: "+ id));
    }

    public Previa convertToPrevia(PreviaDTO previaDetails){

        Escala escala = escalaRepository.findById(previaDetails.idEscala())
            .orElseThrow(() -> new ResourceNotFoundException("Escala não encontrada. id: "+ previaDetails.idEscala()));

        Militar militar = militarRepository.findById(previaDetails.idMilitar())
            .orElseThrow(() -> new ResourceNotFoundException("Militar não encontrado. id: "+ previaDetails.idMilitar()));

        Usuario usuario = usuarioRepository.findById(previaDetails.idUsuario())
            .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado. id: "+ previaDetails.idUsuario()));

        return Previa.builder()
            .escala    (escala                    )
            .militar   (militar                   )
            .usuario   (usuario                   )
            .dataInicio(previaDetails.dataInicio())
            .dataFim   (previaDetails.dataFim()   )
            .status    (previaDetails.status()    )
            .build();
    }

    public PreviaDTO convertToDTO(Previa previa){
        return PreviaDTO.publicDTO(previa);
    }

    private Specification<Previa> byEscalaId(Long escalaId) {
        return (root, query, builder) -> builder.equal(root.get("idEscala"), escalaId);
    }

    private Specification<Previa> byDataInicio(LocalDate dataInicio) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("dataInicio"), dataInicio);
    }

    private Specification<Previa> byDataFim(LocalDate dataFim) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("dataFim"), dataFim);
    }

    public List<PreviaDTO> getPreviaByEscala(Long escalaId, PreviaDTO filter) {

        Specification<Previa> spec = Specification.where(byEscalaId(escalaId));

        if (filter.dataInicio() != null) {
            spec = spec.and(byDataInicio(filter.dataInicio()));
        }
        if (filter.dataFim() != null) {
            spec = spec.and(byDataFim(filter.dataFim()));
        }

        List<Previa> previa = previaRepository.findAll(spec);

        return previa
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Transactional
    public List<Previa> createPrevia(@RequestBody List<PreviaDTO> previaDetails) {
        return previaRepository.saveAll(previaDetails
            .stream()
            .map(this::convertToPrevia)
            .collect(Collectors.toList()));
    }

    public Previa updatePrevia(@PathVariable Long id, @RequestBody PreviaDTO previaDetails) {

        Previa previa = previaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("previa não encontrada. id: "+ id));

        Escala escala = escalaRepository.findById(previaDetails.idEscala())
            .orElseThrow(() -> new ResourceNotFoundException("Escala não encontrada. id: "+ previaDetails.idEscala()));

        Militar militar = militarRepository.findById(previaDetails.idMilitar())
            .orElseThrow(() -> new ResourceNotFoundException("Militar não encontrado. id: "+ previaDetails.idMilitar()));

        Usuario usuario = usuarioRepository.findById(previaDetails.idUsuario())
            .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado. id: "+ previaDetails.idUsuario()));
        
        previa.setStatus    (previaDetails.status()    );
        previa.setMilitar   (militar                   );
        previa.setEscala    (escala                    );
        previa.setUsuario   (usuario                   );
        previa.setDataInicio(previaDetails.dataInicio());
        previa.setDataFim   (previaDetails.dataFim()   );

        return previaRepository.save(previa);
    }

    public void deletePrevia(@PathVariable Long id) {
        previaRepository.deleteById(id);
    }

    public Long getAuthenticatedUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return ((Usuario) userDetails).getId();
        }

        return null;
    }
}