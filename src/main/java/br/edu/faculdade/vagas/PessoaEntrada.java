package br.edu.faculdade.vagas;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PessoaEntrada(

        @NotBlank(message = "o nome é obrigatório")
        String nome,

        @NotBlank(message = "o e-mail é obrigatório")
        @Email(message = "o e-mail precisa ter um formato válido")
        String email,

        @NotBlank(message = "a área é obrigatória")
        String area,

        @NotBlank(message = "a senioridade é obrigatória")
        String senioridade
) { }