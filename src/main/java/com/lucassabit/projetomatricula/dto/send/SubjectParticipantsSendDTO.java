package com.lucassabit.projetomatricula.dto.send;

import com.lucassabit.projetomatricula.enumerators.UserType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SubjectParticipantsSendDTO extends UserSendDTO {
    private String course;
    private String registration;

    public SubjectParticipantsSendDTO(Integer id, String name, String email, String id_number, String login,
            String password, UserType userType, String birthDate, String course, String registration) {
        super(id, name, email, id_number, userType, birthDate);
        this.course = course;
        this.registration = registration;
    }
}
