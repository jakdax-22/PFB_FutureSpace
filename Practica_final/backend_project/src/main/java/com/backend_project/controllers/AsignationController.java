package com.backend_project.controllers;

import com.backend_project.model.Asignation;
import com.backend_project.model.ModifiedAsignation;
import com.backend_project.services.AsignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignations")
public class AsignationController {
    @Autowired
    private AsignationService asignationService;

    @GetMapping
    public ResponseEntity<List<Asignation>> getAllAsignations(){
        List<Asignation> asignations = asignationService.getAllAsignations();
        return new ResponseEntity<>(asignations, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> insertAsignations(@RequestBody List<ModifiedAsignation> asignations){
        return asignationService.insertAsignation(asignations);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteAsignations(@RequestBody List <ModifiedAsignation> asignations){
        System.out.println(asignations);
        return asignationService.deleteAsignations(asignations);
    }

}
