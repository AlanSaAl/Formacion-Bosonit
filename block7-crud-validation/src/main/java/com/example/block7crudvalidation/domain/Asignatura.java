package com.example.block7crudvalidation.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "estudiante_asignatura")
@Getter
@Setter
@EqualsAndHashCode
public class Asignatura {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id_asignatura")
    private String idAsignatura;

    @Column(name = "asignatura")
    private String asignatura;

    @Column(name = "comentarios")
    private String comments;

    @Column(nullable = false, name = "fecha_inicio")
    private Date initialDate;

    @Column(name = "fecha_termino")
    private Date finishDate;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;
}
