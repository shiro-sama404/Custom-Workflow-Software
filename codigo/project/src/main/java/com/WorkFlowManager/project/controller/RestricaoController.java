package com.WorkFlowManager.project.controller;

import com.WorkFlowManager.project.dto.RestricaoDTO;
import com.WorkFlowManager.project.model.Restricao;
import com.WorkFlowManager.project.service.RestricaoService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RestricaoController {

    private final RestricaoService restricaoService;

    public RestricaoController(RestricaoService restricaoService) {
        this.restricaoService = restricaoService;
    }

    @GetMapping("/restricoes")
    public List<Restricao> getAllrestricoes() {
        return restricaoService.getAllrestricoes();
    }

    @GetMapping("/restricoes/{id}")
    public Restricao getrestricaoById(@PathVariable Long id) {
        return restricaoService.getRestricaoById(id);
    }

    @PostMapping("/restricoes")
    public Restricao createrestricao(@RequestBody RestricaoDTO restricaoDetails) {
        return restricaoService.createRestricao(restricaoDetails);
    }

    @PutMapping("/restricoes/{id}")
    public Restricao updaterestricao(@PathVariable Long id, @RequestBody RestricaoDTO restricaoDetails) {
        return restricaoService.updateRestricao(id, restricaoDetails);
    }

    @DeleteMapping("/restricoes/{id}")
    public void deleterestricao(@PathVariable Long id) {
        restricaoService.deleteRestricao(id);
    }
}