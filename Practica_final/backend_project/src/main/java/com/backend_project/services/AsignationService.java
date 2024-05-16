package com.backend_project.services;

import com.backend_project.model.Asignation;
import com.backend_project.repositories.AsignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignationService {
    @Autowired
    public AsignationRepository asignationRepository;

    public AsignationService(){

    }

    public List<Asignation> getAllAsignations(){
        return asignationRepository.findAll();
    }
}
