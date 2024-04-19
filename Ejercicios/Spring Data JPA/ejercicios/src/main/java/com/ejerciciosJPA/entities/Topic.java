package com.ejerciciosJPA.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Tematicas")
public class Topic {
    @Id
    @SequenceGenerator(
            name = "topic_sequence",
            sequenceName = "topic_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "topic_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            nullable = false
    )
    private String name;

    public Topic(String name) {
        this.name = name;
    }

    public Topic(){

    }

    public Long getId() {
        return id;
    }
    //No voy a poner setter de id porque no le veo sentido al ser autogenerado y no poder ser actualizado en la tabla

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
