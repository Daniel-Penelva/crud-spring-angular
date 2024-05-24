package com.daniel.crudspringangular.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.daniel.crudspringangular.enums.Category;
import com.daniel.crudspringangular.enums.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CourseDto(
    @JsonProperty("_id") Long id, 
    @NotBlank @NotNull @Length(min = 3, max = 100) String name, 
    @NotNull @Length(max = 10) @ValueOfEnum(enumClass = Category.class) String category, 
    @NotNull @NotEmpty @Valid List<LessonDto> lessons) {
    
}
