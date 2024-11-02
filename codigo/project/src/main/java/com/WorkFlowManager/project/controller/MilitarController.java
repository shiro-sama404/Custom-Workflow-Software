package com.WorkFlowManager.project.controller;

import com.WorkFlowManager.project.dto.MilitarDTO;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.service.MilitarService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/militares")
public class MilitarController {

    private final MilitarService militarService;

    public MilitarController(MilitarService militarService) {
        this.militarService = militarService;
    }

    @GetMapping
    public String Militares() {
        return "militares";
    }

    @GetMapping("/{id}")
    public String getMilitarById(@PathVariable Long id, Model model) {

        Militar militar = militarService.getMilitarById(id);
        model.addAttribute("militar", militar);
        return "militares";
    }

    @PostMapping
    public Militar createMilitar(@RequestBody MilitarDTO militarDetails) {
        return militarService.createMilitar(militarDetails);
    }

    @PutMapping("/{id}")
    public Militar updateMilitar(@PathVariable Long id, @RequestBody MilitarDTO militarDetails) {
        return militarService.updateMilitar(id, militarDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMilitar(@PathVariable Long id) {
        militarService.deleteMilitar(id);
    }
}