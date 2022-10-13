package com.ead.authuser.specifications;

import com.ead.authuser.models.UserModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

//esta classe é onde vamos definir os specifications (filtros)
public class SpecificationTemplate {

    //essas são as anotações da biblioteca de Specifications
    @And({
        @Spec(path="userType", spec= Equal.class), //o parâmetro path é o atributo desta entidade que vamos filtrar
        @Spec(path="userStatus", spec= Equal.class), //equal vai identificar o valor exato
        @Spec(path="cpf", spec= Equal.class),
        @Spec(path="email", spec= Like.class)}) //like vai buscar se tem algum dado com aquele valor
    public interface UserSpec extends Specification<UserModel>{}
}
