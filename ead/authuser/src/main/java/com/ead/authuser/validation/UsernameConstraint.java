package com.ead.authuser.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameConstraintImpl.class) //nessa anotação é preciso passar qual é a classe que vai conter essa validação em específico.
// Estamos criando apenas a anotação, então precisamos passar a classe que vai implementar essa interface onde faremos o método com a validação
@Target({ElementType.METHOD, ElementType.FIELD}) //no Target é onde informamos onde que podemos utilizar essa anotação, se é no método ou no campo, ou nos 2
@Retention(RetentionPolicy.RUNTIME) //Retention é quando que essa validação vai ocorrer, no caso é em tempo de execução.
public @interface UsernameConstraint {
    //parametros default do bean validation
    String message() default "Invalid username"; //mensagem que vai ser exibida quando ocorrer o erro
    Class<?>[] groups() default {}; //grupo de validação se precisar definir
    Class<? extends Payload> [] payload() default {}; //é o nível que vai ocorrer tal erro

}
