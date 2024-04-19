package com.ejerciciosJPA.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Libros")
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(
            name = "ISBN",
            updatable = false
    )
    private Long isbn;
    @Column(
            name="título",
            nullable = false
    )
    private String title;
    @Column(
            name="ano_publicacion",
            nullable = false
    )
    private Integer publicationDate;
    @ManyToOne
    @JoinColumn(name="author")
    private Author author;

    @ManyToOne
    @JoinColumn(name="publisher")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name="topic")
    private Topic topic;

    public Book(String title, Integer publicationDate, Author author, Publisher publisher, Topic topic) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.publisher = publisher;
        this.topic = topic;
    }
    public Book(){

    }
    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Integer publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN=" + isbn +
                ", title='" + title + '\'' +
                ", publicationDate=" + publicationDate +
                ", author=" + author +
                ", publisher=" + publisher +
                ", topic=" + topic +
                '}';
    }
}
