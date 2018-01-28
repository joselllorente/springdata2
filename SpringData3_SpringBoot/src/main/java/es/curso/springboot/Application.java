package es.curso.springboot;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import es.curso.springboot.jpa.User;
import es.curso.springboot.jpa.UserCommandLineRunner;
import es.curso.springboot.jpa.UserRepository;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

	}

	@Profile("prod")
	@Bean
	public String dummy() {
		return "something";
	}

	/*
	 * Si necesitas correr algún código específico al arranque de la aplicación con
	 * Spring Boot, puedes implementar la interfaz CommandLineRunner.Tiene un método
	 * run para ejecutar
	 */
	@Bean
	CommandLineRunner cargaUsuarios(UserRepository userRepository) {
		System.out.println("Cargando con CommandLineRunner desde cargaUsuarios");
		log.info("Cargando con CommandLineRunner desde cargaUsuarios");
		return x -> {
			log.info("CommandLineRunner tiene como parámetros un array de Strings: " + Arrays.toString(x));
			Arrays.asList(new User("Raul", "Admin"), new User("Juan", "User"), new User("Rosa", "Admin"),
					new User("Lucia", "User")).forEach(e -> {
						log.info(e.toString());
						userRepository.save(e);
					});
			log.info("Carga inicial finalizada.");
		};
	};
}