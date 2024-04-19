package com.ejerciciosJPA.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Editoriales")
public class Publisher {
    @Id
    @SequenceGenerator(
            name = "publisher_sequence",
            sequenceName = "publisher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "publisher_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            nullable = false,
            name="name"
    )
    private String name;
    @Column(
            name="razon_social"
    )
    private String socialReason;

    public Publisher(String name, String socialReason) {
        this.name = name;
        this.socialReason = socialReason;
    }
    public Publisher(){

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

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", socialReason='" + socialReason + '\'' +
                '}';
    }
}
