package com.daniel.crudspringangular.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.daniel.crudspringangular.enums.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;   // Anotação do Lombok eliminando a necessidade de escrever métodos getters, setters

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
    //@Length(max = 10)
    //@Pattern(regexp = "Back-end|Front-end")
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)  // Se colocarmos a anotação @enumerated (enumtype.ordinal) no campo Enum, o JPA usará o valor enum.ordinal (por exemplo, 0 para BACKEND e 1 para FRONTEND, ou seja, vai salvar o indice do enumerador, como o BACKEND é o primeiro logo ele é zero 0) ao persistir em uma determinada entidade no banco de dados.
    private Category category;

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