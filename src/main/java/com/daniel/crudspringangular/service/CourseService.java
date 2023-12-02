package com.daniel.crudspringangular.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

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
    public Optional<Course> findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id);
    }

    // Criar curso
    public Course create(@Valid Course course) {
        return courseRepository.save(course);
    }

    // Atualizar curso por id
    public Optional<Course> update(@NotNull @Positive Long id, @Valid Course course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return courseRepository.save(recordFound);
                });
    }

    // Deletar por id
    public boolean delete(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

}
