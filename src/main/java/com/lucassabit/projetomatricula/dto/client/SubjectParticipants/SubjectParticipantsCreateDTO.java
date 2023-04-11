package com.lucassabit.projetomatricula.dto.client.SubjectParticipants;

import javax.validation.constraints.NotNull;

import com.lucassabit.projetomatricula.dto.client.User.UserCreateDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SubjectParticipantsCreateDTO extends UserCreateDTO {
    @NotNull(message = "Erro na criação do usuario: valor em branco/nulo (curso)")
    private String course;
}
