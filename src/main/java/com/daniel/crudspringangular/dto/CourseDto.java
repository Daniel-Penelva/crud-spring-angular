package com.daniel.crudspringangular.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CourseDto {

    private Long _id;

    @NotBlank
    @NotNull
    @Length(min = 3, max = 100)
    private String name;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Back-end|Front-end")
    private String category;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    private String status = "Ativo";
}
