package com.backend_project.repositories;

import com.backend_project.model.Asignation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignationRepository extends JpaRepository <Asignation,Integer>{
    boolean existsByEmployeeEmployeeId(Integer employeeId);
    boolean existsByProjectProjectId(Integer projectId);

    List<Asignation> findByEmployeeEmployeeId(Integer employeeId);
}
