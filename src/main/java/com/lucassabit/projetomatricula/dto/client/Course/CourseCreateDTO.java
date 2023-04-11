package com.lucassabit.projetomatricula.dto.client.Course;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CourseCreateDTO {
    @NotBlank(message = "Erro na criação do curso: valor em branco/nulo (nome)")
    public String name;
}
