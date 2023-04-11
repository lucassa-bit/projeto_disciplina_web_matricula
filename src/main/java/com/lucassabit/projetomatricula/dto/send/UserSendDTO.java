package com.lucassabit.projetomatricula.dto.send;

import com.lucassabit.projetomatricula.enumerators.UserType;

import lombok.Data;

@Data
public class UserSendDTO {
    protected Integer id;
    protected String name;
    protected String email;
    protected String id_number;

    protected UserType userType;
    protected String birthDate;

    public UserSendDTO(Integer id, String name, String email, String id_number, UserType userType, String birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.id_number = id_number;
        this.userType = userType;
        this.birthDate = birthDate;
    }
}
