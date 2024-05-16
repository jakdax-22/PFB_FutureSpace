package com.backend_project.repositories;

import com.backend_project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

//Repositorio del que sacaremos lo métodos de la clase JpaRepository
@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

    //Método para devolver los proyectos que siguen activos
    List<Project>findByTerminateDateNull();

}
