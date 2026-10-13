package br.edu.faculdade.vagas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VagaEntrada(

        @NotBlank(message = "O título é obrigatório")
        @Size(max = 120, message = "O título deve ter no máximo 120 caracteres")
        String titulo,

        @NotBlank(message = "A área é obrigatória")
        String area,

        @NotBlank(message = "A senioridade é obrigatória")
        String senioridade,

        @NotBlank(message = "O slug da empresa é obrigatório")
        String empresaSlug,

        String descricao,

        String local,

        boolean aceitaIniciante

) {
}
