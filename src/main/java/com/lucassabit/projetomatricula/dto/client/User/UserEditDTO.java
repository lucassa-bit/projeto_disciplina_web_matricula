package com.lucassabit.projetomatricula.dto.client.User;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserEditDTO {
    @NotNull(message = "Erro na edição do Usuário: usuário não existe")
    private Integer id;

    protected String name;

    @Pattern(regexp = "^([a-zA-Z0-9]+(?:[._-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,})$")
    protected String email;

    protected String id_number;

    protected String login;
    protected String password;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    protected String birthDate;
}
