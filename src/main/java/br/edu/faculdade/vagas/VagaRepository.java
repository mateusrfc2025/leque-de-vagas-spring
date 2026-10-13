package br.edu.faculdade.vagas;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VagaRepository {

    private final List<Vaga> vagas = new ArrayList<>(List.of(
            new Vaga("1", "Pessoa Desenvolvedora Front-end Júnior",
                    "Desenvolvimento de interfaces web",
                    "Front-end", "Júnior", "Remoto", true, "aurora-tech"),

            new Vaga("2", "Estágio em Front-end",
                    "Estágio para aprender desenvolvimento front-end",
                    "Front-end", "Estágio", "Presencial · Olinda", true, "mareh-digital"),

            new Vaga("3", "Pessoa Desenvolvedora Front-end Pleno",
                    "Desenvolvimento de interfaces web com experiência",
                    "Front-end", "Pleno", "Remoto", false, "nuvem-rosa"),

            new Vaga("4", "Pessoa Desenvolvedora React Júnior",
                    "Desenvolvimento de aplicações com React",
                    "Front-end", "Júnior", "Híbrido · Recife", true, "coral-labs"),

            new Vaga("5", "Pessoa Desenvolvedora Java Júnior",
                    "Desenvolvimento de sistemas utilizando Java",
                    "Back-end", "Júnior", "Remoto", true, "tucano-systems"),

            new Vaga("6", "Pessoa Desenvolvedora Back-end Pleno",
                    "Desenvolvimento e manutenção de aplicações back-end",
                    "Back-end", "Pleno", "Remoto", false, "aurora-tech"),

            new Vaga("7", "Pessoa Desenvolvedora Node Pleno",
                    "Desenvolvimento de aplicações utilizando Node.js",
                    "Back-end", "Pleno", "Híbrido · Recife", false, "coral-labs"),

            new Vaga("8", "Analista de Dados Júnior",
                    "Análise e interpretação de dados",
                    "Dados", "Júnior", "Híbrido · Recife", true, "nuvem-rosa"),

            new Vaga("9", "Estágio em Dados",
                    "Estágio para aprender análise de dados",
                    "Dados", "Estágio", "Remoto", true, "nuvem-rosa"),

            new Vaga("10", "Pessoa Engenheira de Dados Pleno",
                    "Construção e manutenção de pipelines de dados",
                    "Dados", "Pleno", "Remoto", false, "tucano-systems"),

            new Vaga("11", "Pessoa Desenvolvedora Mobile Júnior",
                    "Desenvolvimento de aplicativos para dispositivos móveis",
                    "Mobile", "Júnior", "Remoto", true, "mareh-digital"),

            new Vaga("12", "Pessoa Desenvolvedora Android Júnior",
                    "Desenvolvimento de aplicativos para Android",
                    "Mobile", "Júnior", "Presencial · Recife", false, "aurora-tech")
    ));

    public List<Vaga> todas() {
        return List.copyOf(vagas);
    }

    public Optional<Vaga> porId(String id) {
        return vagas.stream()
                .filter(vaga -> vaga.id().equals(id))
                .findFirst();
    }

    public Vaga salvar(Vaga nova) {
        vagas.add(nova);
        return nova;
    }

    public Optional<Vaga> trocar(String id, Vaga nova) {
        for (int indice = 0; indice < vagas.size(); indice++) {
            if (vagas.get(indice).id().equals(id)) {
                vagas.set(indice, nova);
                return Optional.of(nova);
            }
        }

        return Optional.empty();
    }

    public boolean remover(String id) {
        return vagas.removeIf(vaga -> vaga.id().equals(id));
    }
}
