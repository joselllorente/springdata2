package com.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.curso.entities.Usuario;
import com.curso.repository.UsuarioRepository;

public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioDao;
	
	public void create(Usuario entity) {
		usuarioDao.save(entity);
	}

	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	public Usuario findById(String id) {
		return usuarioDao.findOne(id);
	}

	public void remove(Usuario entity) {
		usuarioDao.delete(entity);
	}

	public void update(Usuario entity) {
		usuarioDao.save(entity);
	}

}
