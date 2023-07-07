package com.ead.course.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name="TB_MODULES")
public class ModuleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID moduleId;
    @Column(nullable = false, length = 150)
    private String title;
    @Column(nullable = false, length = 250)
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime creationDate;

    /*Relacionamento entre curso e modulo
     * Vários módulos vai pertencer a um determinado curso
     * Ou seja, cada módulo vai pertencer a um determinado curso
     * Representando por meio do JPA
     * Quando criamos esse mapeamento estamos criando uma espécie de chave estrangeira
     * dessa forma ele vai criar uma chave estrangeira para curso ID
     * Sempre ele precisa assimilar um módulo entre um curso, por isso precisamos definir
     * a anotação optional como false.
     * */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CourseModel course;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    private Set<LessonModel> lessons;


}
