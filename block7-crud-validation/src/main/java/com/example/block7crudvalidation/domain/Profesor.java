package com.example.block7crudvalidation.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Profesor {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id_profesor")
    private String idProfesor;
    @Column(name = "comentarios")
    private String coments;
    @Column(nullable = false, name = "rama")
    private String branch;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona")
    private Persona persona;
    @OneToMany(mappedBy = "profesor")
    private List<Student> students = new ArrayList<>();
}
