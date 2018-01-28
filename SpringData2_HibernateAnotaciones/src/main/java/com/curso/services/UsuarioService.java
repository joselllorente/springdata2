package com.curso.services;

import java.util.List;

import com.curso.entities.Usuario;

public interface UsuarioService {
	
	 void create(final Usuario entity);
	 List<Usuario> findAll();
	 Usuario findById(final String id);
	 void remove(final Usuario entity);
	 void update(final Usuario entity);

}
