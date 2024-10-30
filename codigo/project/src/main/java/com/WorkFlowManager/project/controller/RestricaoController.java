package com.WorkFlowManager.project.controller;

import com.WorkFlowManager.project.dto.RestricaoDTO;
import com.WorkFlowManager.project.model.Escala;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.model.Restricao;
import com.WorkFlowManager.project.model.Usuario;
import com.WorkFlowManager.project.service.EscalaService;
import com.WorkFlowManager.project.service.MilitarService;
import com.WorkFlowManager.project.service.RestricaoService;
import com.WorkFlowManager.project.service.UsuarioService;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/restricoes")
public class RestricaoController {

    private final RestricaoService restricaoService;
    private final MilitarService militarService;
    private final EscalaService escalaService;
    private final UsuarioService usuarioService;

    public RestricaoController(RestricaoService restricaoService, MilitarService militarService, EscalaService escalaService, UsuarioService usuarioService) {
        this.restricaoService = restricaoService;
        this.militarService = militarService;
        this.escalaService = escalaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Restricao> getAllrestricoes() {
        return restricaoService.getAllrestricoes();
    }

    @GetMapping("/{id}")
    public Restricao getrestricaoById(@PathVariable Long id) {
        return restricaoService.getRestricaoById(id);
    }

    @PostMapping
    public Restricao createrestricao(@RequestBody RestricaoDTO restricaoDetails) {

        Set<Escala> escalasBloqueadas = escalaService.getEscalasById(restricaoDetails.idsEscalasBloqueadas());
        Militar militar = militarService.getMilitarById(restricaoDetails.idMilitar());
        Usuario usuarioAutor = usuarioService.getUsuarioById(restricaoDetails.idUsuario());
    
        return restricaoService.createRestricao(restricaoDetails, escalasBloqueadas, militar, usuarioAutor);
    }

    @PutMapping("/{id}")
    public Restricao updaterestricao(@PathVariable Long id, @RequestBody RestricaoDTO restricaoDetails) {

        Set<Escala> escalasBloqueadas = escalaService.getEscalasById(restricaoDetails.idsEscalasBloqueadas());
        Militar militar = militarService.getMilitarById(restricaoDetails.idMilitar());
        Usuario usuarioAutor = usuarioService.getUsuarioById(restricaoDetails.idUsuario());

        return restricaoService.updateRestricao(id, restricaoDetails, escalasBloqueadas, militar, usuarioAutor);
    }

    @DeleteMapping("/{id}")
    public void deleterestricao(@PathVariable Long id) {
        restricaoService.deleteRestricao(id);
    }
    
}