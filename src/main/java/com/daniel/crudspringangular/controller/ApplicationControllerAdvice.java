package com.daniel.crudspringangular.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.daniel.crudspringangular.exception.RecordNotFoundException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(RecordNotFoundException ex){
        return ex.getMessage(); 
    }
    

    // Essa exceção vai ser disparada para casos de @NotNull e @NotBlank
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)                   // Error 400 - requisição não válida
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()                    // Aqui, começa a processar a lista de erros de validação. getBindingResult() retorna o resultado da validação, que inclui a lista de erros. getFieldErrors() retorna a lista de erros de campo. stream() converte essa lista em um fluxo, que pode ser processado de forma mais eficiente.
                .map(error -> error.getField() + " " + error.getDefaultMessage()) // Aqui, linha processa cada erro na lista. error.getField() retorna o nome do campo que gerou o erro, e error.getDefaultMessage() retorna a mensagem de erro padrão para esse campo. Essas informações são concatenadas com um espaço entre elas.
                .reduce("", (acc, error) -> acc + error + "\n");         // Aqui, reduz o fluxo de erros em uma única string. O primeiro parâmetro "" é o valor inicial do acumulador. O segundo parâmetro é uma função que é chamada para cada erro no fluxo. Essa função recebe o acumulador atual e o erro atual, e retorna o novo valor do acumulador. Nesse caso, o erro atual é concatenado ao acumulador, seguido de uma quebra de linha (\n). O resultado final é uma string com todos os erros, cada um em uma linha separada.
    }  


    // Essa exceção vai ser disparada para casos de @Positive (id tem que ser maior que zero)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) 
    public String handleConstraintViolationException(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()                                // Aqui, começa a processar o conjunto de violações de restrição. getConstraintViolations() retorna o conjunto de violações de restrição. stream() converte esse conjunto em um fluxo, que pode ser processado de forma mais eficiente.
                .map(error -> error.getPropertyPath() + " " + error.getMessage())   // Aqui, processa cada violação de restrição no fluxo. error.getPropertyPath() retorna o caminho da propriedade que gerou a violação, e error.getMessage() retorna a mensagem de erro para essa violação. Essas informações são concatenadas com um espaço entre elas.
                .reduce("", (acc, error) -> acc + error + "\n");           // Aqui, reduz o fluxo de violações de restrição em uma única string. O primeiro parâmetro "" é o valor inicial do acumulador. O segundo parâmetro é uma função que é chamada para cada violação no fluxo. Essa função recebe o acumulador atual e a violação atual, e retorna o novo valor do acumulador. Nesse caso, a violação atual é concatenada ao acumulador, seguida de uma quebra de linha (\n). O resultado final é uma string com todas as violações de restrição, cada uma em uma linha separada.
    }


    //Essa exceção vai disparar para casos de parametros passados de forma errada (Ex: http://localhost:8080/api/courses/abc)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        if (ex != null && ex.getRequiredType() != null) {                            // Esta condição verifica se a exceção não é nula e se o tipo requerido não é nulo. Isso é importante para garantir que há informações válidas para processar. 
            String type = ex.getRequiredType().getName();                            // Aqui, obtém o nome do tipo requerido pela exceção.
            String[] typeParts = type.split("\\.");                            // Aqui, divide o nome do tipo em partes, usando o ponto como delimitador. Isso é útil para extrair o nome do tipo sem o pacote qualificado. Vai ser extraido o "java.lang" do pacote java.lang.Long deixando apenas o "Long"
            String typeName = typeParts[typeParts.length - 1];                       // Aqui, o nome do tipo é extraído da última parte após a divisão. Isso é feito para obter apenas o nome do tipo sem o pacote. Só vai ser processado o "Long".
            return ex.getName() + " should be of type " + typeName;                  // Aqui, retorna uma mensagem indicando que o argumento deveria ser do tipo correto. Combina o nome do argumento com a mensagem informativa sobre o tipo esperado.
        }
        return "Argument type not valid";
    }
}
