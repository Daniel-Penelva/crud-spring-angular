package com.daniel.crudspringangular.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.daniel.crudspringangular.exception.RecordNotFoundException;
import com.daniel.crudspringangular.model.Course;
import com.daniel.crudspringangular.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Listar curso
    public List<Course> list() {
        return courseRepository.findAll();
    }

    // Buscar curso por id
    public Course findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    // Criar curso
    public Course create(@Valid Course course) {
        return courseRepository.save(course);
    }

    // Atualizar curso por id
    public Course update(@NotNull @Positive Long id, @Valid Course course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return courseRepository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    // Deletar por id
    public void delete(@PathVariable @NotNull @Positive Long id) {

        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));

        /* 
        courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(id);
                    return true;
                }).orElseThrow(() -> new RecordNotFoundException(id)); */
    }

}
