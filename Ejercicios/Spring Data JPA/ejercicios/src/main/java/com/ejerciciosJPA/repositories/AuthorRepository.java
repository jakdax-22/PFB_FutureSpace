package com.ejerciciosJPA.repositories;

import com.ejerciciosJPA.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Creación de un repositorio para cada entidad
@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

}
