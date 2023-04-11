package com.lucassabit.projetomatricula.dto.client.Subject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SubjectCreateDTO {
    @NotBlank(message = "Erro na criação da disciplina: valor em branco/nulo (nome)")
    private String name;
    private String teacher = "";
    @NotBlank(message = "Erro na criação da disciplina: valor em branco/nulo (curso)")
    private String course;

    @NotBlank(message = "Erro na criação da disciplina: valor em branco/nulo (dia da semana)")
    @Pattern(regexp = "^(Segunda-feira|Terça-feira|Quarta-feira|Quinta-feira|Sexta-feira)$", message = "Erro na criação da disciplina: valor fora do padrão (dia da semana)")
    private String dayWeek1;
    @NotBlank(message = "Erro na criação da disciplina: valor em branco/nulo (horario aula)")
    @DateTimeFormat(pattern = "hh:mm")
    private String classTime1;

    @NotBlank(message = "Erro na criação da disciplina: valor em branco/nulo (dia da semana)")
    @Pattern(regexp = "^(Segunda-feira|Terça-feira|Quarta-feira|Quinta-feira| Sexta-feira)$", message = "Erro na criação da disciplina: valor fora do padrão (dia da semana)")
    private String dayWeek2;
    @NotBlank(message = "Erro na criação da disciplina: valor em branco/nulo (horario aula)")
    @DateTimeFormat(pattern = "hh:mm")
    private String classTime2;
}
