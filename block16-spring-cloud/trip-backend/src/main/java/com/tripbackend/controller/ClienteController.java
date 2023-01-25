package com.tripbackend.controller;

import com.tripbackend.application.IClienteService;
import com.tripbackend.controller.dto.ClienteInputDto;
import com.tripbackend.controller.dto.ClienteOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cliente")
public class ClienteController {
    @Autowired
    IClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteOutputDto> addCliente(@RequestBody ClienteInputDto clienteInput) {
        return ResponseEntity.ok().body(clienteService.addCliente(clienteInput));
    }

    @GetMapping
    public Iterable<ClienteOutputDto> getAllClientes(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                     @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return clienteService.getAllClientes(pageNumber, pageSize);
    }

    @GetMapping("obtener/{idCliente}")
    public ClienteOutputDto getClienteById(@PathVariable int idCliente) {
        return clienteService.getClienteById(idCliente);
    }

    @PutMapping("modificar/{idCliente}")
    public ResponseEntity<ClienteOutputDto> modifyClienteById(@PathVariable int idCliente, @RequestBody ClienteInputDto clienteInput) {
        return ResponseEntity.ok().body(clienteService.modifyClienteById(idCliente, clienteInput));
    }

    @DeleteMapping("borrar/{idCliente}")
    public void deleteClienteById(@PathVariable int idCliente) {
        clienteService.deleteClienteById(idCliente);
    }
}
