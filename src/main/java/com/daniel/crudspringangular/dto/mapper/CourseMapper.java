package com.daniel.crudspringangular.dto.mapper;

import org.springframework.stereotype.Component;

import com.daniel.crudspringangular.dto.CourseDto;
import com.daniel.crudspringangular.enums.Category;
import com.daniel.crudspringangular.model.Course;

@Component
public class CourseMapper {

    public CourseDto toDTO(Course course) {
        if(course == null){
            return null;
        }
        return new CourseDto(course.getId(), course.getName(), "Front-end");
    }

    public Course toEntity(CourseDto courseDto) {

        if(courseDto == null){
            return null;
        }

        Course course = new Course();

        if (courseDto.id() != null) {
            course.setId(courseDto.id());
        }
        course.setName(courseDto.name());
        course.setCategory(Category.FRONT_END);
        course.setStatus("Ativo");
        return course;
    }
}
