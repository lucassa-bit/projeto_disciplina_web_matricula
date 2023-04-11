package com.lucassabit.projetomatricula.dto.client.Subject;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ChangeGradesDTO {
    @NotBlank(message = "Erro na atualização das notas: valor em branco/nulo (matricula do estudante)")
    private String registerCodeStudent;
    @NotBlank(message = "Erro na atualização das notas: valor em branco/nulo (matricula da disciplina)")
    private String registerCodeSubject;
    private Double grade_1;
    private Double grade_2;
    private Double final_grade;
}
