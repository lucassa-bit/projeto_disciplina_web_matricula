package com.lucassabit.projetomatricula.dto.client.Subject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

public class SubjectEditDTO {
    @NotNull(message = "Erro na criação da disciplina: valor em branco/nulo (id)")
    private Integer id;
    @NotBlank(message = "Erro na criação da disciplina: valor em branco/nulo (nome)")
    private String name;
    private String teacher = "";

    @NotBlank(message = "Erro na criação da disciplina: valor em branco/nulo (dia da semana)")
    @Pattern(regexp = "^(Segunda-feira|Terça-feira|Quarta-feira|Quinta-feira|Sexta-feira)$", message = "Erro na criação da disciplina: valor fora do padrão (dia da semana)")
    private String dayWeek1;
    @NotBlank(message = "Erro na criação da disciplina: valor em branco/nulo (horario aula)")
    @DateTimeFormat(pattern = "HH:mm")
    private String classTime1;

    @NotBlank(message = "Erro na criação da disciplina: valor em branco/nulo (dia da semana)")
    @Pattern(regexp = "^(Segunda-feira|Terça-feira|Quarta-feira|Quinta-feira|Sexta-feira)$", message = "Erro na criação da disciplina: valor fora do padrão (dia da semana)")
    private String dayWeek2;
    @NotBlank(message = "Erro na criação da disciplina: valor em branco/nulo (horario aula)")
    @DateTimeFormat(pattern = "HH:mm")
    private String classTime2;

    public SubjectEditDTO() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getDayWeek1() {
        return dayWeek1;
    }

    public String getClassTime1() {
        return classTime1;
    }

    public String getClassTime2() {
        return classTime2;
    }

    public String getDayWeek2() {
        return dayWeek2;
    }
}
