package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmpresaService {

    private final EmpresaRepository repositorio;

    public EmpresaService(EmpresaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Empresa> listar() {
        return repositorio.todas();
    }

    public Optional<Empresa> buscarPorSlug(String slug) {
        return repositorio.porSlug(slug);
    }

    public EmpresaResposta criar(EmpresaEntrada nova) {//valida se ja tem uma empresa com os mesmos campos criados
        if (repositorio.porSlug(nova.slug()).isPresent()) {
            throw new SlugJaExisteException(nova.slug());//devolve um erro caso a empresa a ser criada ja exista
        }

        Empresa comId = new Empresa(//caso passe pela validacao cria a empresa nova com os campos usados e da um id para ela
                UUID.randomUUID().toString(),
                nova.nome(),
                nova.slug(),
                nova.site(),
                nova.descricao()
        );

        Empresa salva = repositorio.salvar(comId);// salva a empresa criada com o id dela

        return new EmpresaResposta(//devolve a empresa criada como resposta da entrada
                salva.id(), salva.nome(), salva.slug(), salva.site(), salva.descricao()
        );
    }
    public boolean apagar(String slug) {
        return repositorio.remover(slug);
    }
}