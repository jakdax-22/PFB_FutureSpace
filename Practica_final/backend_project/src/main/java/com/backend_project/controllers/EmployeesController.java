package com.backend_project.controllers;

import com.backend_project.model.Employee;
import com.backend_project.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Decimos que es una API REST y que se va a encontrar esta clase en /employee
@RestController
@RequestMapping("/employee")
public class EmployeesController {
    //Inyección de dependencias para separar la lógica en servicios
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/active")
    //Devuelve la lista de los empleados activos
    public List<Employee> getAllEmployees(){
        return employeeService.getAllActiveEmployees();
    }
    @PostMapping
    //Inserta un nuevo empleado a la BD
    public ResponseEntity<String> insertEmployee(@RequestBody Employee employee){
        //System.out.println("employee: "+employee);
        return employeeService.insertEmployee(employee);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> terminateEmployee(@PathVariable Integer id){
        return employeeService.terminateEmployee(id);
    }
}

