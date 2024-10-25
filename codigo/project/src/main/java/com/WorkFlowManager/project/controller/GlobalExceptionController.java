package com.WorkFlowManager.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionController {

    // Captura exceção 404
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(NoHandlerFoundException e, Model model) {
        model.addAttribute("errorName", "Página não encontrada");
        return "error"; // Nome do arquivo HTML único para erros
    }

    // Captura IllegalArgumentException (erro 400 - Bad Request)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequest(IllegalArgumentException e, Model model) {
        model.addAttribute("errorName", "Requisição inválida");
        return "error"; // Nome do arquivo HTML único para erros
    }

    // Captura qualquer exceção genérica e não mapeada
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAllExceptions(Exception e, Model model) {
        model.addAttribute("errorName", "Erro interno do servidor");
        return "error"; // Nome do arquivo HTML único para erros
    }
}