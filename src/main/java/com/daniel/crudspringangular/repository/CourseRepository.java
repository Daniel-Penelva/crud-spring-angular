package com.daniel.crudspringangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.crudspringangular.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
}
