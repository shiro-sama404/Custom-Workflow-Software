package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.dto.UsuarioDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.model.Organizacao;
import com.WorkFlowManager.project.model.Usuario;
import com.WorkFlowManager.project.repository.UsuarioRepository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioService implements UserDetailsService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<Usuario> getAllUsuarios(Pageable pageble) {
        return usuarioRepository.findAll(pageble);
    }

    public Usuario getUsuarioById(@PathVariable Long id) throws ResourceNotFoundException {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("usuário não encontrado. id: " + id));
    }

    public Usuario createUsuario(@RequestBody UsuarioDTO userDetails, Militar militar, Organizacao organizacao) {

        Usuario usuario = Usuario.builder()
            .militar(militar)
            .organizacao(organizacao)
            .username(userDetails.username())
            .roles(userDetails.roles())
            .passwordHash(userDetails.password())
            .salt(null)
            .email(userDetails.email())
            .tentativasFalhasLogin(0)
            .dataCriacao(LocalDateTime.now())
            .ativo(userDetails.ativo())
            .build();

        return usuarioRepository.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO userDetails, Organizacao organizacao) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("usuário não encontrado. id: " + id));
        
        usuario.setOrganizacao(organizacao           );
        usuario.setUsername   (userDetails.username());
        usuario.setEmail      (userDetails.email()   );
        usuario.setTelefone   (userDetails.telefone());
        usuario.setAtivo      (userDetails.ativo()   );

        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario userAccount = usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new org.springframework.security.core.userdetails.User(
                userAccount.getUsername(), 
                userAccount.getPasswordHash(),  // Usa o passwordHash
                true,                           // Se a conta está habilitada (true ou false)
                true,                           // Se a conta não está expirada (true ou false)
                true,                           // Se as credenciais não estão expiradas (true ou false)
                true,                           // Se a conta não está bloqueada (true ou false)
                userAccount.getAuthorities()    // Lista de permissões (authorities)
        );
    }

    public boolean checkPassword(String rawPassword, Usuario usuario) {
        String saltedPassword = rawPassword + usuario.getSalt();
        return passwordEncoder.matches(saltedPassword, usuario.getPasswordHash());
    }
}