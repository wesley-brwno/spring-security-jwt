package com.example.demo.auth.repository;

import com.example.demo.auth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRespository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}
