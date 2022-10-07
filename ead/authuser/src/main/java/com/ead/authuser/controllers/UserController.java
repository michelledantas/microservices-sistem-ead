package com.ead.authuser.controllers;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController //Para o spring entender que esse é um bin que ele vai gerenciar
@CrossOrigin(origins = "*", maxAge = 3600) //Precisamos permitir que esse endPoint seja acessado de qualquer lugar e com isso. O * significa que permitimos o acesso de todas as origens. MaxAge é a quantidade de segundos que iremos permitir. Essa configuração está a nível de classe, porém se eu precisar fazer essa configuração para um método em específico para limitar o acesso é perfeitamente possível
@RequestMapping ("/users")//é a nossa URI, precisa ser um recurso bem definido para garantir o nível de maturidade do Richardson Maturity Model, para tornar essa API Restfull
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "userId")UUID userId){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (!userModelOptional.isPresent()){ //se o objeto não tiver presente, ou seja, se ele vier vazio
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser (@PathVariable (value = "userId") UUID userId){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } else {
            userService.delete(userModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
        }
    }





}
