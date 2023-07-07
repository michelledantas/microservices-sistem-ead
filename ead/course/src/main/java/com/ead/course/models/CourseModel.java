package com.ead.course.models;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name="TB_COURSES")
public class CourseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID courseId;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false, length = 250)
    private String description;
    @Column
    private String imageUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime lastUpdateDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseLevel courseLevel;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;
    @Column(nullable = false)
    private UUID userInstructor;

    /*Relacionamento entre curso e modulo
    * 1 curso pode ter vários módulos
    * Representando por meio do JPA
    * dentro de mappedBy é onde passamos o atributo que vai ser a chave estrangeira
    * O Hibernate vai utilizar dessa definição para fazer a associação entre um e outro*/

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // SEMPRE QUE FORMOS FAZER UM CONSULTA GETALL CURSOS ELE VAI IGNORAR ESSES CAMPOS, terá apenas o acesso de escrita
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY) //FetchType.Lazy é um carregamento dos dados apenas quando for necessário, ou seja, de forma lenta dessa forma não prejudica a peformance, já o carregamento Eager é considerado uma forma ansiosa, pois sempre traz os dados, essa modalidade é recomendado usar quando é todos os dados serão utilizados. Essa é uma estrastégia estática
    @Fetch(FetchMode.SUBSELECT)
    private Set<ModuleModel> modules;

    /*Pq foi usado um set ao invés de um list?
    * Considerando esses dois tipos Java.
    * O SET não é ordenado, porém não permite duplicadas
    * LIST é uma lista ordenada e permite duplicadas
    *
    * Como que o HIBERNATE gerencia esses tipos de dados no banco de dados?
    *
    * O hibernate trabalha com o LIST de tal forma que ele tras apenas o primeiro elemento do LIST,
    * ele não consegue trazer em apenas uma única consulta
    *
    * Agora quando se trabalha com o SET ele trás todas as coleções / todas as entidades relacionadas
    *
    * */

}
