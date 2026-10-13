package br.edu.faculdade.vagas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;

public record EmpresaEntrada(//Esse código define os dados recebidos para cadastrar uma empresa e garante que o campo nome seja preenchido obrigatoriamente.
        @NotBlank(message = "o nome é obrigatório")//Essa parte garante que o nome seja preenchido e mostra uma mensagem de erro caso esteja vazio.
        String nome,

        @NotBlank(message = "o slug é obrigatório")//Essa validação garante que o slug da empresa seja informado obrigatoriamente.
        @Pattern(regexp = "[a-z0-9-]+", message = "o slug só aceita minúsculas, números e hífen")
        String slug,//garante que o slug tenha apenas letras minusculas, numeros e hifens

        @URL(message = "o site precisa ser uma URL válida")
        String site,//garante que a string site esteja em formato de caminho url

        String descricao
) { }