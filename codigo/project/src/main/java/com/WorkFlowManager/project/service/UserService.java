package com.WorkFlowManager.project.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.WorkFlowManager.project.dto.UserDTO;
import com.WorkFlowManager.project.exception.ResourceNotFoundException;
import com.WorkFlowManager.project.model.User;
import com.WorkFlowManager.project.repository.UserRepository;

public class UserService implements UserDetailsService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(@PathVariable Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user não encontrada com id: " + id));
    }

    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    public User updateUser(@PathVariable Long id, @RequestBody UserDTO userDetails) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user não encontrada com id: " + id));
        
        user.setId(userDetails.id());
        user.setIdOrganizacao(userDetails.idOrganizacao());
        user.setUsername(userDetails.username());
        user.setPasswordHash(userDetails.passwordHash());
        user.setSalt(userDetails.salt());
        user.setNomeCompleto(userDetails.nomeCompleto());
        user.setEmail(userDetails.email());
        user.setTentativasFalhasLogin(userDetails.tentativasFalhasLogin());
        user.setDataCriacao(userDetails.dataCriacao());
        user.setDataUltimoLogin(userDetails.dataUltimoLogin());
        user.setContaAtiva(userDetails.contaAtiva());
        user.setMilitar(userDetails.militar());

        return userRepository.save(user);
    }

    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Criar um objeto UserDetails para Spring Security
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), 
                user.getPasswordHash(),  // Usar o passwordHash
                true,                    // Se a conta está habilitada (true ou false)
                true,                    // Se a conta não está expirada (true ou false)
                true,                    // Se as credenciais não estão expiradas (true ou false)
                true,                    // Se a conta não está bloqueada (true ou false)
                user.getRoles()    // Lista de permissões (authorities)
        );
    }

    public boolean checkPassword(String rawPassword, User user) {
        String saltedPassword = rawPassword + user.getSalt();
        return passwordEncoder.matches(saltedPassword, user.getPasswordHash());
    }
}