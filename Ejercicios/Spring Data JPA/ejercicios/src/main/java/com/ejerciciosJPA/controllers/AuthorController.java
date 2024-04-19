package com.ejerciciosJPA.controllers;

import com.ejerciciosJPA.entities.Author;
import com.ejerciciosJPA.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Controlador de Autor, cuando se ponga una url con autor, nuestra API devolverá algo según la petición
@RestController
@RequestMapping("/author")
public class AuthorController {
    //Inyección de dependencias para separar la lógica (servicio) del controlador
    @Autowired
    private  AuthorService authorService;
    //Si se usa petición Get
    @GetMapping
    //Devuelve lista de todos los autores
    public List<Author> getAllAuthors (){
        return authorService.getAllAuthors();
    }
    //Si se usa el método Get
    @PostMapping
    //inserta un nuevo autor en la tabla
    public Author createAuthor(@RequestBody Author author){
        return authorService.insertAuthor(author);
    }
}
