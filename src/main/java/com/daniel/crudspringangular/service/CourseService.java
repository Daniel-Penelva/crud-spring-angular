package com.daniel.crudspringangular.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.daniel.crudspringangular.dto.CourseDto;
import com.daniel.crudspringangular.dto.mapper.CourseMapper;
import com.daniel.crudspringangular.enums.Category;
import com.daniel.crudspringangular.exception.RecordNotFoundException;
import com.daniel.crudspringangular.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    // Listar curso
    public List<CourseDto> list() {

        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar curso por id
    public CourseDto findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    // Criar curso
    public CourseDto create(@Valid @NotNull CourseDto course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    // Atualizar curso por id
    public CourseDto update(@NotNull @Positive Long id, @Valid @NotNull CourseDto course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.name());
                    recordFound.setCategory(Category.FRONTEND);
                    return courseRepository.save(recordFound);
                })
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    // Deletar por id
    public void delete(@PathVariable @NotNull @Positive Long id) {

        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));

        /*
         * courseRepository.findById(id)
         * .map(recordFound -> {
         * courseRepository.deleteById(id);
         * return true;
         * }).orElseThrow(() -> new RecordNotFoundException(id));
         */
    }

}
