package com.example.block6personcontrollers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Block6PersonControllersApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block6PersonControllersApplication.class, args);
	}

	@Bean("listaCiudades")
	List<Ciudad> crearLista(){
		return new ArrayList<>();
	}

	@Bean("bean1")
	Persona persona1() {
		Persona persona = new Persona();
		persona.setNombre("jimmy");
		persona.setPoblacion("edoMex");
		persona.setEdad(14);
		return persona;
	}

	@Bean("bean2")
	Persona persona2() {
		Persona persona = new Persona();
		persona.setNombre("Alan");
		persona.setPoblacion("mex");
		persona.setEdad(1);
		return persona;
	}

	@Bean("bean3")
	Persona persona3() {
		Persona persona = new Persona();
		persona.setNombre("juan");
		persona.setPoblacion("queretaro");
		persona.setEdad(7);
		return persona;
	}
}
