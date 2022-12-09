package com.example.examen_JPA_cascada.domain;

import com.example.examen_JPA_cascada.controller.dto.FacturaOutputDto;
import com.example.examen_JPA_cascada.mapper.IClienteMapper;
import com.example.examen_JPA_cascada.mapper.ILineasFraMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
public class CabeceraFra {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    @JoinColumn(name = "cli_codi")
    Cliente cliente;

    @Column(name = "importe_fra")
    Double importeFra;

    @OneToMany(mappedBy = "cabeceraFra", cascade = CascadeType.ALL)
    List<LineasFra> lineasFras = new ArrayList<>();

    public FacturaOutputDto cabeceraFraToFacturaOutputDto() {
        return new FacturaOutputDto(
                this.id,
                this.importeFra,
                IClienteMapper.mapper.clienteToClienteOutputDto(this.cliente),
                this.lineasFras.stream().map(lineasFra -> ILineasFraMapper.mapper.lineasFraToLineaOutputDto(lineasFra)).toList()
        );
    }
}
