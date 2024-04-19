package com.ejerciciosJPA;

import com.ejerciciosJPA.entities.Author;
import com.ejerciciosJPA.entities.Publisher;
import com.ejerciciosJPA.entities.Topic;
import com.ejerciciosJPA.repositories.AuthorRepository;
import com.ejerciciosJPA.repositories.PublisherRepository;
import com.ejerciciosJPA.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class EjerciciosJpaApplication{

	@Autowired
	private TopicRepository topicRepository;

	public static void main(String[] args) {
		SpringApplication.run(EjerciciosJpaApplication.class, args);
	}

	/*Lo estaba haciendo como en el vídeo pero no es así
	@Bean
	CommandLineRunner topicCommandLineRunner(TopicRepository topicRepository){
		return args ->{
			Topic topic = new Topic (
					"History"
			);
			Topic topic2 = new Topic (
					"Communications"
			);

			topicRepository.save(topic);
			topicRepository.save(topic2);
		};
	}
	@Bean
	CommandLineRunner publisherCommandLineRunner(PublisherRepository publisherRepository){
		return args ->{
			Publisher publisher1 = new Publisher (
					"Anaya",
					null
			);
			Publisher publisher2 = new Publisher (
					"O'Reilly",
					null
			);

			publisherRepository.save(publisher1);
			publisherRepository.save(publisher2);
		};
	}
	@Bean
	CommandLineRunner authorCommandLineRunner(AuthorRepository authorRepository){
		return args ->{
			Author author1 = new Author (
					"Pepe",
					"Pérez",
					LocalDate.parse("1988-09-22")

			);
			Author author2 = new Author (
					"Enrique",
					"Iranzo",
					LocalDate.parse("2001-07-22")

			);
			authorRepository.save(author1);
			authorRepository.save(author2);
		};
	}*/
}

