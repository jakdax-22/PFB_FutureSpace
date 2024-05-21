package com.backend_project.services;

import com.backend_project.model.*;
import com.backend_project.repositories.AsignationRepository;
import com.backend_project.repositories.EmployeeRepository;
import com.backend_project.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AsignationService {
    @Autowired
    public AsignationRepository asignationRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public ProjectRepository projectRepository;

    public AsignationService(){

    }

    public List<Asignation> getAllAsignations(){
        return asignationRepository.findAll();
    }

    public ResponseEntity<String>insertAsignation(List<ModifiedAsignation>insertAsignations){
        boolean inserted = true;
        for ( ModifiedAsignation insertAsignation : insertAsignations){
            AsignationId ids = new AsignationId(insertAsignation.getEmployeeId(),insertAsignation.getProjectId());
            Optional <Employee> employee = employeeRepository.findById(insertAsignation.getEmployeeId());
            Optional <Project> project = projectRepository.findById(insertAsignation.getProjectId());
            if (employee.isPresent() && project.isPresent()){
                Asignation newAsignation = new Asignation(
                employee.get(),
                project.get(),
                Date.valueOf(LocalDate.now()));
                asignationRepository.save(newAsignation);
            }
            else{
                inserted = false;
            }
        }
        if (inserted)
            return ResponseEntity.status(HttpStatus.OK).body("Operaciones de asignación realizadas correctamente");
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Proyectos o empleados no encontrados");


    }public ResponseEntity<String>deleteAsignations(List<ModifiedAsignation>deleteAsignations){
        boolean deleted = true;
        for ( ModifiedAsignation deleteAsignation : deleteAsignations){
            AsignationId ids = new AsignationId(deleteAsignation.getEmployeeId(),deleteAsignation.getProjectId());
            Optional <Employee> employee = employeeRepository.findById(deleteAsignation.getEmployeeId());
            Optional <Project> project = projectRepository.findById(deleteAsignation.getProjectId());
            if (employee.isPresent() && project.isPresent()){
                Asignation newAsignation = new Asignation(
                employee.get(),
                project.get(),
                Date.valueOf(LocalDate.now()));
                asignationRepository.delete(newAsignation);
            }
            else{
                deleted = false;
            }
        }
        if (deleted)
            return ResponseEntity.status(HttpStatus.OK).body("Operaciones de asignación realizadas correctamente");
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Proyectos o empleados no encontrados");


    }
}
