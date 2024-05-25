package com.daniel.crudspringangular.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.crudspringangular.dto.CourseDto;
import com.daniel.crudspringangular.dto.CoursePageDTO;
import com.daniel.crudspringangular.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Listar curso - http://localhost:8080/api/courses
    // @RequestMapping(method = RequestMethod.GET)
    //@GetMapping
    //public List<CourseDto> list() {
    //    return courseService.list();
    //}


    // Listar curso - http://localhost:8080/api/courses
    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public CoursePageDTO list(@RequestParam int page, @RequestParam int pageSize) {
        return courseService.listWithPagination(page, pageSize);
    }

    // Criar curso - http://localhost:8080/api/courses
    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDto create(@RequestBody @Valid @NotNull CourseDto course) {

        return courseService.create(course);
    }

    // Buscar curso por id - http://localhost:8080/api/courses/{id}
    @GetMapping("/{id}")
    public CourseDto findById(@PathVariable @NotNull @Positive Long id) {
        return courseService.findById(id);
    }

    // Atualizar curso por id - http://localhost:8080/api/courses/{id}
    @PutMapping("/{id}")
    public CourseDto update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull CourseDto course) {
        return courseService.update(id, course);
    }

    // Deletar curso por id - http://localhost:8080/api/courses/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        courseService.delete(id);
    }
}
