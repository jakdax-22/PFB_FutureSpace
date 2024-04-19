package com.ejerciciosJPA.services;

import com.ejerciciosJPA.entities.Book;
import com.ejerciciosJPA.entities.Publisher;
import com.ejerciciosJPA.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private  BookRepository bookRepository;
    /*@Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private TopicRepository topicRepository;*/

    public BookService(){

    }
    public Book insertBook(Book book){
        /*Author author = authorRepository.findById(book.getAuthor().getId()).orElseThrow(()->
                new IllegalArgumentException("El autor no existe"));
        Publisher publisher = publisherRepository.findById(book.getPublisher().getId()).orElseThrow(()->
                new IllegalArgumentException("La editorial no existe"));
        Topic topic = topicRepository.findById(book.getTopic().getId()).orElseThrow(()->
                new IllegalArgumentException("La temática no existe"));
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setTopic(topic);*/
        return bookRepository.save(book);
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public List<Book> findAfter(int year){
        return bookRepository.findBooksAfter(year);
    }
    public List<Book> findAfterMethod(int year){
        return bookRepository.findByPublicationDateGreaterThan(year);
    }
    public List<Book> findByDate (int year){
        return bookRepository.findByPublicationDate(year);
    }
    public List<Book> findByIsbn (int isbn){
        return bookRepository.findByIsbn(isbn);
    }
    public List<Book> findByPublisher (String publisher){
        return bookRepository.findByPublisher(publisher);
    }
    public List<Book> findByPublisherAndDate (String publisher,int year){
        return bookRepository.findByDateAndPublisher(publisher,year);
    }
}
