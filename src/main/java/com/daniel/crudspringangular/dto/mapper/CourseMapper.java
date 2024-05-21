package com.daniel.crudspringangular.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.daniel.crudspringangular.dto.CourseDto;
import com.daniel.crudspringangular.dto.LessonDto;
import com.daniel.crudspringangular.enums.Category;
import com.daniel.crudspringangular.model.Course;
import com.daniel.crudspringangular.model.Lesson;

@Component
public class CourseMapper {

    public CourseDto toDTO(Course course) {
        if (course == null) {
            return null;
        }
        List<LessonDto> lessons = course.getLessons()
                .stream()
                .map(lesson -> new LessonDto(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
                .collect(Collectors.toList());

        return new CourseDto(course.getId(), course.getName(), course.getCategory().getValue(), lessons);
    }

    public Course toEntity(CourseDto courseDto) {

        if (courseDto == null) {
            return null;
        }

        Course course = new Course();

        if (courseDto.id() != null) {
            course.setId(courseDto.id());
        }
        course.setName(courseDto.name());
        course.setCategory(convertCategoryValue(courseDto.category()));

        List<Lesson> lessons = courseDto.lessons().stream().map(lessonDTO -> {
            var lesson = new Lesson();
            lesson.setId(lessonDTO.id());
            lesson.setName(lessonDTO.name());
            lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
            lesson.setCourse(course);
            return lesson;
        }).collect(Collectors.toList());
        
        course.setLessons(lessons);

        return course;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria inválida: " + value);
        };
    }
}
