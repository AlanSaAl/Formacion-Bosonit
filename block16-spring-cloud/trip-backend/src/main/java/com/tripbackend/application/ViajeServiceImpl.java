package com.tripbackend.application;

import com.tripbackend.controller.dto.ClienteOutputDto;
import com.tripbackend.controller.dto.ViajeInputDto;
import com.tripbackend.controller.dto.ViajeOutputDto;
import com.tripbackend.domain.Cliente;
import com.tripbackend.domain.Viaje;
import com.tripbackend.mapper.IViajeMapper;
import com.tripbackend.repository.IClienteRepository;
import com.tripbackend.repository.IViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViajeServiceImpl implements IViajeService {
    @Autowired
    IViajeRepository viajeRepository;
    @Autowired
    IClienteRepository clienteRepository;

    @Override
    public ViajeOutputDto addViaje(ViajeInputDto viajeInput) {
        Viaje viaje = IViajeMapper.mapper.viajeInputDtoToViaje(viajeInput);
        return IViajeMapper.mapper.viajeToViajeOutputDto(viajeRepository.save(viaje));
    }

    @Override
    public ViajeOutputDto addPasajero(int idViaje, int idPasajero) {
        Cliente pasajero = clienteRepository.findById(idPasajero).orElseThrow();
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow();

        List<Cliente> listaPasajeros = viaje.getPasajeros();
        listaPasajeros.add(pasajero);

        viaje.setPasajeros(listaPasajeros);
        viajeRepository.save(viaje);

        return IViajeMapper.mapper.viajeToViajeOutputDto(viaje);
    }

    @Override
    public Iterable<ViajeOutputDto> getAllViajes(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return viajeRepository.findAll().stream().map(IViajeMapper.mapper::viajeToViajeOutputDto).toList();
    }

    @Override
    public ViajeOutputDto getViajeById(int idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow();
        return IViajeMapper.mapper.viajeToViajeOutputDto(viaje);
    }

    @Override
    public ViajeOutputDto modifyViajeById(int idViaje, ViajeInputDto viajeInput) {
        getViajeById(idViaje);
        viajeInput.setIdViaje(idViaje);
        return addViaje(viajeInput);
    }

    @Override
    public void deleteViajeById(int idViaje) {
        getViajeById(idViaje);
        viajeRepository.deleteById(idViaje);
    }
}
