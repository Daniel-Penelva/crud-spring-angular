package com.daniel.crudspringangular.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.crudspringangular.model.Course;
import com.daniel.crudspringangular.repository.CourseRepository;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    
    private final CourseRepository courseRepository;

    /**
     * O construtor vai fazer o papel da anotação @Autowired para injeção de dependência.
     * 
     * public CourseController(CourseRepository courseRepository) {
     *  this.courseRepository = courseRepository;
     * }
     * 
     * Ou pode usar o lombok com a anotação: @AllArgsConstructor
    */
    
    // Listar curso - http://localhost:8080/api/courses
    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public @ResponseBody List<Course> list(){
       return courseRepository.findAll();
    }

    // Criar curso - http://localhost:8080/api/courses
    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody Course course){
       
       return courseRepository.save(course);
    }

    /**
     * Buscar curso por id - http://localhost:8080/api/courses/{id}
     * Explicando passo a passo do código:
     * 
     * 1. `@GetMapping("/{id}")`: Esta anotação indica que o método deve ser chamado quando uma solicitação HTTP com método GET é recebida com 
     * um caminho que corresponde a `"/{id}"`. O `{id}` é uma variável de caminho (path variable) que permite identificar qual recurso específico 
     * deve ser recuperado com base no valor do `id` passado na URL.
     * 
     * 2. `public ResponseEntity<Course> findById(@PathVariable Long id)`: Este é o cabeçalho do método. Ele declara que o método é público e 
     * retorna um objeto do tipo `ResponseEntity` que contém um objeto do tipo `Course`. O parâmetro do método é:
     * 
     *      - `@PathVariable Long id`: O `@PathVariable` indica que o valor de `id` é extraído da URL, correspondendo à variável de caminho 
     *         definida na anotação `@GetMapping`. Esse `id` é usado para identificar qual recurso `Course` deve ser recuperado.
     * 
     * 3. `return courseRepository.findById(id) .map(recordFound -> { ... }):` Esta linha começa com uma chamada ao método `findById(id)` do 
     * objeto `courseRepository`, que geralmente é um repositório Spring Data JPA usado para interagir com o banco de dados. Essa chamada tenta 
     * encontrar um registro de `Course` no banco de dados com o ID fornecido.
     * 
     *      - `.map(recordFound -> { ... })`: O método `map` é usado para processar o objeto encontrado (se existir). Se um registro com o ID fornecido for encontrado, o código dentro do bloco `map` será executado.
     * 
     * 4. `ResponseEntity.ok().body(recordFound)`: Se o registro com o ID fornecido for encontrado, esta parte do código cria um `ResponseEntity` 
     * com um código de status HTTP 200 (OK) e o corpo da resposta contém o objeto `recordFound`, que é o registro encontrado no banco de dados.
     * 
     * 5. `.orElse(ResponseEntity.notFound().build());`: Se o método `findById(id)` não encontrar um registro com o ID fornecido, o `.orElse` é 
     * acionado e cria um `ResponseEntity` com um código de status HTTP 404 (Not Found). Isso indica que nenhum registro foi encontrado para o 
     * ID especificado na solicitação.
     * 
     * Resumindo, esse método lida com a recuperação de um recurso `Course` com base no ID fornecido na solicitação GET. Se o registro com o ID 
     * fornecido for encontrado, ele é retornado com um código de status 200 (OK). Se o registro não for encontrado, uma resposta de "Not Found" 
     * é retornada com um código de status 404.
    */
    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id){
        return courseRepository.findById(id)
            .map(recordFound -> ResponseEntity.ok().body(recordFound))
            .orElse(ResponseEntity.notFound().build());
    }


    /** 
     * Atualizar curso por id - http://localhost:8080/api/courses/{id}
     * Explicando passo a passo do código:
     * 
     * 1. `@PutMapping("/{id}")`: Esta anotação indica que o método deve ser chamado quando uma solicitação HTTP com método PUT é recebida com 
     * um caminho que corresponde a `"/{id}"`. O `{id}` é uma variável de caminho (path variable) que permite identificar qual recurso específico 
     * deve ser atualizado com base no valor do `id` passado na URL.
     * 
     * 2. `public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course)`: Este é o cabeçalho do método. Ele declara 
     * que o método é público e retorna um objeto do tipo `ResponseEntity` que contém um objeto do tipo `Course`. Os parâmetros do método são:
     * 
     *     - `@PathVariable Long id`: O `@PathVariable` indica que o valor de `id` é extraído a partir da URL, correspondendo à variável de 
     *        caminho definida na anotação `@PutMapping`. Esse `id` é usado para identificar qual recurso `Course` deve ser atualizado.
     * 
     *     - `@RequestBody Course course`: O `@RequestBody` indica que o objeto `course` é obtido a partir do corpo da solicitação HTTP. Isso 
     *        permite que os dados do curso a serem atualizados sejam passados no corpo da solicitação HTTP.
     * 
     * 3. `return courseRepository.findById(id) .map(recordFound -> { ... }):` Esta linha começa com uma chamada ao método `findById(id)` do 
     * objeto `courseRepository`, que geralmente é um repositório Spring Data JPA usado para interagir com o banco de dados. Essa chamada tenta 
     * encontrar um registro de `Course` no banco de dados com o ID fornecido.
     * 
     *      - `.map(recordFound -> { ... })`: O método `map` é usado para processar o objeto encontrado (se existir). Se um registro com o ID 
     *         fornecido for encontrado, o código dentro do bloco `map` será executado.
     * 
     * 4. `recordFound.setName(course.getName());` e `recordFound.setCategory(course.getCategory());`: Estas linhas atualizam os atributos `name` 
     * e `category` do objeto `recordFound` (que é o registro encontrado no banco de dados) com os valores do objeto `course` passado no corpo da 
     * solicitação. Essa é a parte da atualização dos dados.
     * 
     * 5. `Course update = courseRepository.save(recordFound);`: Após a atualização dos atributos, o registro é salvo novamente no banco de dados 
     * usando o método `save` do repositório. O objeto `update` contém o registro atualizado após a operação de salvamento.
     * 
     * 6. `return ResponseEntity.ok().body(update);`: Se a atualização for bem-sucedida, este trecho de código cria um `ResponseEntity` com um 
     * código de status HTTP 200 (OK) e o corpo da resposta contém o objeto `update`, que é o registro atualizado.
     * 
     * 7. `.orElse(ResponseEntity.notFound().build());`: Se o método `findById(id)` não encontrar um registro com o ID fornecido, o `.orElse` é 
     * acionado e cria um `ResponseEntity` com um código de status HTTP 404 (Not Found). Isso indica que nenhum registro foi encontrado para 
     * atualização.
     * 
     * Em resumo, esse método lida com a atualização de um recurso `Course` no banco de dados com base no ID fornecido na solicitação PUT. Se o 
     * registro é encontrado, ele é atualizado com os novos valores e o registro atualizado é retornado. Se o registro não for encontrado, uma 
     * resposta de "Not Found" é retornada.
    */
    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course){
        return courseRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(course.getName());
                recordFound.setCategory(course.getCategory());
                Course update = courseRepository.save(recordFound);
                return ResponseEntity.ok().body(update);
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
