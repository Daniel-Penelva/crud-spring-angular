package com.daniel.crudspringangular.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.daniel.crudspringangular.model.Lesson;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDto(
    @JsonProperty("_id") Long id, 
    @NotBlank @NotNull @Length(min = 3, max = 100) String name, 
    @NotNull @Length(max = 10) @Pattern(regexp = "Back-end|Front-end") String category, 
    List<Lesson> lessons) {
    
}
