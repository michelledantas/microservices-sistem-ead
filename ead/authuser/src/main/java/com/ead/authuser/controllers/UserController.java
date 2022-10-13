package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.ead.authuser.specifications.SpecificationTemplate;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController //Para o spring entender que esse é um bin que ele vai gerenciar
@CrossOrigin(origins = "*", maxAge = 3600) //Precisamos permitir que esse endPoint seja acessado de qualquer lugar e com isso. O * significa que permitimos o acesso de todas as origens. MaxAge é a quantidade de segundos que iremos permitir. Essa configuração está a nível de classe, porém se eu precisar fazer essa configuração para um método em específico para limitar o acesso é perfeitamente possível
@RequestMapping ("/users")//é a nossa URI, precisa ser um recurso bem definido para garantir o nível de maturidade do Richardson Maturity Model, para tornar essa API Restfull
public class UserController {

    @Autowired
    UserService userService;


    //Vamos fazer a implementação do hateos dentro do método getAllUsers,
    // como boas práticas o recomendavel é que o primeiro link a ser criado é para mostrar o caminho de cada um dos recursos próprios
    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllUsers(SpecificationTemplate.UserSpec spec, //precisamos receber o Specification dentro do método para funcionar
                                            @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC)
                                                       Pageable pageable){

        Page<UserModel> userModelPage = userService.findAll(spec, pageable); //também é preciso definir no método o specification

        //se a lista tiver vazia, então não é preciso retornar nada
        if(!userModelPage.isEmpty()){
            //cada um desses recursos são userModel, extraio a listagem de paginação, então assim eu consigo acessar cada um dos elementos dessa lista
            for(UserModel userModel : userModelPage.toList()) {
                //construindo link (navegação para este recurso)
                //usei o método do RepresentationModel, por isso consegui acessar o add
                //linkTo do Hateos
                //dentro do linkTo eu preciso definir o método, pra isso eu preciso passar qual é o controller que está esse método
                //e qual é este método, no caso é getOneUser
                //e eu precisei passar o id, já que esse método eu recebo um parametro na uri
                //withSelfRel ele qualifica qual é a relação desse link com esse recurso, no caso é um link para o próprio recurso, por isso SelfRel
                userModel.add(linkTo(methodOn(UserController.class).getOneUser(userModel.getUserId())).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
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


    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser (@PathVariable (value = "userId") UUID userId,
                                             @RequestBody @Validated(UserDto.UserView.UserPut.class)
                                             @JsonView(UserDto.UserView.UserPut.class) UserDto userDto){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } else {
            var userModel = userModelOptional.get();
            userModel.setFullName(userDto.getFullName());
            userModel.setPhoneNumber(userDto.getPhoneNumber());
            userModel.setCpf(userDto.getCpf());
            userModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(userModel);

            return ResponseEntity.status(HttpStatus.OK).body(userModel);
        }
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Object> updatePassword (@PathVariable (value = "userId") UUID userId,
                                                  @RequestBody @Validated(UserDto.UserView.PasswordPut.class)
                                                  @JsonView(UserDto.UserView.PasswordPut.class) UserDto userDto){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        //se a senha que está salva no banco, se é igual a senha old passowrd que o cliente está passando, porém como temos a negação
        //fica se a senha que o cliente está passando não for igual a senha velha que está salva no banco
        //então preciso informar que é incompatível
        if(!userModelOptional.get().getPassword().equals(userDto.getOldPassword())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old passoword");
        }

        else {
            var userModel = userModelOptional.get();
            userModel.setPassword(userDto.getPassword());
            userModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(userModel);

            return ResponseEntity.status(HttpStatus.OK).body("Password update successfully");
        }
    }


    @PutMapping("/{userId}/image")
    public ResponseEntity<Object> updateImage (@PathVariable (value = "userId") UUID userId,
                                               @RequestBody @Validated(UserDto.UserView.ImagePut.class)
                                               @JsonView(UserDto.UserView.ImagePut.class) UserDto userDto){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        else {
            var userModel = userModelOptional.get();
            userModel.setImageUrl(userDto.getImageUrl());
            userModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(userModel);

            return ResponseEntity.status(HttpStatus.OK).body(userModel);
        }
    }




}
