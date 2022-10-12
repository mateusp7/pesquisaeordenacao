package application;

import entities.Conta;
import entities.ContaEspecial;

import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    /*static final Scanner entrada = new Scanner(System.in);
    static ArrayList<Conta> lista = new ArrayList<>();
    static Conta conta;
    static ContaEspecial contaEspecial;
    public static void main(String[] args) {
        int num;
        do {
            mostrarMenu();
            num = entrada.nextInt();
            switch (num) {
                case 1 -> cadastrarConta();
                case 2 -> System.out.println(verificarSaldoDaConta());
                case 3 -> realizarSaque();
                case 4 -> realizarDeposito();
                case 5 -> realizarTransferencia();
                case 6 -> System.out.println("Programa finalizado");
                default -> System.out.println("Opção inválida");
            }
        }while (num != 6);
    }*/

    /*public static void mostrarMenu() {
        System.out.println("==========================");
        System.out.println("Menu de opções\n");
        System.out.println("[ 1 ] - Cadastrar conta");
        System.out.println("[ 2 ] - Saldo da conta");
        System.out.println("[ 3 ] - Sacar");
        System.out.println("[ 4 ] - Depositar");
        System.out.println("[ 5 ] - Transferir");
        System.out.println("[ 6 ] - Sair do sistema");
        System.out.print("Escolha sua opção: ");
    }

    public static void cadastrarConta() {
        System.out.println("Qual será o tipo da conta?");
        System.out.println(" [ 1 ] - Conta normal");
        System.out.println(" [ 2 ] - Conta especial");
        System.out.print(">");
        int opcao = entrada.nextInt();
        switch (opcao) {
            case 1 -> {
                entrada.nextLine();
                System.out.print("Nome: ");
                String nome = entrada.nextLine();

                System.out.print("CPF: ");
                String cpf = entrada.nextLine();

                System.out.print("Número da conta: ");
                int numeroDaConta = entrada.nextInt();
                while (pesquisarNumeroDaConta(numeroDaConta, lista) != -1) {
                    System.out.print("Número da conta já existe, digite um novo número: ");
                    numeroDaConta = entrada.nextInt();
                }

                try {
                    conta = new Conta(nome, cpf, numeroDaConta);
                    lista.add(conta);
                    System.out.println("Conta inserida com sucesso");
                    System.out.println(lista.toString());
                } catch (Exception e) {
                    System.out.println("Não foi possível criar uma nova conta " + e);
                }

            }
            case 2 -> {
                entrada.nextLine();
                System.out.println("Nome: ");
                String nome = entrada.nextLine();

                System.out.println("CPF: ");
                String cpf = entrada.nextLine();

                System.out.print("Número da conta: ");
                int numeroDaConta = entrada.nextInt();
                while (pesquisarNumeroDaConta(numeroDaConta, lista) != -1) {
                    System.out.print("Número da conta já existe, digite um novo número: ");
                    numeroDaConta = entrada.nextInt();
                }

                System.out.print("Limite especial: R$");
                double limiteEspecial = entrada.nextDouble();

                try {
                    contaEspecial = new ContaEspecial(nome, cpf, numeroDaConta, limiteEspecial);
                    lista.add(contaEspecial);
                    System.out.println("Conta Especial inserida com sucesso");
                } catch (Exception e) {
                    System.out.println("Não foi possível criar uma nova conta especial " + e);
                }

            }
        }
    }

    public static String verificarSaldoDaConta() {
        System.out.print("Digite o número da conta: ");
        String result;
        int numeroConta = entrada.nextInt();
        int resultado = pesquisarNumeroDaConta(numeroConta, lista);
        if (resultado != -1) {
            result = "Saldo da conta é de: R$" + lista.get(resultado).saldo();
        } else {
            result = "------> Conta não encontrada no sistema <------";
        }
        return result;
    }
    public static void realizarSaque() {
        System.out.print("Digite o número da conta: ");
        int numeroConta = entrada.nextInt();
        int resultado = pesquisarNumeroDaConta(numeroConta, lista);
        if (resultado != -1) {
            System.out.print("Digite ao lado o valor a ser sacado: R$");
            double valorSaque = entrada.nextDouble();
            boolean resultadoSaque = lista.get(resultado).saque(valorSaque);
            if (resultadoSaque) {
                System.out.println("\nSaque realizado com sucesso!\n");
            } else {
                System.out.println("\n------> Saldo insuficiente <------\n");
            }
        }
    }

    public static void realizarDeposito() {
        System.out.print("Digite o número da conta: ");
        int numeroConta = entrada.nextInt();
        int resultado = pesquisarNumeroDaConta(numeroConta, lista);
        if (resultado != -1) {
            System.out.print("Digite ao lado o valor a ser depositado: R$");
            double valorDeposito = entrada.nextDouble();
            lista.get(resultado).deposito(valorDeposito);
            System.out.println("\nDepósito realizado com sucesso!\n");
        } else {
            System.out.println("\n------> Conta inexistente <------\n");
        }
    }

    public static void realizarTransferencia() {
        System.out.print("Digite o número da conta que irá transferir: ");
        int numeroDaContaTransferir = entrada.nextInt();

        System.out.print("Digite o número da conta que irá receber o valor: ");
        int numeroDaContaRecebe = entrada.nextInt();

        // Verificar se as 2 contas existem
        boolean resultadoContas = verificarContasTransferir(numeroDaContaTransferir, numeroDaContaRecebe, lista);

        if (resultadoContas) {
            System.out.print("Digite a quantia a ser transferida: R$");
            double quantidadeTransferir = entrada.nextDouble();

            // Pegar o indice da conta 1
            int indiceContaTransferir = pesquisarNumeroDaConta(numeroDaContaTransferir, lista);
            // Pegar o indice da conta 2
            int indiceContaReceber = pesquisarNumeroDaConta(numeroDaContaRecebe, lista);

            // Realizar a transferencia
            boolean resultadoTransferencia = lista.get(indiceContaTransferir).transferir(quantidadeTransferir,
                    lista.get(indiceContaReceber));
            if (resultadoTransferencia) {
                System.out.println("\nTransferência realizada\n");
            } else {
                System.out.println("\n-------> Saldo insuficiente na conta que irá transferir <-------\n");
            }
        }
    }

    public static boolean verificarContasTransferir(int numeroConta1, int numeroConta2, ArrayList<Conta> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNumeroDaConta() == numeroConta2) {
                if(pesquisarNumeroDaConta(numeroConta1, lista) != -1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int pesquisarNumeroDaConta(int novoNumeroDaConta,
                                             ArrayList<Conta> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getNumeroDaConta() == novoNumeroDaConta) {
                return i;
            }
        }
        return -1;
    }*/
}
