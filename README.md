# AgroPlus | Sistema de Gestão de Granja

> Projeto desenvolvido para a disciplina de Análise e Projeto de Sistemas (APS) — UFPB, Campus IV, Rio Tinto.

O **AgroPlus** é uma solução de software desenvolvida em Java, projetada para operar via Interface de Linha de Comando (CLI). O sistema auxilia no gerenciamento de uma granja, centralizando o controle de animais, produção, estoque, alimentação, saúde e funcionários.

## Resumo das Funcionalidades

### Gestão de Animais
- Cadastro e gerenciamento de animais.
- Registro e acompanhamento de peso.
- Controle da situação de saúde dos animais.
- Consulta do histórico e informações dos animais.
- Organização dos animais por lote.

### Controle de Saúde
- Cadastro de vacinas e medicamentos.
- Registro da aplicação de medicamentos e vacinas.
- Controle de validade e quantidade dos produtos.
- Registro de visitas veterinárias.
- Acompanhamento das informações de saúde dos animais.

### Gestão de Estoque
- Cadastro e gerenciamento de produtos.
- Controle de entrada e saída de insumos.
- Controle de alimentos, vacinas e medicamentos.
- Definição de estoque mínimo.
- Identificação de produtos com estoque baixo.

### Produção de Ovos
- Registro da produção diária de ovos.
- Associação da produção aos lotes.
- Consulta da produção por período.
- Acompanhamento do desempenho produtivo da granja.

### Gestão de Funcionários
- Cadastro e gerenciamento de funcionários.
- Controle de usuários do sistema.
- Autenticação por login e senha.
- Controle de permissões de acesso.
- Alteração de senha e encerramento de sessão.

### Relatórios
- Geração de relatórios do sistema.
- Relatórios de produção, estoque e saúde.
- Organização das informações para acompanhamento da granja.
- Exportação de informações para arquivos.

## Tecnologias e Padrões Utilizados

- **Linguagem:** Java
- **Interface:** CLI
- **Paradigma:** Programação Orientada a Objetos (POO)
- **Arquitetura:** Model, Controller, Repository e View
- **Gerenciamento de Dados:** Collections Java
- **Datas:** `LocalDate`
- **Padrão de Projeto:** Singleton
- **Controle de Acesso:** Autenticação e permissões

## Estrutura do Sistema

- `br.ufpb.dcx.agroplus.model`: Entidades e modelos.
- `br.ufpb.dcx.agroplus.controller`: Operações e regras de negócio.
- `br.ufpb.dcx.agroplus.repository`: Gerenciamento e armazenamento dos dados.
- `br.ufpb.dcx.agroplus.view`: Interface de interação pelo terminal.
- `br.ufpb.dcx.agroplus`: Ponto de entrada da aplicação.

## Documentação

O projeto inclui diagramas de casos de uso, classes, sequência, arquitetura e documentação dos padrões de projeto utilizados.

## Equipe de Desenvolvimento

- **Mariano Santos da Silva** - Desenvolvimento
- **Rhauany Aragão** - Desenvolvimento
