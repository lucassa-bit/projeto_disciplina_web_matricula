package com.lucassabit.projetomatricula.dto.client.SubjectParticipants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lucassabit.projetomatricula.dto.client.User.UserEditDTO;
import com.lucassabit.projetomatricula.enumerators.UserType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SubjectParticipantsEditDTO extends UserEditDTO{
    @NotNull(message = "Erro na criação do usuario: valor nulo (cargo)")
    private UserType userType;
    @NotBlank(message = "Erro na edição do usuario: valor em branco/nulo (curso)")
    private String course;
}
