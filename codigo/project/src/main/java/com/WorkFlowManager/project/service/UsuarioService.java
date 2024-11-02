package com.WorkFlowManager.project.service;

import com.WorkFlowManager.project.dto.UsuarioDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.Militar;
import com.WorkFlowManager.project.model.Organizacao;
import com.WorkFlowManager.project.model.Usuario;
import com.WorkFlowManager.project.repository.MilitarRepository;
import com.WorkFlowManager.project.repository.OrganizacaoRepository;
import com.WorkFlowManager.project.repository.UsuarioRepository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioService{
    
    private final UsuarioRepository     usuarioRepository;
    private final PasswordEncoder       passwordEncoder;
    private final OrganizacaoRepository organizacaoRepository;
    private final MilitarRepository     militarRepository;

    public UsuarioService(UsuarioRepository userAccountRepository, PasswordEncoder passwordEncoder, OrganizacaoRepository organizacaoRepository, MilitarRepository militarRepository) {
        this.usuarioRepository     = userAccountRepository;
        this.passwordEncoder       = passwordEncoder;
        this.organizacaoRepository = organizacaoRepository;
        this.militarRepository     = militarRepository;
    }

    public Page<Usuario> getAllUsuarios(Pageable pageble) {
        return usuarioRepository.findAll(pageble);
    }

    public Usuario getUsuarioById(@PathVariable Long id) throws ResourceNotFoundException {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("usuário não encontrado. id: " + id));
    }

    public Usuario createUsuario(@RequestBody UsuarioDTO usuarioDetails) {

        Militar militar = militarRepository.findById(usuarioDetails.idMilitar())
            .orElseThrow(() -> new ResourceNotFoundException("Militar não encontrado. id: "+ usuarioDetails.idMilitar()));

        Organizacao organizacao = organizacaoRepository.findById(usuarioDetails.idOrganizacao())
            .orElseThrow(() -> new ResourceNotFoundException("Organização não encontrada. id: "+ usuarioDetails.idOrganizacao()));

        String passwordHash = passwordEncoder.encode(usuarioDetails.rawPassword());

        Usuario usuario = Usuario.builder()
            .militar              (militar                  )
            .organizacao          (organizacao              )
            .username             (usuarioDetails.username())
            .roles                (usuarioDetails.roles()   )
            .passwordHash         (passwordHash             )
            .email                (usuarioDetails.email()   )
            .tentativasFalhasLogin(0  )
            .dataCriacao          (LocalDateTime.now()      )
            .ativo                (usuarioDetails.ativo()   )
            .build();

        return usuarioRepository.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDetails) {

        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. id: " + id));

        Organizacao organizacao = organizacaoRepository.findById(usuarioDetails.idOrganizacao())
            .orElseThrow(() -> new ResourceNotFoundException("Organização não encontrada. id: "+ usuarioDetails.idOrganizacao()));
        
        usuario.setOrganizacao(organizacao              );
        usuario.setUsername   (usuarioDetails.username());
        usuario.setEmail      (usuarioDetails.email()   );
        usuario.setTelefone   (usuarioDetails.telefone());
        usuario.setAtivo      (usuarioDetails.ativo()   );

        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}