package com.ejerciciosJPA.controllers;

import com.ejerciciosJPA.entities.Topic;
import com.ejerciciosJPA.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Controlador de Temáticas, cuando se ponga una url con temática, nuestra API devolverá algo según la petición
@RestController
@RequestMapping("/topic")
public class TopicController {
    //Inyección de dependencias para separar la lógica (servicio) del controlador
    @Autowired
    private TopicService topicService;
    //Si se usa petición Get
    @GetMapping
    //Devuelve lista de todas las temáticas
    public List<Topic> getAllTopics (){
        return topicService.getAllTopics();
    }
    //Si se usa el método Get
    @PostMapping
    //inserta una nueva editorial en la tabla
    public Topic createPublisher(@RequestBody Topic topic){
        return topicService.insertTopic(topic);
    }
}
