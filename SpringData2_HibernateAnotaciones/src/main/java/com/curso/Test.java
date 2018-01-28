package com.curso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curso.entities.Usuario;
import com.curso.services.UsuarioServiceImpl;

@Component
public class Test {
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void begins (){
		//ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		List<Usuario> usuarios = usuarioService.findAll();
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}
}
