package br.edu.faculdade.vagas;
// teste push
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VagaEntrada(
        @NotBlank(message = "o título é obrigatório")
        @Size(max = 120)
        String titulo,

        @NotBlank String area,
        @NotBlank String senioridade,
        @NotBlank String empresaSlug,

        //String descricao,
        String local,
        boolean aceitaIniciante
) { }