package com.lucassabit.projetomatricula.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "course")
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "course")
    private List<Subject> subjects;

    public Course(String name) {
        this.name = name;
    }
}
