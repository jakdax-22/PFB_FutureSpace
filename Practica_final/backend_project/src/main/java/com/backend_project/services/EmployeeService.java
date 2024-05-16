package com.backend_project.services;

import com.backend_project.model.Employee;
import com.backend_project.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
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
    //Método para insertar un empleado
    public ResponseEntity<String> insertEmployee(Employee newEmployee){
        try{
            employeeRepository.save(newEmployee);

            return ResponseEntity.status(HttpStatus.OK).body("Empleado insertado correctamente");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
    public ResponseEntity<String> terminateEmployee(Integer id){
        try{
            //Buscar el empleado
            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException(("Empleado no encontrado")));
            //Dar fecha actual de fecha de baja
            employee.setEndDate(Date.valueOf(LocalDate.now()));
            employeeRepository.save(employee);
            //Devolver respuesta
            return ResponseEntity.status(HttpStatus.OK).body("Empleado dado de baja correctamente");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al dar de baja al empleado");
        }
    }

}
