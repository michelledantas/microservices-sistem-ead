package com.ead.authuser.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;


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

    //essa anotação serve para informar qual tipo de visão terá o atributo
    @JsonView(UserView.RegistrationPost.class) // neste caso esse campo não pode ser alterado, por isso a visão fica disponível apenas no momento do cadastro
    private String username;

    @JsonView(UserView.RegistrationPost.class)
    private String email;

    @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
    private String password;

    @JsonView(UserView.PasswordPut.class)
    private String oldPassword;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String fullName;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String phoneNumber;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String cpf;

    @JsonView(UserView.ImagePut.class)
    private String imageUrl;
}
