package com.lucassabit.projetomatricula.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;

import com.lucassabit.projetomatricula.dto.client.Subject.SubjectCreateDTO;
import com.lucassabit.projetomatricula.dto.client.Subject.SubjectEditDTO;
import com.lucassabit.projetomatricula.dto.send.SubjectSendDTO;
import com.lucassabit.projetomatricula.dto.send.StudentSubjectSendDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String registerCode;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "subject")
    private List<Grade> students;

    @ManyToOne
    private Course course;
    private String dayWeek1;
    private LocalTime classTime1;
    private String dayWeek2;
    private LocalTime classTime2;

    public Subject(String name, Teacher teacher, Course course, String dayWeek1, LocalTime classTime1,
            String dayWeek2, LocalTime classTime2) {
        this.name = name;
        this.teacher = teacher;
        this.course = course;
        this.classTime1 = classTime1;
        this.classTime2 = classTime2;
        this.dayWeek1 = dayWeek1;
        this.dayWeek2 = dayWeek2;
    }

    public static Subject fromCreateDto(SubjectCreateDTO dto, Teacher teacher, Course course) {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("HH:mm");
        return new Subject(dto.getName(), teacher, course, dto.getDayWeek1(),
                LocalTime.parse(dto.getClassTime1(), date), dto.getDayWeek2(),
                LocalTime.parse(dto.getClassTime2(), date));
    }

    public Subject fromEditDto(SubjectEditDTO dto, Teacher teacher) {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("HH:mm");

        this.name = dto.getName();
        if (teacher != null)
            this.teacher = teacher;

        this.dayWeek1 = dto.getDayWeek1();
        this.classTime1 = LocalTime.parse(dto.getClassTime1(), date);

        this.dayWeek2 = dto.getDayWeek2();
        this.classTime2 = LocalTime.parse(dto.getClassTime2(), date);

        return this;
    }

    public SubjectSendDTO toSendDTO() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("HH:mm");
        return new SubjectSendDTO(id, name, this.teacher != null ? teacher.getName() : "",
                students.parallelStream().map((value) -> value.getStudent().toSendDTO())
                        .sorted((a, b) -> comparevalue(a.getId(), b.getId())).toList(),
                course.getName(),
                registerCode, dayWeek1,
                date.format(classTime1), dayWeek2, date.format(classTime2));
    }

    public StudentSubjectSendDTO toSubjectStudentDTO(Student student, Grade grade) {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("HH:mm");
        return new StudentSubjectSendDTO(id, name, teacher != null ? teacher.getName() : "", student.toSendDTO(),
                course.getName(), registerCode, dayWeek1,
                date.format(classTime1), dayWeek2, date.format(classTime2), grade.getNota_parcial_1(),
                grade.getNota_parcial_2(),
                grade.getNota_final());
    }
    
    private int comparevalue(Integer value1, Integer value2) {
        if (value1 > value2)
            return 1;
        else if (value1 < value2)
            return -1;
        else
            return 0;
    }
}