package com.daniel.crudspringangular;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.daniel.crudspringangular.enums.Category;
import com.daniel.crudspringangular.model.Course;
import com.daniel.crudspringangular.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringAngularApplication.class, args);
	}

	// Para testar a lista de cursos
	@Bean
	CommandLineRunner initDataBase(CourseRepository courseRepository){
		return args ->{
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(Category.FRONT_END);

			courseRepository.save(c);
		};
	}
}
