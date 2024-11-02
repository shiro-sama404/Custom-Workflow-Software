package com.WorkFlowManager.project.controller;

import com.WorkFlowManager.project.dto.UsuarioDTO;
import com.WorkFlowManager.project.service.AuthenticationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {
    
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDTO usuarioDTO) {

        boolean isValid;

        if (usuarioDTO.username() != null) {
            isValid = authenticationService.checkPasswordByUsername(usuarioDTO.username(), usuarioDTO.rawPassword());
        } else if (usuarioDTO.email() != null) {
            isValid = authenticationService.checkPasswordByEmail(usuarioDTO.email(), usuarioDTO.rawPassword());
        } else {
            isValid = false;
        }

        if (isValid) {
            return ResponseEntity.ok("Login bem-sucedido");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok("Logout bem-sucedido");
    }
}