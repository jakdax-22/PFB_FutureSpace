package com.backend_project.services;

import com.backend_project.model.Employee;
import com.backend_project.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Servicio de empleado, va a tener una clase de su propio repositorio autoimplementada
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(){

    }
    //Método cogido del repositorio para mostrar todos los empleados en activo
    public List<Employee> getAllActiveEmployees(){
        return employeeRepository.findByEndDateNull();
    }

}
