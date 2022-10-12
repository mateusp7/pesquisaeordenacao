package application;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


import entities.Conta;
import entities.ContaEspecial;
import estrutura.CadConta;

import java.util.ArrayList;

public class Program2 {
    public static final Scanner entrada = new Scanner(System.in);
    public static Path path = Paths.get("../contas.txt");
    static Conta conta;
    static ContaEspecial contaEspecial;
    static ArrayList<Conta> lista = new ArrayList<>();
    static CadConta listaContas = new CadConta(lista);
    public static void main(String[] args) throws IOException {
        if (!(path.toFile().exists())) {
            FileWriter out = new FileWriter("../contas.txt");
            PrintWriter arquivo = new PrintWriter(out);
        }  else {
            try {
                listaContas.limpar();
                lerContas();
                System.out.println("O arquivo de alunos foi lido com sucesso");
            }catch (Exception e) {
                System.out.println("====== Não foi possível carregar o arquivo ======");
            }
        }
        int opcao;
        do {
            mostrarMenu();
            opcao = entrada.nextInt();
            try {
                opcao = Integer.parseInt(String.valueOf(opcao));
            } catch (NumberFormatException e) {
                continue;
            }
            switch (opcao) {
                case 1 -> solicitarDados();
                case 2 -> consultar();
                case 5 -> gravarConta();
            }
        } while(opcao != 5);

    }

    public static ArrayList<Conta> lerContas() throws IOException {
        List<String> linhas = Files.readAllLines(path, StandardCharsets.UTF_8);
        int i = 0;
        String linha;
        String[] valorComSplit;
        while(i < (linhas.size())){
            linha = linhas.get(i);
            valorComSplit = linha.split(" - ");
            int number = Integer.parseInt(valorComSplit[0]);
            String numberString = "" + number;
            char firstLetterChar = numberString.charAt(0);
            int firstDigit = Integer.parseInt("" + firstLetterChar);
            if (firstDigit == 2) {
                listaContas.inserirVetor(new ContaEspecial(Integer.parseInt(valorComSplit[0]), valorComSplit[1],
                        valorComSplit[2], Double.parseDouble(valorComSplit[3])));
            } else {
                listaContas.inserirVetor(new Conta(Integer.parseInt(valorComSplit[0]), valorComSplit[1],
                        valorComSplit[2]));
            }
            i++;
        }
        return listaContas.getArrayContas();
    }

    public static void gravarConta() throws IOException {
        FileWriter escrever = new FileWriter(String.valueOf(path), StandardCharsets.UTF_8);

        for (Conta c : listaContas.getArrayContas()) {
            if (!(c instanceof ContaEspecial)) {
                escrever.write(c.getNumeroDaConta() + " - " + c.getCpf() + " - " + c.getNome() + " - " + c.getValorNaConta() + "\n");
            }
            if (c instanceof ContaEspecial) {
                ContaEspecial aux = (ContaEspecial) c;
                escrever.write(c.getNumeroDaConta() + " - " + c.getCpf() + " - " + c.getNome() + " - "
                        + c.getValorNaConta() + " - " + aux.saldo() +"\n");
            }
        }
        escrever.close();
    }

    public static void consultar() {
        if (listaContas.getArrayContas().size() == 0) {
            System.out.println("Não existem alunos para pesquisar!");
        } else {
            for (Conta c : listaContas.getArrayContas()) {
                if (!(c instanceof ContaEspecial)) {
                    System.out.println(c.getNumeroDaConta() + " - " + c.getCpf() + " - " + c.getNome() + " - " + c.getValorNaConta());
                }
                if (c instanceof ContaEspecial) {
                    ContaEspecial auxC = (ContaEspecial) c;
                    System.out.println(c.getNumeroDaConta() + " - " + c.getCpf() + " - " + c.getNome() + " - "
                            + c.getValorNaConta() + " - " + auxC.saldo());
                }
            }
        }
    }

    public static void solicitarDados() {
        System.out.println("Qual será o tipo da conta?");
        System.out.println(" [ 1 ] - Conta normal");
        System.out.println(" [ 2 ] - Conta especial");
        System.out.print(">");
        int opcao = entrada.nextInt();
        switch (opcao) {
            case 1 -> {
                System.out.print("Número da conta: ");
                int numeroDaConta = entrada.nextInt();
                String novoValor = (String.valueOf(numeroDaConta));
                String numeroDaContaFinal = "1" + novoValor;
                while (listaContas.pesqBinaria(numeroDaConta) != -1) {
                    System.out.print("Número da conta já existe, digite um novo número: ");
                    numeroDaConta = entrada.nextInt();
                    novoValor = (String.valueOf(numeroDaConta));
                    numeroDaContaFinal = "1" + novoValor;
                }

                entrada.nextLine();
                System.out.print("Nome: ");
                String nome = entrada.nextLine();

                System.out.print("CPF: ");
                String cpf = entrada.nextLine();

                try {
                    conta = new Conta(Integer.parseInt(numeroDaContaFinal), cpf, nome);
                    listaContas.inserirVetor(conta);
                    System.out.println("Conta inserida com sucesso");
                } catch (Exception e) {
                    System.out.println("Não foi possível criar uma nova conta " + e);
                }
            }
            case 2 -> {
                System.out.print("Número da conta: ");
                int numeroDaConta = entrada.nextInt();
                String novoValor = (String.valueOf(numeroDaConta));
                String numeroDaContaFinal = "2" + novoValor;
                while (listaContas.pesqBinaria(numeroDaConta) != -1) {
                    System.out.print("Número da conta já existe, digite um novo número: ");
                    numeroDaConta = entrada.nextInt();
                    novoValor = (String.valueOf(numeroDaConta));
                    numeroDaContaFinal = "2" + novoValor;
                }

                entrada.nextLine();
                System.out.print("Nome: ");
                String nome = entrada.nextLine();

                System.out.print("CPF: ");
                String cpf = entrada.nextLine();


                System.out.print("Limite especial: R$");
                double limiteEspecial = entrada.nextDouble();

                try {
                    contaEspecial = new ContaEspecial(Integer.parseInt(numeroDaContaFinal), cpf, nome, limiteEspecial);
                    listaContas.inserirVetor(contaEspecial);
                    System.out.println("Conta Especial inserida com sucesso");
                } catch (Exception e) {
                    System.out.println("Não foi possível criar uma nova conta especial " + e);
                }

            }
            default -> System.out.println("Opção inválida");
        }
    }

    public static void mostrarMenu() {
        System.out.println("======== Menu de opções ========");
        System.out.println("[ 1 ] - Inserir Conta");
        System.out.println("[ 2 ] - Consultar as contas");
        System.out.println("[ 3 ] - Remover Conta");
        System.out.println("[ 4 ] - Ler todas as contas");
        System.out.println("[ 5 ] - Finalizar programa e aplicar alterações");
        System.out.print(">");
    }

}
