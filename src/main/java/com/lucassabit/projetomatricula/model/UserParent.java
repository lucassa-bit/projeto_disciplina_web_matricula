package com.lucassabit.projetomatricula.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.lucassabit.projetomatricula.dto.client.User.UserCreateDTO;
import com.lucassabit.projetomatricula.dto.send.UserSendDTO;
import com.lucassabit.projetomatricula.enumerators.UserType;
import com.lucassabit.projetomatricula.error.login.EncodingPasswordException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("U")
public class UserParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String name;
    protected String email;
    protected String id_number;
    protected String login;
    protected String password;

    protected UserType userType;

    protected LocalDate birthDate;

    public UserParent(String name, String email, String id_number, String login, String password, UserType userType,
            LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.id_number = id_number;
        this.login = login;
        this.userType = userType;
        this.birthDate = birthDate;
        this.password = password;
    }

    public static UserParent fromCreateDto(UserCreateDTO dto, Course course)
            throws EncodingPasswordException {
        return new UserParent(dto.getName(), dto.getEmail(), dto.getId_number(), dto.getLogin(), dto.getPassword(),
                UserType.SECRETARY, LocalDate.parse(dto.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public UserParent fromEditDto(UserCreateDTO dto, Course course) {
        if (dto.getName() != null)
            this.name = dto.getName();
        if (dto.getBirthDate() != null)
            this.birthDate = LocalDate.parse(dto.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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

    public UserSendDTO toSendDTO() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new UserSendDTO(id, name, email, id_number, userType, birthDate.format(format));
    }
}
