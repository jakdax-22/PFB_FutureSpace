package com.ejerciciosJPA.services;

import com.ejerciciosJPA.entities.Topic;
import com.ejerciciosJPA.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    private  TopicRepository topicRepository;

    public TopicService(){
    }
    public Topic insertTopic(Topic topic){
        return topicRepository.save(topic);
    }
    public List<Topic> getAllTopics(){
        return topicRepository.findAll();
    }
}
