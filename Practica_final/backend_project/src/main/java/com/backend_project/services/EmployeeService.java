package com.backend_project.services;

import com.backend_project.model.Asignation;
import com.backend_project.model.Employee;
import com.backend_project.model.EmployeeAssigned;
import com.backend_project.model.Project;
import com.backend_project.repositories.AsignationRepository;
import com.backend_project.repositories.EmployeeRepository;
import com.backend_project.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Servicio de empleado, va a tener una clase de su propio repositorio autoimplementada
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AsignationRepository asignationRepository;

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
        if (asignationRepository.existsByEmployeeEmployeeId(id)){
            Optional<Employee> employee = employeeRepository.findById(id);
            String message = "No se puede dar de baja al empleado "+employee.get().getName()+" "+
                    employee.get().getLastName()+ " "+ employee.get().getSecondLastName()+
                    " porque está asignado a el/los proyectos ";
            List <Asignation> asignations = asignationRepository.findByEmployeeEmployeeId(id);
            for (int i = 0; i < asignations.size(); i++){
                if (i == asignations.size() - 1){
                    message+=asignations.get(i).getProject().getDescription();
                }
                else {
                    message+=asignations.get(i).getProject().getDescription() + ", ";
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
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
    public List <EmployeeAssigned> getAssignedEmployees(Integer projectId){
        List<Asignation> asignations = asignationRepository.findAll();

        List <Asignation> projectAsignations = new ArrayList<>();

        for (Asignation asignation : asignations){
            if (asignation.getId().getProjectId().equals(projectId)){
                projectAsignations.add(asignation);
            }
        }

        List<EmployeeAssigned> assignedEmployees = new ArrayList<>();

        List<Employee> allEmployees = getAllActiveEmployees();

        for (Employee employee : allEmployees){
            boolean assigned = projectAsignations.stream()
                    .anyMatch(asignation -> asignation.getEmployee().getEmployeeId().equals(employee.getEmployeeId()));

            EmployeeAssigned assignedEmployee = new EmployeeAssigned(employee.getName() + " " + employee.getLastName() + " " + employee.getSecondLastName(), employee.getEmployeeId(),assigned);

            assignedEmployees.add(assignedEmployee);
        }
        return assignedEmployees;
    }

}
