package com.daniel.crudspringangular.controller;


import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.crudspringangular.model.Course;
import com.daniel.crudspringangular.repository.CourseRepository;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    
    private final CourseRepository courseRepository;

    /**
     * O construtor vai fazer o papel da anotação @Autowired para injeção de dependência.
     * 
     * public CourseController(CourseRepository courseRepository) {
     *  this.courseRepository = courseRepository;
     * }
     * 
     * Ou pode usar o lombok com a anotação: @AllArgsConstructor
    */
    
    @GetMapping
    public List<Course> list(){
       return courseRepository.findAll();
    }
}
