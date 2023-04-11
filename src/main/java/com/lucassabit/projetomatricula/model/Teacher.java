package com.lucassabit.projetomatricula.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.lucassabit.projetomatricula.dto.client.SubjectParticipants.SubjectParticipantsCreateDTO;
import com.lucassabit.projetomatricula.dto.client.SubjectParticipants.SubjectParticipantsEditDTO;
import com.lucassabit.projetomatricula.dto.send.SubjectParticipantsSendDTO;
import com.lucassabit.projetomatricula.enumerators.UserType;
import com.lucassabit.projetomatricula.error.login.EncodingPasswordException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "T")
public class Teacher extends UserParent {
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Subject> subjects;

    @ManyToOne
    private Course course;
    private String registerCode;

    public Teacher(String name, String email, String id_number, String login, String password, UserType userType,
            LocalDate birthDate, Course course) {
        super(name, email, id_number, login, password, userType, birthDate);
        this.course = course;
    }

    public static Teacher fromCreateDto(SubjectParticipantsCreateDTO dto, Course course)
            throws EncodingPasswordException {
        return new Teacher(dto.getName(), dto.getEmail(), dto.getId_number(), dto.getLogin(), dto.getPassword(),
                UserType.TEACHER, LocalDate.parse(dto.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), course);
    }

    public Teacher fromEditDto(SubjectParticipantsEditDTO dto, Course course) {
        if (dto.getName() != null)
            this.name = dto.getName();
        if (dto.getBirthDate() != null)
            this.birthDate = LocalDate.parse(dto.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (dto.getCourse() != null)
            this.course = course;
        if (dto.getEmail() != null)
            this.email = dto.getEmail();
        if (dto.getId_number() != null)
            this.id_number = dto.getId_number();
        if (dto.getLogin() != null)
            this.login = dto.getLogin();
        if (dto.getPassword() != null)
            this.password = dto.getPassword();

        return this;
    }

    public SubjectParticipantsSendDTO toSendDTO() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new SubjectParticipantsSendDTO(id, name, email, id_number, login, password, userType,
                birthDate.format(format), course.getName(),
                registerCode);
    }
}
