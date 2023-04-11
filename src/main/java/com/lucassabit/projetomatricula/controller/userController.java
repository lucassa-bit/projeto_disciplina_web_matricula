package com.lucassabit.projetomatricula.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lucassabit.projetomatricula.dto.client.SubjectParticipants.SubjectParticipantsCreateDTO;
import com.lucassabit.projetomatricula.dto.client.SubjectParticipants.SubjectParticipantsEditDTO;
import com.lucassabit.projetomatricula.dto.client.User.UserCreateDTO;
import com.lucassabit.projetomatricula.dto.client.User.UserEditDTO;
import com.lucassabit.projetomatricula.dto.send.SubjectParticipantsSendDTO;
import com.lucassabit.projetomatricula.dto.send.UserSendDTO;
import com.lucassabit.projetomatricula.enumerators.UserType;
import com.lucassabit.projetomatricula.error.AccessDeniedException;
import com.lucassabit.projetomatricula.error.course.CourseDoesntExistException;
import com.lucassabit.projetomatricula.error.login.DoesntExistUserTypeException;
import com.lucassabit.projetomatricula.error.login.EncodingPasswordException;
import com.lucassabit.projetomatricula.error.login.LoginAlreadyExistsException;
import com.lucassabit.projetomatricula.error.login.UserDoestExistException;
import com.lucassabit.projetomatricula.service.login.PermissionsVerifyService;
import com.lucassabit.projetomatricula.service.login.UserService;

import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class userController {
        @Autowired
        private UserService uServices;
        @Autowired
        public PermissionsVerifyService vService;

        private final String USER_CREATED = "Cadastro do usuario realizado com sucesso!";
        private final String USER_EDITED = "Edicao de usuario realizado com sucesso!";
        private final String USER_DELETED = "Delete de usuario realizado com sucesso!";

        @PostMapping("/secretary")
        @ResponseStatus(code = HttpStatus.CREATED)
        @ApiOperation(value = "Cria um novo usuário do tipo secretária")
        public ResponseEntity<String> createNewSecretary(Authentication authentication,
                        @Valid @RequestBody UserCreateDTO dto)
                        throws LoginAlreadyExistsException, EncodingPasswordException, UserDoestExistException,
                        AccessDeniedException, DoesntExistUserTypeException {
                List<UserType> cargosPermitidos = Arrays
                                .asList(new UserType[] { UserType.SECRETARY });
                vService.PermissionVerify(authentication.getName(), cargosPermitidos);

                if (dto.getPassword() == null)
                        throw new EncodingPasswordException("Senha possui valor nulo");

                uServices.createNewUsuario(dto);

                return new ResponseEntity<String>(USER_CREATED, HttpStatus.CREATED);
        }

        @GetMapping("/secretary")
        @ApiOperation(value = "Pega todos os usuários do tipo secretária cadastros no sistema")
        public List<UserSendDTO> getAllSecretary(Authentication authentication)
                        throws UserDoestExistException, AccessDeniedException, DoesntExistUserTypeException {
                List<UserType> cargosPermitidos = Arrays
                                .asList(new UserType[] { UserType.SECRETARY });
                vService.PermissionVerify(authentication.getName(), cargosPermitidos);

                return uServices.pickAllUsers();
        }

        // editar usuario
        @PutMapping("/secretary")
        @ApiOperation(value = "Edita o usuário do tipo secretária")
        public ResponseEntity<String> editSecretary(Authentication authentication, @Valid @RequestBody UserEditDTO dto)
                        throws UserDoestExistException, AccessDeniedException, LoginAlreadyExistsException,
                        DoesntExistUserTypeException {
                List<UserType> cargosPermitidos = Arrays
                                .asList(new UserType[] { UserType.SECRETARY });
                vService.PermissionVerify(authentication.getName(), cargosPermitidos);

                uServices.editarUsuario(dto);

                return new ResponseEntity<String>(USER_EDITED, HttpStatus.OK);
        }


        @PostMapping("/teacher")
        @ResponseStatus(code = HttpStatus.CREATED)
        @ApiOperation(value = "Cria um novo usuário do tipo professor")
        public ResponseEntity<String> createNewTeacher(Authentication authentication,
                        @Valid @RequestBody SubjectParticipantsCreateDTO dto)
                        throws LoginAlreadyExistsException, EncodingPasswordException, UserDoestExistException,
                        AccessDeniedException, CourseDoesntExistException, DoesntExistUserTypeException {
                List<UserType> cargosPermitidos = Arrays
                                .asList(new UserType[] { UserType.SECRETARY });
                vService.PermissionVerify(authentication.getName(), cargosPermitidos);

                if (dto.getPassword() == null)
                        throw new EncodingPasswordException("Senha possui valor nulo");

                uServices.createNewUsuario(dto, UserType.TEACHER);

                return new ResponseEntity<String>(USER_CREATED, HttpStatus.CREATED);
        }

        @GetMapping("/teacher")
        @ApiOperation(value = "Pega todos os usuários do tipo professor cadastros no sistema")
        public List<SubjectParticipantsSendDTO> getAllTeachersByCourse(Authentication authentication,
                        @RequestParam String course)
                        throws UserDoestExistException, AccessDeniedException, CourseDoesntExistException,
                        DoesntExistUserTypeException {
                List<UserType> cargosPermitidos = Arrays
                                .asList(new UserType[] { UserType.SECRETARY });
                vService.PermissionVerify(authentication.getName(), cargosPermitidos);

                return uServices.pickAllUsers(course, UserType.TEACHER);
        }


        // editar usuario e professor
        @PutMapping("/teacher")
        @ApiOperation(value = "Edita o usuário do tipo professor cadastros no sistema")
        public ResponseEntity<String> editTeacher(Authentication authentication,
                        @Valid @RequestBody SubjectParticipantsEditDTO dto)
                        throws UserDoestExistException, AccessDeniedException, LoginAlreadyExistsException,
                        EncodingPasswordException, CourseDoesntExistException, DoesntExistUserTypeException {

                List<UserType> cargosPermitidos = Arrays
                                .asList(new UserType[] { UserType.SECRETARY, UserType.TEACHER });
                vService.PermissionVerify(authentication.getName(), cargosPermitidos);

                uServices.editarUsuario(dto, UserType.TEACHER);

                return new ResponseEntity<String>(USER_EDITED, HttpStatus.OK);
        }

        @PostMapping("/student")
        @ResponseStatus(code = HttpStatus.CREATED)
        @ApiOperation(value = "Cria um novo usuário do tipo estudante")
        public ResponseEntity<String> createNewStudent(Authentication authentication,
                        @Valid @RequestBody SubjectParticipantsCreateDTO dto)
                        throws LoginAlreadyExistsException, EncodingPasswordException, UserDoestExistException,
                        AccessDeniedException, CourseDoesntExistException, DoesntExistUserTypeException {
                List<UserType> cargosPermitidos = Arrays
                                .asList(new UserType[] { UserType.SECRETARY });
                vService.PermissionVerify(authentication.getName(), cargosPermitidos);

                if (dto.getPassword() == null)
                        throw new EncodingPasswordException("Senha possui valor nulo");

                uServices.createNewUsuario(dto, UserType.STUDENT);

                return new ResponseEntity<String>(USER_CREATED, HttpStatus.CREATED);
        }

        @GetMapping("/student")
        @ApiOperation(value = "Pega todos os usuários do tipo estudante cadastros no sistema")
        public List<SubjectParticipantsSendDTO> getAllStudentsByCourse(Authentication authentication,
                        @RequestParam String course)
                        throws UserDoestExistException, AccessDeniedException, CourseDoesntExistException,
                        DoesntExistUserTypeException {
                List<UserType> cargosPermitidos = Arrays
                                .asList(new UserType[] { UserType.SECRETARY, UserType.TEACHER });
                vService.PermissionVerify(authentication.getName(), cargosPermitidos);

                return uServices.pickAllUsers(course, UserType.STUDENT);
        }

        @PutMapping("/student")
        @ApiOperation(value = "Edita o usuário do tipo estudante cadastros no sistema")
        public ResponseEntity<String> editStudent(Authentication authentication,
                        @Valid @RequestBody SubjectParticipantsEditDTO dto)
                        throws UserDoestExistException, AccessDeniedException, LoginAlreadyExistsException,
                        EncodingPasswordException, CourseDoesntExistException, DoesntExistUserTypeException {
                List<UserType> cargosPermitidos = Arrays
                                .asList(new UserType[] { UserType.SECRETARY, UserType.STUDENT });
                vService.PermissionVerify(authentication.getName(), cargosPermitidos);

                uServices.editarUsuario(dto, UserType.STUDENT);

                return new ResponseEntity<String>(USER_EDITED, HttpStatus.OK);
        }

        // pegar usuario por id
        @GetMapping
        @ApiOperation(value = "Pega o usuário pelo Id")
        public UserSendDTO getUsuarioById(Authentication authentication, @RequestParam int id)
                        throws UserDoestExistException, AccessDeniedException, DoesntExistUserTypeException {
                List<UserType> cargosPermitidos = Arrays
                                .asList(new UserType[] { UserType.SECRETARY, UserType.TEACHER });
                UserSendDTO user = vService.PermissionVerify(authentication.getName(), cargosPermitidos);

                return uServices.getUsuarioById(id, user.getUserType());
        }

        // retornar usuario logado
        @GetMapping("/me")
        @ApiOperation(value = "Pega o usuário logado no sistema")
        public UserSendDTO getUsuarioLogado(Authentication authentication)
                        throws UserDoestExistException, AccessDeniedException, DoesntExistUserTypeException {

                return uServices.getUsuarioByLogin(authentication.getName());
        }

        // deletar usuario
        @DeleteMapping
        @ApiOperation(value = "deleta um usuário pelo id")
        public ResponseEntity<String> deleteUsuario(Authentication authentication, @RequestParam Integer id)
                        throws UserDoestExistException, AccessDeniedException, DoesntExistUserTypeException {
                List<UserType> cargosPermitidos = Arrays
                                .asList(new UserType[] { UserType.SECRETARY });
                vService.PermissionVerify(authentication.getName(), cargosPermitidos);

                uServices.deleteUsuario(id);

                return new ResponseEntity<String>(USER_DELETED, HttpStatus.OK);
        }
}
