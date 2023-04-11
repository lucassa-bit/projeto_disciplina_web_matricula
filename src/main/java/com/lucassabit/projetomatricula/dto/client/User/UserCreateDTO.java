package com.lucassabit.projetomatricula.dto.client.User;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserCreateDTO {
    @NotBlank(message = "Erro na criação do usuario: valor em branco/nulo (nome)")
    protected String name;

    @NotBlank(message = "Erro na criação do usuario: valor em branco/nulo (email)")
    @Pattern(regexp = "^([a-zA-Z0-9]+(?:[._-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,})$")
    protected String email;

    @NotBlank(message = "Erro na criação do usuario: valor em branco/nulo (identidade)")
    protected String id_number;

    @NotBlank(message = "Erro na criação do usuario: valor em branco/nulo (login)")
    protected String login;
    
    @NotBlank(message = "Erro na criação do usuario: valor em branco/nulo (email)")
    @JsonProperty(value = "password")
    protected String password;

    @NotBlank(message = "Erro na criação do usuario: valor em branco/nulo (data de nascimento)")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    protected String birthDate;
}