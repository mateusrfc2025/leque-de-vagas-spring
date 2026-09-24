package br.edu.faculdade.vagas;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VagaRepository {

    private final List<Vaga> vagas = new ArrayList<>(List.of(
            new Vaga("1", "Pessoa Desenvolvedora Front-end Júnior",
                    "Front-end", "Júnior", "Remoto", true, "aurora-tech"),

            new Vaga("2", "Estágio em Front-end",
                    "Front-end", "Estágio", "Presencial · Olinda", true, "mareh-digital"),

            new Vaga("3", "Pessoa Desenvolvedora Front-end Pleno",
                    "Front-end", "Pleno", "Remoto", false, "nuvem-rosa"),

            new Vaga("4", "Pessoa Desenvolvedora React Júnior",
                    "Front-end", "Júnior", "Híbrido · Recife", true, "coral-labs"),

            new Vaga("5", "Pessoa Desenvolvedora Java Júnior",
                    "Back-end", "Júnior", "Remoto", true, "tucano-systems"),

            new Vaga("6", "Pessoa Desenvolvedora Back-end Pleno",
                    "Back-end", "Pleno", "Remoto", false, "aurora-tech"),

            new Vaga("7", "Pessoa Desenvolvedora Node Pleno",
                    "Back-end", "Pleno", "Híbrido · Recife", false, "coral-labs"),

            new Vaga("8", "Analista de Dados Júnior",
                    "Dados", "Júnior", "Híbrido · Recife", true, "nuvem-rosa"),

            new Vaga("9", "Estágio em Dados",
                    "Dados", "Estágio", "Remoto", true, "nuvem-rosa"),

            new Vaga("10", "Pessoa Engenheira de Dados Pleno",
                    "Dados", "Pleno", "Remoto", false, "tucano-systems"),

            new Vaga("11", "Pessoa Desenvolvedora Mobile Júnior",
                    "Mobile", "Júnior", "Remoto", true, "mareh-digital"),

            new Vaga("12", "Pessoa Desenvolvedora Android Júnior",
                    "Mobile", "Júnior", "Presencial · Recife", false, "aurora-tech")
    ));

    public List<Vaga> todas() {
        return List.copyOf(vagas);
    }

    public Optional<Vaga> porId(String id) {
        return vagas.stream()
                .filter(v -> v.id().equals(id))
                .findFirst();
    }

    public Vaga salvar(Vaga nova) {
        vagas.add(nova);
        return nova;
    }

    public Optional<Vaga> trocar(String id, Vaga nova) {
        for (int i = 0; i < vagas.size(); i++) {
            if (vagas.get(i).id().equals(id)) {
                vagas.set(i, nova);
                return Optional.of(nova);
            }
        }
        return Optional.empty();
    }

    public boolean remover(String id) {
        return vagas.removeIf(v -> v.id().equals(id));
    }

}