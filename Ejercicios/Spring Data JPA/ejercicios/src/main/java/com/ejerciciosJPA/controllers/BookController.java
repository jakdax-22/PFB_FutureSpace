package com.ejerciciosJPA.controllers;

import com.ejerciciosJPA.entities.Book;
import com.ejerciciosJPA.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    //Todos los gets para hacer consultas a la BD

    //Seleccionar todos
    @GetMapping
    public List<Book> getAllBooks (){
        return bookService.getAllBooks();
    }
    //Seleccionar después de un año específico
    @GetMapping(path="/afteryear/{year}")
    public List<Book> findAfter(@PathVariable int year){
        return bookService.findAfter(year);
    }
    //Lo mismo pero usando método
    @GetMapping(path="/afteryearMethod/{year}")
    public List<Book> findAfterMethod(@PathVariable int year){
        return bookService.findAfterMethod(year);
    }
    //Seleccionar de un año específico
    @GetMapping(path="/byYear/{year}")
    public List<Book> findByYear(@PathVariable int year){
        return bookService.findByDate(year);
    }
    //Seleccionar de un ISBN específico
    @GetMapping(path="/byISBN/{isbn}")
    public List<Book> findByIsbn(@PathVariable int isbn){
        return bookService.findByIsbn(isbn);
    }
    //Seleccionar de una editorial en específico, como mis FK (Author,publisher y topic)son objetos he tenido que poner que se pida un objeto publisher en el body
    @GetMapping(path="/byPublisher/{publisherName}")
    public List<Book> findByPublisher(@PathVariable String publisherName){
        return bookService.findByPublisher(publisherName);
    }
    //Seleccionar de una editorial y fecha específicas
    @GetMapping(path="/byPublisherAndDate/{date}-{publisherName}")
    public List<Book> findByPublisherAndDate(@PathVariable int date,@PathVariable String publisherName){
        return bookService.findByPublisherAndDate(publisherName,date);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookService.insertBook(book);
    }
}
