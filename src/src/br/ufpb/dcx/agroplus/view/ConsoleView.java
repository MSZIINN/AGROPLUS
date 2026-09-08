package br.ufpb.dcx.agroplus.view;

import br.ufpb.dcx.agroplus.controller.*;
import br.ufpb.dcx.agroplus.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String BOLD = "\u001B[1m";

    private final AuthController authController;
    private final FuncionarioController funcionarioController;
    private final AnimalController animalController;
    private final EstoqueController estoqueController;
    private final ProducaoController producaoController;
    private final RelatorioController relatorioController;

    public ConsoleView() {
        this.authController = new AuthController();
        this.funcionarioController = new FuncionarioController();
        this.animalController = new AnimalController();
        this.estoqueController = new EstoqueController();
        this.producaoController = new ProducaoController();
        this.relatorioController = new RelatorioController();
    }

    public void iniciar() {
        exibirBoasVindas();
        while (true) {
            if (AuthController.getUsuarioLogado() == null) {
                new LoginView().exibir();
            } else {
                new DashboardView().exibir();
            }
        }
    }

    private void exibirBoasVindas() {
        System.out.println(GREEN + BOLD + "=============================================" + RESET);
        System.out.println(GREEN + BOLD + "       SISTEMA DE GESTÃO DE GRANJA           " + RESET);
        System.out.println(GREEN + BOLD + "               AGROPLUS                      " + RESET);
        System.out.println(GREEN + BOLD + "=============================================" + RESET);
        System.out.println(CYAN + "Bem-vindo ao AgroPlus! O seu gerenciador avícola." + RESET);
    }

    private class LoginView {
        public void exibir() {
            System.out.println("\n" + BOLD + "--- TELA DE AUTENTICAÇÃO ---" + RESET);
            System.out.print("E-mail: ");
            String email = scanner.nextLine().trim();
            System.out.print("Senha: ");
            String senha = scanner.nextLine().trim();
            if (authController.login(email, senha)) {
                System.out.println(GREEN + BOLD + "\n[SUCESSO] Login realizado com sucesso!" + RESET);
                System.out.println("Bem-vindo(a), " + BOLD + AuthController.getUsuarioLogado().getNomeCompleto() + RESET + " (" + AuthController.getUsuarioLogado().getCargo() + ")");
            } else {
                System.out.println(RED + BOLD + "\n[ERRO] E-mail ou senha incorretos." + RESET);
            }
        }
    }

    private class DashboardView {
        public void exibir() {
            System.out.println(GREEN + BOLD + "\n=============================================" + RESET);
            System.out.println(BOLD + "                 PAINEL PRINCIPAL            " + RESET);
            System.out.println(GREEN + BOLD + "=============================================" + RESET);
            int totalAnimais = animalController.listarAnimaisAtivos().size();
            List<String> alertas = estoqueController.verificarAlertasEstoque();
            System.out.println(CYAN + "Total de Animais Ativos: " + BOLD + totalAnimais + RESET);
            System.out.println(CYAN + "Alertas de Estoque Pendentes: " + BOLD + alertas.size() + RESET);
            for (String alerta : alertas) System.out.println(YELLOW + "  -> " + alerta + RESET);
            System.out.println("\nMenu:");
            System.out.println("1. Módulo de Animais");
            System.out.println("2. Módulo de Estoque e Consumo");
            System.out.println("3. Módulo de Produção");
            System.out.println("4. Geração de Relatórios");
            System.out.println("5. Gerenciamento de Funcionários");
            System.out.println("6. Alterar Minha Senha");
            System.out.println("7. Logout (Sair)");
            System.out.println("8. Fechar Programa");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine().trim();
            switch (opcao) {
                case "1": new AnimalView().exibir(); break;
                case "2": new EstoqueView().exibir(); break;
                case "3": new ProducaoView().exibir(); break;
                case "4": new RelatorioView().exibir(); break;
                case "5": new FuncionarioView().exibir(); break;
                case "6": alterarPropriaSenha(); break;
                case "7": authController.logout(); System.out.println(GREEN + "\nSessão encerrada com sucesso." + RESET); break;
                case "8": System.out.println(GREEN + "\nObrigado por utilizar o AgroPlus! Finalizando aplicação." + RESET); System.exit(0); break;
                default: System.out.println(RED + "Opção inválida!" + RESET);
            }
        }
        private void alterarPropriaSenha() {
            System.out.println("\n" + BOLD + "--- ALTERAR MINHA SENHA ---" + RESET);
            System.out.print("Digite a nova senha: ");
            String nova = scanner.nextLine().trim();
            if (nova.isEmpty()) { System.out.println(RED + "Senha não pode ser vazia." + RESET); return; }
            AuthController.getUsuarioLogado().alterarSenha(nova);
            System.out.println(GREEN + "[SUCESSO] Senha alterada com sucesso!" + RESET);
        }
    }

    private class AnimalView {
        public void exibir() {
            while (true) {
                System.out.println("\n" + BOLD + "--- MÓDULO DE ANIMAIS ---" + RESET);
                System.out.println("1. Listar Animais Ativos");
                System.out.println("2. Cadastrar Novo Animal");
                System.out.println("3. Atualizar Peso de Animal");
                System.out.println("4. Atualizar Situação de Saúde");
                System.out.println("5. Consultar Histórico Completo de Animal");
                System.out.println("6. Excluir Animal");
                System.out.println("7. Voltar ao Painel Principal");
                System.out.print("Escolha uma opção: ");
                String op = scanner.nextLine().trim();
                switch (op) {
                    case "1": listarAnimais(); break;
                    case "2": cadastrarAnimal(); break;
                    case "3": atualizarPeso(); break;
                    case "4": atualizarSaude(); break;
                    case "5": consultarHistorico(); break;
                    case "6": excluirAnimal(); break;
                    case "7": return;
                    default: System.out.println(RED + "Opção inválida!" + RESET);
                }
            }
        }
        private void listarAnimais() {
            System.out.println("\n" + BOLD + "--- ANIMAIS CADASTRADOS ---" + RESET);
            List<Animal> animais = animalController.listarAnimaisAtivos();
            if (animais.isEmpty()) { System.out.println("Nenhum animal cadastrado."); return; }
            System.out.printf("%-4s | %-10s | %-8s | %-6s | %-8s | %-12s | %-8s\n", "ID", "Identif.", "Espécie", "Idade", "Peso(kg)", "Saúde", "Lote");
            System.out.println("----------------------------------------------------------------------");
            for (Animal a : animais) System.out.printf("%-4d | %-10s | %-8s | %-3d mes | %-8.2f | %-12s | %-8s\n", a.getId(), a.getIdentificacaoUnica(), a.getEspecie(), a.getIdade(), a.getPesoAtual(), a.getSituacaoSaude(), a.getLote());
        }
        private void cadastrarAnimal() {
            System.out.println("\n" + BOLD + "--- CADASTRAR ANIMAL ---" + RESET);
            System.out.print("Identificação única (ex: GRN-005): "); String ident = scanner.nextLine().trim();
            System.out.print("Espécie (ex: Frango/Galinha): "); String especie = scanner.nextLine().trim();
            int idade = lerInteiro("Idade (em meses): "); float peso = lerFloat("Peso inicial (kg): ");
            System.out.print("Situação de Saúde (Saudavel, Doente, EmTratamento, Morto): "); String saude = scanner.nextLine().trim();
            System.out.print("Lote (ex: Lote A): "); String lote = scanner.nextLine().trim();
            try { animalController.cadastrarAnimal(ident, especie, idade, peso, saude, lote); } catch (Exception e) { System.out.println(RED + "[ERRO] Falha ao cadastrar: " + e.getMessage() + RESET); }
        }
        private void atualizarPeso() { int id = lerInteiro("Digite o ID do animal: "); float peso = lerFloat("Digite o novo peso (kg): "); try { animalController.atualizarPeso(id, peso); } catch (Exception e) { System.out.println(RED + "[ERRO] " + e.getMessage() + RESET); } }
        private void atualizarSaude() { int id = lerInteiro("Digite o ID do animal: "); System.out.print("Nova Situação de Saúde (Saudavel, Doente, EmTratamento, Morto): "); String saude = scanner.nextLine().trim(); try { animalController.atualizarSaude(id, saude); } catch (Exception e) { System.out.println(RED + "[ERRO] " + e.getMessage() + RESET); } }
        private void consultarHistorico() { int id = lerInteiro("Digite o ID do animal: "); try { HistoricoAnimal ha = animalController.obterHistorico(id); System.out.println(ha.exibirCompleto()); } catch (Exception e) { System.out.println(RED + "[ERRO] " + e.getMessage() + RESET); } }
        private void excluirAnimal() { int id = lerInteiro("Digite o ID do animal a ser excluído: "); try { animalController.excluirAnimal(id); } catch (Exception e) { System.out.println(RED + "[ERRO] " + e.getMessage() + RESET); } }
    }

    private class EstoqueView {
        public void exibir() {
            while (true) {
                System.out.println("\n" + BOLD + "--- MÓDULO DE ESTOQUE ---" + RESET);
                System.out.println("1. Listar Estoque Geral");
                System.out.println("2. Registrar Entrada de Insumo");
                System.out.println("3. Registrar Saída de Insumo");
                System.out.println("4. Listar Medicamentos/Vacinas");
                System.out.println("5. Aplicar Vacina/Medicamento em Animal");
                System.out.println("6. Registrar Consumo de Alimento por Animal");
                System.out.println("7. Voltar ao Painel Principal");
                System.out.print("Escolha uma opção: ");
                String op = scanner.nextLine().trim();
                switch (op) {
                    case "1": listarEstoque(); break;
                    case "2": registrarEntrada(); break;
                    case "3": registrarSaida(); break;
                    case "4": listarMedicamentos(); break;
                    case "5": aplicarMedicamento(); break;
                    case "6": registrarConsumo(); break;
                    case "7": return;
                    default: System.out.println(RED + "Opção inválida!" + RESET);
                }
            }
        }
        private void listarEstoque() { System.out.println("\n" + BOLD + "--- ESTOQUE ATUAL ---" + RESET); List<Estoque> estoques = estoqueController.listarEstoqueGeral(); System.out.printf("%-4s | %-20s | %-12s | %-8s | %-8s | %-15s\n", "ID", "Produto", "Qtd Atual", "Mínimo", "Máximo", "Local"); System.out.println("--------------------------------------------------------------------------------"); for (Estoque e : estoques) System.out.printf("%-4d | %-20s | %-8.2f %s | %-8.2f | %-8.2f | %-15s\n", e.getId(), e.getNomeProduto(), e.getQuantidadeAtual(), e.getUnidadeMedida(), e.getEstoqueMinimo(), e.getEstoqueMaximo(), e.getLocalArmazenamento()); }
        private void registrarEntrada() { int id = lerInteiro("ID do insumo no estoque: "); float qtd = lerFloat("Quantidade de entrada: "); System.out.print("Número da Nota Fiscal: "); String nf = scanner.nextLine().trim(); try { estoqueController.registrarEntradaInsumo(id, qtd, nf); } catch (Exception e) { System.out.println(RED + "[ERRO] " + e.getMessage() + RESET); } }
        private void registrarSaida() { int id = lerInteiro("ID do insumo no estoque: "); float qtd = lerFloat("Quantidade de saída: "); System.out.print("Motivo: "); String motivo = scanner.nextLine().trim(); try { estoqueController.registrarSaidaInsumo(id, qtd, motivo); } catch (Exception e) { System.out.println(RED + "[ERRO] " + e.getMessage() + RESET); } }
        private void listarMedicamentos() { System.out.println("\n" + BOLD + "--- VACINAS E MEDICAMENTOS EM ESTOQUE ---" + RESET); List<VacinaMedicamento> lista = estoqueController.listarMedicamentos(); for (VacinaMedicamento vm : lista) System.out.println(vm); }
        private void aplicarMedicamento() { int animalId = lerInteiro("ID do animal: "); int produtoId = lerInteiro("ID da vacina/medicamento: "); System.out.print("Dose: "); String dose = scanner.nextLine().trim(); System.out.print("Observações: "); String obs = scanner.nextLine().trim(); try { estoqueController.aplicarVacinaMedicamento(animalId, produtoId, dose, obs); } catch (Exception e) { System.out.println(RED + "[ERRO] " + e.getMessage() + RESET); } }
        private void registrarConsumo() { int animalId = lerInteiro("ID do animal: "); int alimentoId = lerInteiro("ID do alimento: "); float quantidade = lerFloat("Quantidade consumida: "); try { estoqueController.registrarConsumoAnimal(animalId, alimentoId, quantidade); } catch (Exception e) { System.out.println(RED + "[ERRO] " + e.getMessage() + RESET); } }
    }

    private class ProducaoView {
        public void exibir() {
            while (true) {
                System.out.println("\n" + BOLD + "--- MÓDULO DE PRODUÇÃO ---" + RESET);
                System.out.println("1. Registrar Produção Diária");
                System.out.println("2. Listar Produções");
                System.out.println("3. Voltar ao Painel Principal");
                System.out.print("Escolha uma opção: ");
                String op = scanner.nextLine().trim();
                switch (op) { case "1": registrarProducao(); break; case "2": listarProducoes(); break; case "3": return; default: System.out.println(RED + "Opção inválida!" + RESET); }
            }
        }
        private void registrarProducao() { LocalDate data = lerData("Data (dd/MM/yyyy): "); int qtd = lerInteiro("Quantidade de ovos: "); System.out.print("Lote: "); String lote = scanner.nextLine().trim(); System.out.print("Observações: "); String obs = scanner.nextLine().trim(); try { producaoController.registrarProducao(data, qtd, lote, obs); } catch (Exception e) { System.out.println(RED + "[ERRO] " + e.getMessage() + RESET); } }
        private void listarProducoes() { List<ProducaoDiaria> lista = producaoController.listarProducoes(); if (lista.isEmpty()) System.out.println("Nenhuma produção registrada."); else for (ProducaoDiaria p : lista) System.out.println(p); }
    }

    private class RelatorioView {
        public void exibir() {
            while (true) {
                System.out.println("\n" + BOLD + "--- RELATÓRIOS ---" + RESET);
                System.out.println("1. Relatório de Animais");
                System.out.println("2. Relatório de Estoque");
                System.out.println("3. Relatório de Produção");
                System.out.println("4. Voltar ao Painel Principal");
                System.out.print("Escolha uma opção: ");
                String op = scanner.nextLine().trim();
                switch (op) { case "1": System.out.println(relatorioController.gerarRelatorioAnimais()); break; case "2": System.out.println(relatorioController.gerarRelatorioEstoque()); break; case "3": System.out.println(relatorioController.gerarRelatorioProducao()); break; case "4": return; default: System.out.println(RED + "Opção inválida!" + RESET); }
            }
        }
    }

    private class FuncionarioView {
        public void exibir() {
            while (true) {
                System.out.println("\n" + BOLD + "--- FUNCIONÁRIOS ---" + RESET);
                System.out.println("1. Listar Funcionários");
                System.out.println("2. Cadastrar Funcionário");
                System.out.println("3. Alterar Senha");
                System.out.println("4. Voltar ao Painel Principal");
                System.out.print("Escolha uma opção: ");
                String op = scanner.nextLine().trim();
                switch (op) { case "1": listarFuncionarios(); break; case "2": cadastrarFuncionario(); break; case "3": alterarSenhaFuncionario(); break; case "4": return; default: System.out.println(RED + "Opção inválida!" + RESET); }
            }
        }
        private void listarFuncionarios() { List<Funcionario> lista = funcionarioController.listarFuncionarios(); if (lista.isEmpty()) System.out.println("Nenhum funcionário cadastrado."); else for (Funcionario f : lista) System.out.println(f); }
        private void cadastrarFuncionario() { System.out.print("Nome completo: "); String nome = scanner.nextLine().trim(); System.out.print("E-mail: "); String email = scanner.nextLine().trim(); System.out.print("Senha: "); String senha = scanner.nextLine().trim(); System.out.print("Cargo: "); String cargo = scanner.nextLine().trim(); try { funcionarioController.cadastrarFuncionario(nome, email, senha, cargo); } catch (Exception e) { System.out.println(RED + "[ERRO] " + e.getMessage() + RESET); } }
        private void alterarSenhaFuncionario() { int id = lerInteiro("ID do funcionário: "); System.out.print("Nova senha: "); String senha = scanner.nextLine().trim(); try { funcionarioController.alterarSenha(id, senha); } catch (Exception e) { System.out.println(RED + "[ERRO] " + e.getMessage() + RESET); } }
    }

    private int lerInteiro(String mensagem) {
        while (true) { try { System.out.print(mensagem); return Integer.parseInt(scanner.nextLine().trim()); } catch (NumberFormatException e) { System.out.println(RED + "Digite um número inteiro válido." + RESET); } }
    }
    private float lerFloat(String mensagem) {
        while (true) { try { System.out.print(mensagem); return Float.parseFloat(scanner.nextLine().trim().replace(',', '.')); } catch (NumberFormatException e) { System.out.println(RED + "Digite um número válido." + RESET); } }
    }
    private LocalDate lerData(String mensagem) {
        while (true) { try { System.out.print(mensagem); return LocalDate.parse(scanner.nextLine().trim(), dateFormatter); } catch (DateTimeParseException e) { System.out.println(RED + "Data inválida. Use dd/MM/yyyy." + RESET); } }
    }
}
