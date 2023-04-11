package com.lucassabit.projetomatricula.dto.client.Subject;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegisterSubjectsDTO {
    @NotBlank(message = "Erro na atualização das notas: valor em branco/nulo (matricula do estudante)")
    private String registerCodeStudent;
    @NotNull(message = "Erro na atualização das notas: valor em branco/nulo (matricula da disciplina)")
    private List<String> registerCodeSubject;
}
