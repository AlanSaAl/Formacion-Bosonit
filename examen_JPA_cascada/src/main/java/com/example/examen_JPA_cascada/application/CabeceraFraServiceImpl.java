package com.example.examen_JPA_cascada.application;

import com.example.examen_JPA_cascada.controller.dto.ClienteOutputDto;
import com.example.examen_JPA_cascada.controller.dto.FacturaInputDto;
import com.example.examen_JPA_cascada.controller.dto.FacturaOutputDto;
import com.example.examen_JPA_cascada.domain.CabeceraFra;
import com.example.examen_JPA_cascada.domain.Cliente;
import com.example.examen_JPA_cascada.domain.LineasFra;
import com.example.examen_JPA_cascada.mapper.ICabeceraFraMapper;
import com.example.examen_JPA_cascada.repository.ICabecerFraRepository;
import com.example.examen_JPA_cascada.repository.ILineasFraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CabeceraFraServiceImpl implements ICabeceraFraService {
    @Autowired
    ICabecerFraRepository cabecerFraRepository;
    @Autowired
    ClienteServiceImpl clienteService;
    @Autowired
    ILineasFraRepository lineasFraRepository;
    @Autowired
    LineasFraServiceImpl lineasFraService;

    @Override
    public CabeceraFra addCabeceraFra(FacturaInputDto cabeceraFraInput) {
        return cabecerFraRepository.save(ICabeceraFraMapper.mapper.facturaInputDtoToCabeceraFra(cabeceraFraInput));
    }

    /*@Override
    public CabeceraFra addCabeceraFra(int idCliente, FacturaInputDto cabeceraFraInput) {
        // relacionar la factura que se creo con el cliente
        Cliente cliente = clienteService.getClienteById(idCliente);
        CabeceraFra factura = ICabeceraFraMapper.mapper.facturaInputDtoToCabeceraFra(cabeceraFraInput);
        List<CabeceraFra> listaFacturas = cliente.getFactura();
        listaFacturas.add(factura);
        cliente.setFactura(listaFacturas);
        factura.setCliente(cliente);

        clienteService.addCliente(IClienteMapper.mapper.clienteToClienteInputDto(cliente));

        return cabecerFraRepository.save(factura);
    }*/

    @Override
    public CabeceraFra getFacturaById(int id) {
        return cabecerFraRepository.findById(id).orElseThrow();
    }

    @Override
    public List<FacturaOutputDto> getAllFacturas() { // Falta devolver los otros objetos
        List<CabeceraFra> cabeceraFraList = cabecerFraRepository.findAll();
        List<FacturaOutputDto> facturaOutputDtoList = new ArrayList<>();

        for (CabeceraFra fra : cabeceraFraList) {
            ClienteOutputDto clienteOutputDto = clienteService.getClienteById(fra.getCliCodi());
            FacturaOutputDto facturaOutputDto = ICabeceraFraMapper.mapper.cabeceraFraToFacturaOutputDto(fra);
            facturaOutputDto.setClienteOutput(clienteOutputDto);
            facturaOutputDtoList.add(facturaOutputDto);
            facturaOutputDto.setLineaOutputDto(lineasFraService.getAllLineasByIdFra(fra.getId()));
        }

        return facturaOutputDtoList;
    }

    @Override
    public void deleteFacturaById(int id) {
        cabecerFraRepository.findById(id).orElseThrow();
        List<LineasFra> lineasFraList = lineasFraRepository.findByIdFra(id);
        lineasFraList.forEach(fra -> lineasFraRepository.deleteById(fra.getIdLinea()));
        cabecerFraRepository.deleteById(id);
    }
}
