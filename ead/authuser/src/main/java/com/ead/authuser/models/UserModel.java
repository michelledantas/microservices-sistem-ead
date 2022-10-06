package com.ead.authuser.models;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //essa anotação a nível de classe, toda a vez que for preciso fazer a serialização de objetos java para json ele vai ocultar atributos que estiverem com valores nulos
@Entity //anotação para definir que vai ser uma entidade JPA
@Table(name = "TB_USERS")  //anotação para informar o nome da tabela que vai ser criada no banco de dados
//ao implementar a classe Serializable ela vai fazer a conversao de objetos java para uma sequencia de bytes que podem ser salvos no banco de dados
public class UserModel implements Serializable {

    /*é como se fosse um número de controle de versionamento dessas conversões feitas pela JVM,
    a JVM vai utilizar desse número para fazer o controle e fazer a comparação para saber se aqueles números convertidos são realmente dessa classe
    Basicamente a JVM vai vincular esse serial com cada classe que é serializavel*/
    private static final long serialVersionUID = 1L;

    /*UUID é mto utilizado pois é um id único, gerado universalmente, id temporal,
    então a probalidade de gerar 2 id's identicos é mto insignificante,
    muito utilizado em sistemas distribuídos*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, length = 255)
    @JsonIgnore //como o passoword é um campo restríto ao usuário, sempre que for serializar esses dados via json esse campo seja ignorado, dessa forma garantimos a segurança e não expomos o usuário
    private String password;
    @Column(nullable = false, length = 150)
    private String fullName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING) //para salvar no banco de dados como uma string
    private UserStatus userStatus;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column(length = 20)
    private String phoneNumber;
    @Column(length = 20)
    private String cpf;
    @Column
    private String imageUrl;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastUpdate;
}
