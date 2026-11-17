package br.edu.faculdade.vagas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record EmpresaEntrada(

        @NotBlank(message = "o nome é obrigatório")
        String nome,

        @NotBlank(message = "o slug é obrigatório")
        @Size(max = 60, message = "o slug deve ter no máximo 60 caracteres")
        @Pattern(regexp = "[a-z0-9-]+", message = "o slug só aceita minúsculas, números e hífen")
        String slug,

        @URL(message = "o site precisa ser uma URL válida")
        String site,

        @Size(max = 1000, message = "a descrição deve ter no máximo 1000 caracteres")
        String descricao
) { }