package com.daniel.crudspringangular.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.daniel.crudspringangular.dto.CourseDto;
import com.daniel.crudspringangular.dto.CoursePageDTO;
import com.daniel.crudspringangular.dto.mapper.CourseMapper;
import com.daniel.crudspringangular.exception.RecordNotFoundException;
import com.daniel.crudspringangular.model.Course;
import com.daniel.crudspringangular.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

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

    // Listar curso com paginação
    public CoursePageDTO listWithPagination(@PositiveOrZero int page, @Positive @Max(100) int size) {

        Page<Course> pageCourse = courseRepository.findAll(PageRequest.of(page, size));
        List<CourseDto> courses = pageCourse.get().map(courseMapper::toDTO).collect(Collectors.toList());
        return new CoursePageDTO(courses, pageCourse.getTotalElements(), pageCourse.getTotalPages());         
    }

    // Buscar curso por id
    public CourseDto findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    // Criar curso
    public CourseDto create(@Valid @NotNull CourseDto course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    // Atualizar curso por id
    public CourseDto update(@NotNull @Positive Long id, @Valid @NotNull CourseDto courseDTO) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    Course course = courseMapper.toEntity(courseDTO);
                    recordFound.setName(courseDTO.name());
                    recordFound.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));
                    recordFound.getLessons().clear();                                                 // limpa a lista de lição que veio da base de dados (OBS. corresponde a mesma referência de objeto lesson da classe Course). Isso é necessário porque as lições atualizadas serão adicionadas posteriormente.
                    course.getLessons().forEach(recordFound.getLessons()::add);                       // vai add cada lição que vier da tela (OBS. ou .getLessons().add(lesson)). Para cada lição no novo objeto Course, a lição é adicionada à lista de lições do curso existente.
                    return courseMapper.toDTO(courseRepository.save(recordFound));                    // O curso salvo é convertido de volta para um CourseDto usando o courseMapper.
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    // Deletar por id
    public void delete(@NotNull @Positive Long id) {

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
