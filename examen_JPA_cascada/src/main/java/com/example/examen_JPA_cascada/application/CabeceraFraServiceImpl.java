package com.example.examen_JPA_cascada.application;

import com.example.examen_JPA_cascada.domain.CabeceraFra;
import com.example.examen_JPA_cascada.domain.Cliente;
import com.example.examen_JPA_cascada.domain.LineasFra;
import com.example.examen_JPA_cascada.repository.ICabecerFraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabeceraFraServiceImpl implements ICabeceraFraService {
    @Autowired
    ICabecerFraRepository cabecerFraRepository;
    @Autowired
    ClienteServiceImpl clienteService;

    @Override
    public CabeceraFra addCabeceraFra(CabeceraFra cabeceraFra, int idCliente) {
        Cliente cliente = clienteService.getClienteById(idCliente);
        cabeceraFra.setCliente(cliente);
        return cabecerFraRepository.save(cabeceraFra);
    }

    @Override
    public CabeceraFra getFacturaById(int idFra) {
        return cabecerFraRepository.findById(idFra).orElseThrow();
    }

    @Override
    public List<CabeceraFra> getAllFacturas() {
        return cabecerFraRepository.findAll();
    }

    @Override
    public void deleteFacturaById(int id) {
        getFacturaById(id);
        cabecerFraRepository.deleteById(id);
    }

    @Override
    public CabeceraFra addLineaToFactura(LineasFra lineaFra, int idFra) {
        CabeceraFra factura = getFacturaById(idFra);
        lineaFra.setCabeceraFra(factura);
        List<LineasFra> lineasFraList = factura.getLineasFras();
        lineasFraList.add(lineaFra);
        factura.setLineasFras(lineasFraList);

        return cabecerFraRepository.save(factura);
    }
}
