package com.daniel.crudspringangular.model;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String youtubeUrl;

    // Relacionamento bidirecional - Muitas aulas para um curso
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // indica que a propriedade anotada só deve ser serializada para JSON e não deve ser desserializada a partir de JSON. Isso significa que a propriedade será ignorada durante a desserialização, mas será incluída quando o objeto for serializado em JSON
    private Course course;
    
}


/*OBS. 

Serialização refere-se à conversão de um objeto Java em um formato de dados, como JSON ou XML, que pode ser facilmente transmitido ou armazenado. 
Isso é equivalente à "escrita" de dados em um formato legível por máquina.

Desserialização, por outro lado, refere-se à conversão de um formato de dados, como JSON ou XML, de volta a um objeto Java. Isso é equivalente à 
"leitura" de dados de um formato legível por máquina e a criação de um objeto Java correspondente.

Portanto, a anotação "@JsonProperty(access = JsonProperty.Access.WRITE\_ONLY)" indica que a propriedade anotada deve ser incluída apenas durante 
a serialização (escrita) e deve ser ignorada durante a desserialização (leitura).
*/