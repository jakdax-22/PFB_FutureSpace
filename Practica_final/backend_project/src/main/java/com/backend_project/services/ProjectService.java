package com.backend_project.services;

import com.backend_project.model.Employee;
import com.backend_project.model.Project;
import com.backend_project.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

//Servicio de empleado, va a tener una clase de su propio repositorio autoimplementada
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectService() {

    }

    //Método cogido del repositorio para mostrar todos los proyectos en activo
    public List<Project> getAllActiveProjects() {
        return projectRepository.findByTerminateDateNull();
    }
    //Método para insertar un proyecto
    public ResponseEntity<String> insertProject(Project newProject){
        try{
            projectRepository.save(newProject);

            return ResponseEntity.status(HttpStatus.OK).body("Proyecto insertado correctamente");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
    public ResponseEntity<String> terminateProject(Integer id){
        try{
            //Buscar el empleado
            Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException(("Proyecto no encontrado")));
            //Dar fecha actual de fecha de baja
            project.setTerminateDate(Date.valueOf(LocalDate.now()));
            projectRepository.save(project);
            //Devolver respuesta
            return ResponseEntity.status(HttpStatus.OK).body("Proyecto dado de baja correctamente");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al dar de baja el proyecto");
        }
    }

}