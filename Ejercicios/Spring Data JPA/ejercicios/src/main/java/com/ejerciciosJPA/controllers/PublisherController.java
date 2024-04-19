package com.ejerciciosJPA.controllers;

import com.ejerciciosJPA.entities.Author;
import com.ejerciciosJPA.entities.Publisher;
import com.ejerciciosJPA.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Controlador de Editoriales, cuando se ponga una url con editorial, nuestra API devolverá algo según la petición
@RestController
@RequestMapping("/publisher")
public class PublisherController {
    //Inyección de dependencias para separar la lógica (servicio) del controlador
    @Autowired
    private PublisherService publisherService;
    //Si se usa petición Get
    @GetMapping
    //Devuelve lista de todas las editoriales
    public List<Publisher> getAllPublishers (){
        return publisherService.getAllPublishers();
    }
    //Si se usa el método Get
    @PostMapping
    //inserta una nueva editorial en la tabla
    public Publisher createPublisher(@RequestBody Publisher publisher){
        return publisherService.insertPublisher(publisher);
    }
}
