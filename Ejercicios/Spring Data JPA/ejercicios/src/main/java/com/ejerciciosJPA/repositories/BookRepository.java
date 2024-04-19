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
    @Query("SELECT b FROM Book b WHERE b.publicationDate > 2001")
    List<Book> findBooksAfter(int year);

    //Con método
    List<Book>findByPublicationDateGreaterThan(int year);
    List<Book>findByPublicationDate(int year);
    List<Book>findByIsbn(int isbn);
    @Query ("SELECT b FROM Book b JOIN b.publisher p WHERE p.name = ?1")
    List<Book>findByPublisher(String name);

    @Query("SELECT b FROM Book b JOIN b.publisher p WHERE p.name = ?1 AND b.publicationDate = ?2")
    List<Book>findByDateAndPublisher(String name, int year);

}
