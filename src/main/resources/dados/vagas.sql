INSERT INTO vaga (titulo, descricao, area, senioridade, local, aceita_iniciante, empresa_slug)
SELECT * FROM (VALUES
                   ('Pessoa Desenvolvedora Front-end Júnior', 'Desenvolvimento de interfaces web', 'Front-end', 'Júnior', 'Remoto', true, 'aurora-tech'),
                   ('Estágio em Front-end', 'Estágio para aprender desenvolvimento front-end', 'Front-end', 'Estágio', 'Presencial · Olinda', true, 'mareh-digital'),
                   ('Pessoa Desenvolvedora Front-end Pleno', 'Desenvolvimento de interfaces web com experiência', 'Front-end', 'Pleno', 'Remoto', false, 'nuvem-rosa'),
                   ('Pessoa Desenvolvedora React Júnior', 'Desenvolvimento de aplicações com React', 'Front-end', 'Júnior', 'Híbrido · Recife', true, 'coral-labs'),
                   ('Pessoa Desenvolvedora Java Júnior', 'Desenvolvimento de sistemas utilizando Java', 'Back-end', 'Júnior', 'Remoto', true, 'tucano-systems'),
                   ('Pessoa Desenvolvedora Back-end Pleno', 'Desenvolvimento e manutenção de aplicações back-end', 'Back-end', 'Pleno', 'Remoto', false, 'aurora-tech'),
                   ('Pessoa Desenvolvedora Node Pleno', 'Desenvolvimento de aplicações utilizando Node.js', 'Back-end', 'Pleno', 'Híbrido · Recife', false, 'coral-labs'),
                   ('Analista de Dados Júnior', 'Análise e interpretação de dados', 'Dados', 'Júnior', 'Híbrido · Recife', true, 'nuvem-rosa'),
                   ('Estágio em Dados', 'Estágio para aprender análise de dados', 'Dados', 'Estágio', 'Remoto', true, 'nuvem-rosa'),
                   ('Pessoa Engenheira de Dados Pleno', 'Construção e manutenção de pipelines de dados', 'Dados', 'Pleno', 'Remoto', false, 'tucano-systems'),
                   ('Pessoa Desenvolvedora Mobile Júnior', 'Desenvolvimento de aplicativos para dispositivos móveis', 'Mobile', 'Júnior', 'Remoto', true, 'mareh-digital'),
                   ('Pessoa Desenvolvedora Android Júnior', 'Desenvolvimento de aplicativos para Android', 'Mobile', 'Júnior', 'Presencial · Recife', false, 'aurora-tech')
              ) AS carga (titulo, descricao, area, senioridade, local, aceita_iniciante, empresa_slug)
WHERE NOT EXISTS (SELECT 1 FROM vaga);