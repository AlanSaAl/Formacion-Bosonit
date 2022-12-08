package com.example.block7crudvalidation.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Student {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id_student")
    private String idStudent;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona")
    private Persona persona;
    @Column(nullable = false, name = "num_hours_week")
    private int numHoursWeek;
    @Column(name = "comentarios")
    private String coments;
    @Column(nullable = false, name = "rama")
    private String branch;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;
    @OneToMany(mappedBy = "student")
    private List<Asignatura> asignatura = new ArrayList<>();
}
