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
            System.out.println("\n======== Arquivo de contas criado com sucesso ========\n");
        }  else {
            try {
                listaContas.limpar();
                lerContasDoArquivoEInserirNoArray();
                System.out.println("\n======== O arquivo de contas foi lido com sucesso! ========\n");
            }catch (Exception e) {
                System.out.println("\n====== Não foi possível carregar o arquivo ======\n");
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
                case 1 -> solicitarDadosDaConta();
                case 2 -> mostrarContas();
                case 5 -> gravarContasESalvarAlteracoes();
            }
        } while(opcao != 5);

    }

    public static ArrayList<Conta> lerContasDoArquivoEInserirNoArray() throws IOException {
        List<String> linhas = Files.readAllLines(path, StandardCharsets.UTF_8);
        int i = 0;
        String linha;
        String[] valorComSplit;
        while(i < (linhas.size())){
            linha = linhas.get(i);
            valorComSplit = linha.split(" - ");

            int numero = Integer.parseInt(valorComSplit[0]);
            String numeroString = "" + numero;
            char primeriaLetraComoChar = numeroString.charAt(0);
            int primeiroDigitoDaConta = Integer.parseInt("" + primeriaLetraComoChar);


            if (primeiroDigitoDaConta == 2) {
                listaContas.inserirVetor(new ContaEspecial(Integer.parseInt(valorComSplit[0]), valorComSplit[1],
                        valorComSplit[2], Double.parseDouble(valorComSplit[4])));
            } else {
                listaContas.inserirVetor(new Conta(Integer.parseInt(valorComSplit[0]), valorComSplit[1],
                        valorComSplit[2]));
            }
            i++;
        }
        return listaContas.getArrayContas();
    }

    public static void gravarContasESalvarAlteracoes() throws IOException {
        FileWriter escreverArquivoTxt = new FileWriter(String.valueOf(path), StandardCharsets.UTF_8);
        listaContas.quicksort(listaContas.getArrayContas());
        try {
            for (Conta c : listaContas.getArrayContas()) {
                if (!(c instanceof ContaEspecial)) {
                    escreverArquivoTxt.write(c.getNumeroDaConta() + " - " + c.getCpf() + " - " + c.getNome() + " - " + c.getValorNaConta() + "\n");
                }
                if (c instanceof ContaEspecial) {
                    ContaEspecial aux = (ContaEspecial) c;
                    escreverArquivoTxt.write(c.getNumeroDaConta() + " - " + c.getCpf() + " - " + c.getNome() + " - "
                            + c.getValorNaConta() + " - " + aux.saldo() +"\n");
                }
            }
            System.out.println("\n======== Contas armazenadas com sucesso ========\n");
        }
        catch (Exception e) {
            System.out.println("\n======== Não foi possível gravar as contas ========\n");
        }
        escreverArquivoTxt.close();
    }

    public static void mostrarContas() {
        if (listaContas.getArrayContas().size() == 0) {
            System.out.println("Não existem contas registradas!");
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

    public static void solicitarDadosDaConta() {
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
                String numeroDaContaMaisDigito = "1" + novoValor;
                int numeroDaContaFinal = Integer.parseInt(numeroDaContaMaisDigito);
                while (listaContas.pesqBinaria(numeroDaConta) != -1) {
                    System.out.print("Número da conta já existe, digite um novo número: ");
                    numeroDaConta = entrada.nextInt();
                    novoValor = (String.valueOf(numeroDaConta));
                    numeroDaContaMaisDigito = "1" + novoValor;
                    numeroDaContaFinal = Integer.parseInt(numeroDaContaMaisDigito);
                }

                entrada.nextLine();
                System.out.print("Nome: ");
                String nome = entrada.nextLine();

                System.out.print("CPF: ");
                String cpf = entrada.nextLine();

                try {
                    conta = new Conta(numeroDaContaFinal, cpf, nome);
                    listaContas.inserirVetor(conta);
                    System.out.println("\nConta inserida com sucesso\n");
                } catch (Exception e) {
                    System.out.println("\nNão foi possível criar uma nova conta " + e + "\n");
                }
            }
            case 2 -> {
                System.out.print("Número da conta: ");
                int numeroDaConta = entrada.nextInt();
                String novoValor = (String.valueOf(numeroDaConta));
                String numeroDaContaMaisDigito = "2" + novoValor;
                int numeroDaContaFinal = Integer.parseInt(numeroDaContaMaisDigito);
                while (listaContas.pesqBinaria(numeroDaConta) != -1) {
                    System.out.print("Número da conta já existe, digite um novo número: ");
                    numeroDaConta = entrada.nextInt();
                    novoValor = (String.valueOf(numeroDaConta));
                    numeroDaContaMaisDigito = "2" + novoValor;
                    numeroDaContaFinal = Integer.parseInt(numeroDaContaMaisDigito);
                }

                entrada.nextLine();
                System.out.print("Nome: ");
                String nome = entrada.nextLine();

                System.out.print("CPF: ");
                String cpf = entrada.nextLine();


                System.out.print("Limite especial: R$");
                double limiteEspecial = entrada.nextDouble();

                try {
                    contaEspecial = new ContaEspecial(numeroDaContaFinal, cpf, nome, limiteEspecial);
                    listaContas.inserirVetor(contaEspecial);
                    System.out.println("\nConta Especial inserida com sucesso\n");
                } catch (Exception e) {
                    System.out.println("\nNão foi possível criar uma nova conta especial " + e + "\n");
                }

            }
            default -> System.out.println("Opção inválida");
        }
    }

    public static void mostrarMenu() {
        System.out.println("======== Menu de opções ========");
        System.out.println("[ 1 ] - Inserir Conta");
        System.out.println("[ 2 ] - Mostrar todas as contas");
        System.out.println("[ 3 ] - Remover Conta");
        System.out.println("[ 4 ] - Ler todas as contas");
        System.out.println("[ 5 ] - Finalizar programa e aplicar alterações");
        System.out.print(">");
    }

}
