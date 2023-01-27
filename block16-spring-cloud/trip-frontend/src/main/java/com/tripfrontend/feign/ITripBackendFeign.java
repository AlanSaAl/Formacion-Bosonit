package com.tripfrontend.feign;

import com.tripfrontend.controller.dto.ClienteOutputDto;
import com.tripfrontend.controller.dto.ViajeOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://tripbackend:8080", name = "tripBackend")
public interface ITripBackendFeign {
    @GetMapping("cliente/obtener/{idCliente}")
    ClienteOutputDto getClienteById(@PathVariable int idCliente);

    @GetMapping("viaje/obtener/{idViaje}")
    ViajeOutputDto getViajeById(@PathVariable int idViaje);
}
