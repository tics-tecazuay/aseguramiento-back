package com.sistema.examenes;

import com.sistema.examenes.services.Archivoservices;
import com.sistema.examenes.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;
@EnableScheduling
@SpringBootApplication
public class SistemaExamenesBackendApplication implements CommandLineRunner {
	
@Resource
	Archivoservices servis;
	public static void main(String[] args) {
		SpringApplication.run(SistemaExamenesBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		servis.init();
	}
}
