package com.backend_project.repositories;

import com.backend_project.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repositorio del que sacaremos lo métodos de la clase JpaRepository
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    //Método para devolver los empleados que siguen activos
    List<Employee>findByEndDateNull();
}
