package com.WorkFlowManager.project.controller;

import com.WorkFlowManager.project.dto.PreviaDTO;
import com.WorkFlowManager.project.dto.PreviaFilterDTO;
import com.WorkFlowManager.project.enums.PreviaFormat;
import com.WorkFlowManager.project.service.PreviaService;
import com.WorkFlowManager.project.util.DateUtil;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PreviaController {

    private final PreviaService previaService;

    public PreviaController(PreviaService previaService) {
        this.previaService = previaService;
    }

    @GetMapping({"/", "/previa", "home"})
    public String getPreviaPage(Model model) {
        
        String currentMonth = DateUtil.monthNumberToString(LocalDate.now().getMonthValue()-1);

        model.addAttribute("alocacoes", null);
        model.addAttribute("month", currentMonth);

        return "previa";
    }

    @GetMapping("/previa/render")
    public String renderPrevia(@RequestParam PreviaFilterDTO filter, @RequestParam PreviaFormat format, Model model) {

        List<PreviaDTO> previasFiltradas = previaService.getPreviaByFilter(filter);
        model.addAttribute("alocacoes", previasFiltradas);

        return switch (format) {
            case TABELA -> "tabela";
            case CALENDARIO -> "calendario";
        };
    }

    @GetMapping("/edit/previa/{escalaId}")
    public String getPreviaByUsuarioByStatus(@PathVariable Long escalaId, Model model) {
        // TODO
        // List<PreviaDTO> previaSalva = previaService.getPreviaByUsuario(escalaId);
        // model.addAttribute("saved", previaSalva.size() > 0);
        return "";
    }

    @ResponseBody
    @PostMapping("/edit/previa/{escalaID}")
    public void createPrevia(@RequestBody List<PreviaDTO> previaDetails, Model model) {
        previaService.createPrevia(previaDetails);
    }

    @PutMapping("/edit/previa/{escalaId}")
    public void updatePrevia(@PathVariable Long id, @RequestBody PreviaDTO previaDetails, Model model) {
        previaService.updatePrevia(id, previaDetails);
    }

    @DeleteMapping("/edit/previa/{id}")
    public void deletePrevia(@PathVariable Long id) {
        previaService.deletePrevia(id);
    }
}