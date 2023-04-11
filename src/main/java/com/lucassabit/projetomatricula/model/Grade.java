package com.lucassabit.projetomatricula.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.lucassabit.projetomatricula.dto.client.Subject.ChangeGradesDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Student student;

    private double nota_parcial_1;
    private double nota_parcial_2;
    private double nota_final;

    public Grade(Subject subject, Student student) {
        this.subject = subject;
        this.student = student;
        this.nota_parcial_1 = 0;
        this.nota_parcial_2 = 0;
        this.nota_final = 0;
    }

    public Grade fromGradeDTO(ChangeGradesDTO dto) {
        this.nota_parcial_1 = dto.getGrade_1() != null ? dto.getGrade_2() : 0;
        this.nota_parcial_2 = dto.getGrade_2() != null ? dto.getGrade_2() : 0;
        this.nota_final = dto.getFinal_grade() != null ? dto.getFinal_grade() : 0;

        return this;
    }
}
