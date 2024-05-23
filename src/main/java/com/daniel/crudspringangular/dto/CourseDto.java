package com.daniel.crudspringangular.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDto(
    @JsonProperty("_id") Long id, 
    @NotBlank @NotNull @Length(min = 3, max = 100) String name, 
    @NotNull @Length(max = 10) @Pattern(regexp = "Back-end|Front-end") String category, 
    @NotNull @NotEmpty @Valid List<LessonDto> lessons) {
    
}
