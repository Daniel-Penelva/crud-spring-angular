package com.daniel.crudspringangular.dto;

import java.util.List;

public record CoursePageDTO(List<CourseDto> courses, long totalElements, int totalPages) {

}