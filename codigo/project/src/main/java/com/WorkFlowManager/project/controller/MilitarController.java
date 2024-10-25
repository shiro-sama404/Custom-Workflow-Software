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

import com.WorkFlowManager.project.dto.MilitarDTO;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.service.MilitarService;

@Controller
@RequestMapping("/edit/militares")
public class MilitarController {

    private final MilitarService militarService;

    public MilitarController(MilitarService militarService) {
        this.militarService = militarService;
    }

    @GetMapping
    public List<Militar> getAllMilitares() {
        return militarService.getAllMilitares();
    }

    @GetMapping("/{id}")
    public Militar getMilitarById(@PathVariable Long id) {
        return militarService.getMilitarById(id);
    }

    @PostMapping
    public Militar createMilitar(@RequestBody Militar militar) {
        return militarService.createMilitar(militar);
    }

    @PutMapping("/{id}")
    public Militar updateMilitar(@PathVariable Long id, @RequestBody MilitarDTO militar) {
        return militarService.updateMilitar(id, militar);
    }

    @DeleteMapping("/{id}")
    public void deleteMilitar(@PathVariable Long id) {
        militarService.deleteMilitar(id);
    }
}