package com.backend_project.controllers;

import com.backend_project.model.Employee;
import com.backend_project.model.Project;
import com.backend_project.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Decimos que es una API REST y que se va a encontrar esta clase en /project
@RestController
@RequestMapping("/project")
public class ProjectController {
    //Inyección de dependencias para separar la lógica en servicios
    @Autowired
    private ProjectService projectService;

    @GetMapping(path = "/active")
    //Devuelve la lista de los proyectos activos
    public List<Project> geAllProjects(){
        return projectService.getAllActiveProjects();
    }
    @PostMapping
    //Inserta un nuevo Proyecto a la BD
    public ResponseEntity<String> insertProject(@RequestBody Project project){
        //System.out.println("employee: "+employee);
        return projectService.insertProject(project);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> terminateProject(@PathVariable Integer id){
        return projectService.terminateProject(id);
    }
}

