package com.zetalabs.mood.app.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zetalabs.mood.app.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByUsername(String username);
}
