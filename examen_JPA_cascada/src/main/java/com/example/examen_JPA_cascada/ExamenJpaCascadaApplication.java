package com.example.examen_JPA_cascada;

import com.example.examen_JPA_cascada.application.CabeceraFraServiceImpl;
import com.example.examen_JPA_cascada.application.ClienteServiceImpl;
import com.example.examen_JPA_cascada.application.LineasFraServiceImpl;
import com.example.examen_JPA_cascada.controller.dto.ClienteInputDto;
import com.example.examen_JPA_cascada.controller.dto.FacturaInputDto;
import com.example.examen_JPA_cascada.controller.dto.LineaInputDto;
import com.example.examen_JPA_cascada.domain.CabeceraFra;
import com.example.examen_JPA_cascada.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ExamenJpaCascadaApplication implements CommandLineRunner {
	@Autowired
	ClienteServiceImpl clienteService;
	@Autowired
	CabeceraFraServiceImpl cabeceraFraService;
	@Autowired
	LineasFraServiceImpl lineasFraService;

	public static void main(String[] args) {
		SpringApplication.run(ExamenJpaCascadaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cliente cliente = clienteService.addCliente(new ClienteInputDto("Alan"));

		CabeceraFra factura = cabeceraFraService.addCabeceraFra(new FacturaInputDto(cliente.getId(), 100.00));

		LineaInputDto lineaInicialUno = new LineaInputDto(factura.getId(), "coca-cola", 1.00, 15.00);
		LineaInputDto lineaInicialDos = new LineaInputDto(factura.getId(), "Sabritas moradas", 1.00, 17.00);
		List<LineaInputDto> listaDeLineas = new ArrayList<>();

		listaDeLineas.add(lineaInicialUno);
		listaDeLineas.add(lineaInicialDos);
		lineasFraService.addLineaFra(listaDeLineas);
	}
}
