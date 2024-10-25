package com.WorkFlowManager.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.WorkFlowManager.project.dto.RestricaoDTO;
import com.WorkFlowManager.project.model.Restricao;
import com.WorkFlowManager.project.service.RestricaoService;

@Controller
@RequestMapping("/edit/restricoes")
public class RestricaoController {

    private final RestricaoService restricaoService;

    public RestricaoController(RestricaoService restricaoService) {
        this.restricaoService = restricaoService;
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
    public Restricao createrestricao(@RequestBody Restricao restricao) {
        return restricaoService.createRestricao(restricao);
    }

    @PutMapping("/{id}")
    public Restricao updaterestricao(@PathVariable Long id, @RequestBody RestricaoDTO restricao) {
        return restricaoService.updateRestricao(id, restricao);
    }

    @DeleteMapping("/{id}")
    public void deleterestricao(@PathVariable Long id) {
        restricaoService.deleteRestricao(id);
    }
    
}