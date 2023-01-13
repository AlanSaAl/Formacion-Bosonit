package com.example.block7crudvalidation;

import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@SpringBootApplication
public class Block7CrudValidationApplication {
	@Autowired
	IPersonaRepository personaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Block7CrudValidationApplication.class, args);
	}

	@PostConstruct
	public void crearUsuarioInicial() {
		Persona admin = new Persona();
		admin.setName("admin");
		admin.setUsuario("admin");
		admin.setPassword(new BCryptPasswordEncoder().encode("1234"));
		admin.setAdmin(true);

		personaRepository.save(admin);
	}
}
