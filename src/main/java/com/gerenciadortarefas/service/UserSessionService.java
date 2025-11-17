package com.gerenciadortarefas.service;

import com.gerenciadortarefas.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserSessionService {

    public CustomUserDetails get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) auth.getPrincipal();
    }
    
    public String getEmail() {
        return get().getUsername();
    }
}