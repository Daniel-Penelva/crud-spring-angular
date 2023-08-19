package com.daniel.crudspringangular.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    
    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public @ResponseBody List<Course> list(){
       return courseRepository.findAll();
    }

    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody Course course){
        //System.out.println(course.getName());
       //return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course)); <Este exemplo declara o tipo ResponseEntity<Course>>

       return courseRepository.save(course);
    }
}
