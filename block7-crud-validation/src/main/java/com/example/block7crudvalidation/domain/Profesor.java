package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.ProfesorFullOutputDto;
import com.example.block7crudvalidation.controller.dto.ProfesotOutputDto;
import com.example.block7crudvalidation.mapper.IPersonaMapper;
import com.example.block7crudvalidation.mapper.IStudentMapper;
import javax.persistence.*;
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
    private List<Student> studentList = new ArrayList<>();

    public ProfesotOutputDto profesorToProfesorOutputDto() {
        return new ProfesotOutputDto(
                this.idProfesor,
                this.coments,
                this.branch,
                this.studentList.stream().map(student -> IStudentMapper.mapper.studentToStudentOutputDto(student)).toList()
        );
    }

    public ProfesorFullOutputDto profesorToProfesorFullOutputDto() {
        ProfesorFullOutputDto profesorFullOut = new ProfesorFullOutputDto(IPersonaMapper.mapper.personaToPersonaOutputDto(this.persona));
        profesorFullOut.setIdProfesor(this.idProfesor);
        profesorFullOut.setComents(this.coments);
        profesorFullOut.setBranch(this.branch);
        profesorFullOut.setStudents(this.studentList.stream().map(student -> IStudentMapper.mapper.studentToStudentOutputDto(student)).toList());
        return profesorFullOut;
    }
}
