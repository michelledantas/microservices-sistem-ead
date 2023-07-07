package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID> {


    /*Dessa forma, como foi anotato nessa consulta EntityGraph como eu passei qual que é o atributo LAZY
    que eu quero que seja carregado de forma EAGER, ele vai usar dessa propriedade*/
    @EntityGraph(attributePaths = {"course"})
    ModuleModel findByTitle(String title);
}
