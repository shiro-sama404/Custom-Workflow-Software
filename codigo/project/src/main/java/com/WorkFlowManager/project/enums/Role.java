package com.WorkFlowManager.project.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    PESSOAL,
    SARGENTEACAO,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}