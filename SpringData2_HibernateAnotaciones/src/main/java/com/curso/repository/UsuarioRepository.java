package com.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
