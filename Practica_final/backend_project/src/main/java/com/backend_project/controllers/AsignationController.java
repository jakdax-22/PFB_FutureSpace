package com.backend_project.controllers;

import com.backend_project.model.Asignation;
import com.backend_project.services.AsignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
