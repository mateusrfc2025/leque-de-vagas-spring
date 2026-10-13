package br.edu.faculdade.vagas;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErros {

    // Corpo inválido (@Valid no @RequestBody) → 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail campoInvalido(MethodArgumentNotValidException e) {
        Map<String, String> erros = new LinkedHashMap<>();
        e.getBindingResult()
                .getFieldErrors()
                .forEach(f -> erros.put(f.getField(), f.getDefaultMessage()));

        return problema(HttpStatus.BAD_REQUEST, "Dados inválidos",
                "Um ou mais campos estão inválidos.", erros);
    }

    // Parâmetro de URL inválido (@Size no @RequestParam) → 400
    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail parametroInvalido(ConstraintViolationException e) {
        Map<String, String> erros = new LinkedHashMap<>();
        e.getConstraintViolations().forEach(v -> {
            String caminho = v.getPropertyPath().toString();   // ex.: "buscar.area"
            String campo = caminho.substring(caminho.lastIndexOf('.') + 1);
            erros.put(campo, v.getMessage());
        });

        return problema(HttpStatus.BAD_REQUEST, "Parâmetros inválidos",
                "Um ou mais parâmetros estão inválidos.", erros);
    }

    // Slug de empresa repetido → 409
    @ExceptionHandler(SlugJaExisteException.class)
    public ProblemDetail slugRepetido(SlugJaExisteException e) {
        Map<String, String> erros = new LinkedHashMap<>();
        erros.put("slug", e.getMessage());

        return problema(HttpStatus.CONFLICT, "Slug já existe",
                e.getMessage(), erros);
    }

    private ProblemDetail problema(HttpStatus status, String titulo,
                                   String detalhe, Map<String, String> campos) {
        ProblemDetail p = ProblemDetail.forStatusAndDetail(status, detalhe);
        p.setTitle(titulo);
        p.setProperty("campos", campos);
        return p;
    }
}