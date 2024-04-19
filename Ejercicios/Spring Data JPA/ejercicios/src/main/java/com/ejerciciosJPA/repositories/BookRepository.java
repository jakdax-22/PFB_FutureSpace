package com.ejerciciosJPA.repositories;

import com.ejerciciosJPA.entities.Book;
import com.ejerciciosJPA.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    //Con consulta @Query
    //Yo es que solo había tenido en cuenta el año de publicación, lo tengo guardado como un Int, no como Date, por tanto lo hago de mayores de 2001
    @Query("SELECT b FROM Book b WHERE b.publicationDate > 2001")
    List<Book> findBooksAfter(int year);

    //Con método
    List<Book>findByPublicationDateGreaterThan(int year);
    //Encontrar por fecha de publicación
    List<Book>findByPublicationDate(int year);
    //Encontrar por Isbn
    List<Book>findByIsbn(int isbn);
    // Encontrar por editorial
    @Query ("SELECT b FROM Book b JOIN b.publisher p WHERE p.name = ?1")
    List<Book>findByPublisher(String name);
    //Encontrar por editorial y fecha de publicación
    @Query("SELECT b FROM Book b JOIN b.publisher p WHERE p.name = ?1 AND b.publicationDate = ?2")
    List<Book>findByDateAndPublisher(String name, int year);

}
