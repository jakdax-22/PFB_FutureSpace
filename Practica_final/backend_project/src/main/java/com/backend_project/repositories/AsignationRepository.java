package com.backend_project.repositories;

import com.backend_project.model.Asignation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignationRepository extends JpaRepository <Asignation,Integer>{
}
