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
    public static Path path = Paths.get("contas.txt");
    static ArrayList<Conta> lista = new ArrayList<>();
    static CadConta listaContas = new CadConta(lista);
    public static void main(String[] args) throws IOException {
        if (!(path.toFile().exists())) {
            FileWriter out = new FileWriter("contas.txt");
            PrintWriter arquivo = new PrintWriter(out);
        }  else {
            try {

                System.out.println("O arquivo de alunos foi lido com sucesso");
            }catch (Exception e) {
                System.out.println("====== Não foi possível carregar o arquivo ======");
            }
        }

        ArrayList<Conta> contas = new ArrayList<>();
        CadConta cadConta = new CadConta();
        Conta conta1 = new Conta("Mateus", "18727714756", 1);
        Conta conta2 = new Conta("Julia", "21345687", 2);
        Conta conta3 = new ContaEspecial("José", "147852369", 5, 1000);
        cadConta.inserirVetor(conta1);
        cadConta.inserirVetor(conta2);
        cadConta.inserirVetor(conta3);

    }

    public static ArrayList<Conta> lerContas() throws IOException {
        List<String> linhas = Files.readAllLines(path, StandardCharsets.UTF_8);
        int i = 0;
        String linha;
        String[] valorComSplit;
        while(i < (linhas.size())){
            linha = linhas.get(i);
            valorComSplit = linha.split(" - ");
            listaContas.inserirVetor(new Conta(valorComSplit[0], (valorComSplit[1]),
                    Integer.parseInt(valorComSplit[2], Dob);
            i++;
        }
        return turma.alunos;
    }

    public static void gravarConta(CadConta cadContas) throws IOException {
        FileWriter escrever = new FileWriter(String.valueOf(path), StandardCharsets.UTF_8);

        for (int i = 0; i < cadContas.getTamanhoVetor(); i++) {
            escrever.write(cadContas.getConta(i).getNome() + " - " +
                    cadContas.getConta(i).getNumeroDaConta() + " - " +
                    cadContas.getConta(i).getValorNaConta() + " - " +
                    cadContas.getConta(i).getCpf());
        }
        escrever.close();
    }
}
