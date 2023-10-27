package com.daniel.crudspringangular.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;   // Anotação do Lombok eliminando a necessidade de escrever métodos getters, setters

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
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
    @Length(max = 10)
    @Pattern(regexp = "Back-end|Front-end")
    @Column(length = 50, nullable = false)
    private String category;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    @Column(length = 10, nullable = false)
    private String status = "Ativo";

}

/*
 * OBS.
 * Anotação @Pattern(regexp = "Back-end|Front-end") 
 * Essa anotação é usada para garantir que um determinado campo contenha um valor que corresponda a um padrão regular expresso. 
 * 
 *   -> Se o campo tiver o valor "Back-end", a validação será bem-sucedida.
 *   -> Se o campo tiver o valor "Front-end", a validação também será bem-sucedida.
 *   -> Se o campo tiver qualquer outro valor, a validação falhará.
*/