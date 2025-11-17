package com.gerenciadortarefas.service;

import com.gerenciadortarefas.model.user.User;
import com.gerenciadortarefas.repository.UserRepository;
import com.gerenciadortarefas.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, String> login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Credenciais inválidas.");
        }

        String accessToken = jwtUtil.generateAccessToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        return Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        );
    }

    public Map<String, String> refresh(String refreshToken) {
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new RuntimeException("Refresh token inválido ou expirado.");
        }

        String username = jwtUtil.getUsernameFromToken(refreshToken);
        String newAccessToken = jwtUtil.generateAccessToken(username);

        return Map.of("accessToken", newAccessToken);
    }
}
