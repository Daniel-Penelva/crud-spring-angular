package com.daniel.crudspringangular;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.daniel.crudspringangular.enums.Category;
import com.daniel.crudspringangular.model.Course;
import com.daniel.crudspringangular.model.Lesson;
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

			for(int i=0; i<20; i++){

				Course c = new Course();
				c.setName("Angular com Spring " + i);
				c.setCategory(Category.FRONT_END);

				Lesson l = new Lesson();
				l.setName("Introducao");
				l.setYoutubeUrl("https://www.youtube.com/");
				l.setCourse(c);
				c.getLessons().add(l);

				Lesson l1 = new Lesson();
				l1.setName("google");
				l1.setYoutubeUrl("https://www.google.com/");
				l1.setCourse(c);
				c.getLessons().add(l1);

				courseRepository.save(c);
			}
		};
	}
}
