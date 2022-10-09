package com.ead.authuser.dtos;

import com.ead.authuser.validation.CpfConstraint;
import com.ead.authuser.validation.UsernameConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    //Essa interface foi criada para evitar ficar criando um DTO para cada tipo de ação que iremos fazer
    // Em cada uma das interfaces de acordo com os dados vão ser visualizados em cada uma delas
    //Exemplo no método para atualizar o usuário ele vai olhar para todos os campos que estiver com a anotação do JsonView com o método UserPut
    public interface UserView {
        interface RegistrationPost{}
        interface UserPut{}
        interface PasswordPut{}
        interface ImagePut{}

    }

    //dentro de groups, colocamos em quais visões do Json view que iremos fazer as validações
    @NotBlank(groups = UserView.RegistrationPost.class) //não permite valores nulos e tbm não permite valores vazios
    //essa anotação serve para informar qual tipo de visão terá o atributo
    @Size(min=4, max=50, groups = UserView.RegistrationPost.class) //validação com relação ao tamanho de caracteres
    @UsernameConstraint(groups = UserView.RegistrationPost.class)
    @JsonView(UserView.RegistrationPost.class) // neste caso esse campo não pode ser alterado, por isso a visão fica disponível apenas no momento do cadastro
    private String username;

    @NotBlank(groups = UserView.RegistrationPost.class)
    @Email(groups = UserView.RegistrationPost.class)
    @JsonView(UserView.RegistrationPost.class)
    private String email;

    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
    @Size(min=6, max=20, groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
    @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
    private String password;

    @NotBlank(groups = UserView.PasswordPut.class)
    @Size(min=6, max=20, groups = UserView.PasswordPut.class)
    @JsonView(UserView.PasswordPut.class)
    private String oldPassword;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String fullName;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String phoneNumber;

    @CpfConstraint(groups = {UserView.RegistrationPost.class, UserView.UserPut.class})
    @Size(min=11, max=11, groups = {UserView.RegistrationPost.class, UserView.UserPut.class})
    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String cpf;

    @NotBlank(groups = UserView.ImagePut.class)
    @JsonView(UserView.ImagePut.class)
    private String imageUrl;
}
