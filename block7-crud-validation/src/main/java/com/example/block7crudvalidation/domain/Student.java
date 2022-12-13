package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.StudentFullOutputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.mapper.IAsignaturaMapper;
import com.example.block7crudvalidation.mapper.IPersonaMapper;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

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
    private List<Asignatura> asignaturaList = new ArrayList<>();

    public StudentOutputDto studentToStudentOutputDto() {
        return new StudentOutputDto(
                this.idStudent,
                this.numHoursWeek,
                this.coments,
                this.branch,
                this.asignaturaList.stream().map(asignatura -> IAsignaturaMapper.mapper.asignaturaToAsignaturaOutputDto(asignatura)).toList()
        );
    }

    public StudentFullOutputDto studentToStudentFullOutputDto() {
        StudentFullOutputDto studentFullOut = new StudentFullOutputDto(IPersonaMapper.mapper.personaToPersonaOutputDto(this.persona));
        studentFullOut.setIdStudent(this.idStudent);
        studentFullOut.setNumHoursWeek(this.numHoursWeek);
        studentFullOut.setComents(this.coments);
        studentFullOut.setBranch(this.branch);
        studentFullOut.setAsignaturas(this.asignaturaList.stream().map(asignatura -> IAsignaturaMapper.mapper.asignaturaToAsignaturaOutputDto(asignatura)).toList());

        return studentFullOut;
    }
}
