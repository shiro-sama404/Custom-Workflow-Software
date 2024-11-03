package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.config.AppUserPrincipal;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Usuario;
import com.WorkFlowManager.project.repository.UsuarioRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    
    private final PasswordEncoder   passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public AuthenticationService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository){
        this.passwordEncoder   = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    public Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return ((AppUserPrincipal) userDetails).getId();
        }

        throw new IllegalStateException("Usuário não autenticado");
    }

    @Override
    public AppUserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        return new AppUserPrincipal(usuario);
    }

    public boolean checkPasswordByUsername(String rawPassword, String username) {
        
        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário "+username+" não encontrado."));

        return passwordEncoder.matches(rawPassword, usuario.getPasswordHash());
    }

    public boolean checkPasswordByEmail(String email, String username) {
        
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário "+username+" não encontrado."));

        return passwordEncoder.matches(email, usuario.getPasswordHash());
    }
}