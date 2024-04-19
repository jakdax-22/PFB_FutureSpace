package com.ejerciciosJPA.services;

import com.ejerciciosJPA.entities.Publisher;
import com.ejerciciosJPA.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private  PublisherRepository publisherRepository;

    public PublisherService(){
    }
    public Publisher insertPublisher(Publisher publisher){
        return publisherRepository.save(publisher);
    }
    public List<Publisher> getAllPublishers(){
        return publisherRepository.findAll();
    }
}
