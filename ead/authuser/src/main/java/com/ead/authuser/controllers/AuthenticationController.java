package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody
                                                   @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto){

        //Ao incluir essa anotação @JsonView(UserDto.UserView.RegistrationPost.class, ele vai olhar apenas para essa visão de RegistrationPost



        if(userService.existsByUserName(userDto.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is Already Taken!");
        }
        if(userService.existsByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is Already Taken!");
        }

        /*Precisamos converter UserDto em UserModel, pra isso vamos utilizar do bin utils
        mas antes disso preciso criar uma instancia do user model
        novo scopo que veio com o JDK 11 onde não preciso colocar o tipo dessa instância, dessa forma ele já identifica automaticamente
        só posso utilizar essa forma se estiver dentro do scopo do método, sempre usar em escopo fechado*/
        var userModel = new UserModel();
        //nesse método eu converso o UserDto em UserModel
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);

    }

}
