package br.edu.faculdade.vagas;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController //indica que essa classe recebe requisições da API e devolve respostas, geralmente em formato JSON.
@RequestMapping("/empresas")//define o caminho principal das rotas dessa classe.
public class EmpresaController {

    private final EmpresaService servico;//Essa linha cria um atributo chamado servico, que permite ao Controller utilizar as funções do EmpresaService.

    public EmpresaController(EmpresaService servico) {
        this.servico = servico;
    }

    @GetMapping//Esse metodo lista todas as empresas cadastradas, chamando o serviço responsável por buscar os dados.
    public List<Empresa> listar() {
        return servico.listar();
    }

    @GetMapping("/{slug}")//Esse metodo busca uma empresa pelo slug. Se encontrar, retorna os dados dela; caso contrario, retorna um erro informando que ela nao foi encontrada
    public ResponseEntity<Empresa> porSlug(@PathVariable String slug) {
        return servico.buscarPorSlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping//Esse metodo recebe os dados de uma nova empresa, valida essas informações, solicita a criação ao serviço e retorna os dados da empresa cadastrada
    public ResponseEntity<EmpresaResposta> criar(@Valid @RequestBody EmpresaEntrada nova) {
        EmpresaResposta salva = servico.criar(nova);
        URI onde = URI.create("/empresas/" + salva.slug());
        return ResponseEntity.created(onde).body(salva);
    }

    @DeleteMapping("/{slug}")//Esse metodo tenta excluir uma empresa pelo slug. Se conseguir, retorna sucesso; se a empresa não existir, retorna um erro informando que ela não foi encontrada.
    public ResponseEntity<Void> apagar(@PathVariable String slug) {
        return servico.apagar(slug)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}