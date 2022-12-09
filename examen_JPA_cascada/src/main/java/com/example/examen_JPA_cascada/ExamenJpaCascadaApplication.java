package com.example.examen_JPA_cascada;

import com.example.examen_JPA_cascada.application.CabeceraFraServiceImpl;
import com.example.examen_JPA_cascada.application.ClienteServiceImpl;
import com.example.examen_JPA_cascada.domain.CabeceraFra;
import com.example.examen_JPA_cascada.domain.Cliente;
import com.example.examen_JPA_cascada.domain.LineasFra;
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

	public static void main(String[] args) {
		SpringApplication.run(ExamenJpaCascadaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cliente cliente = new Cliente();
		cliente.setNombre("Alan");
		cliente = clienteService.addCliente(cliente);

		CabeceraFra factura = new CabeceraFra();

		LineasFra lineaUno = new LineasFra();
		lineaUno.setProNomb("coca-cola");
		lineaUno.setCantidad(1.00);
		lineaUno.setPrecio(15.00);
		lineaUno.setCabeceraFra(factura);

		LineasFra lineaDos = new LineasFra();
		lineaDos.setProNomb("Sabritas moradas");
		lineaDos.setCantidad(1.00);
		lineaDos.setPrecio(17.00);
		lineaDos.setCabeceraFra(factura);

		List<LineasFra> lineasLista = new ArrayList<>();
		lineasLista.add(lineaUno);
		lineasLista.add(lineaDos);

		factura.setImporteFra(12.00);
		factura.setLineasFras(lineasLista);
		cabeceraFraService.addCabeceraFra(factura, cliente.getId());
	}
}
