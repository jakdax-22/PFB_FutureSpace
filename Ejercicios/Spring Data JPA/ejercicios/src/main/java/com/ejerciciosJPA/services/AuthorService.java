package com.ejerciciosJPA.services;

import com.ejerciciosJPA.entities.Author;
import com.ejerciciosJPA.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Creación de un servicio para cada repositorio
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorService(){

    }
    public Author insertAuthor(Author author){
        return authorRepository.save(author);
    }
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
}
