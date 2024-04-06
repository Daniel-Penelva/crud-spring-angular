package com.daniel.crudspringangular.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.daniel.crudspringangular.enums.Category;
import com.daniel.crudspringangular.enums.Status;
import com.daniel.crudspringangular.enums.converters.CategoryConverter;
import com.daniel.crudspringangular.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data; // Anotação do Lombok eliminando a necessidade de escrever métodos getters, setters

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    // A anotação: Column(<qtd de caracteres>, <define que não pode ser not null>)
    @NotBlank
    @NotNull
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    // Relacionamento bidirecional - Um curso tem várias aulas
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    //@JoinColumn(name = "course_id") - OBS. Em termo de perfomance não é aconselhavel usar mapeamento relacional dessa maneira. A maneira correta é utilizar a propriedade "mappedBy" e tornar o relacionamento bidirecional e nesse outro relacionamento, Lesson, utilizar a propriedade "@JoinColumn".
    private List<Lesson> lessons = new ArrayList<>();

}