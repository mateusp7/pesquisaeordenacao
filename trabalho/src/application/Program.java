package application;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


import entities.Conta;
import entities.ContaEspecial;
import estrutura.CadConta;

import java.util.ArrayList;

public class Program {
    static int[] vetorQuantidades = {500, 1000, 5000, 10000, 50000};
    static String[] vetorNomes = {"alea", "inv", "ord"};

    public static void main(String[] args) {
        try {
            lerContasDoArquivoEInserirNoArray();
            System.out.println("\n======== Processos realizados ========\n");
        }catch (Exception e) {
            System.out.println("\n====== Não foi possível realizar os procedimentos ======\n");
        }
    }

    public static void lerContasDoArquivoEInserirNoArray() throws IOException {
        Path path;
        CadConta listaCad;
        long start, end;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                // Pegando o path
                path = Paths.get("../cliente" + vetorQuantidades[i] + vetorNomes[j] + ".txt");
                // Criando um arraylist
                listaCad = new CadConta(vetorQuantidades[i]);
                // Limpando o arraylist
                listaCad.getArrayContas().clear();
                // Carregando o arrayList com os dados do .txt equivalente
                carregarVetor(listaCad, path);

                start = System.currentTimeMillis(); // get time
                listaCad.shellsort(listaCad.getArrayContas());
                end = System.currentTimeMillis();
                System.out.println("Tempo para execução do arquivo" + vetorQuantidades[i] + vetorNomes[j] + ": " + (end - start) + "ms");

                gravarContasESalvarAlteracoes(listaCad);
            }
        }
    }

    public static void carregarVetor(CadConta lista, Path path) throws IOException {
        List<String> linhas = Files.readAllLines(path, StandardCharsets.UTF_8);
        int i = 0;
        String linha;
        String[] valorComSplit;
        while(i < (linhas.size())){
            linha = linhas.get(i);
            valorComSplit = linha.split(";");
            if (valorComSplit.length == 5) {
                lista.inserirVetor(new ContaEspecial(Integer.parseInt(valorComSplit[0]), (valorComSplit[1]),
                        (valorComSplit[2]), Double.parseDouble(valorComSplit[3]), Double.parseDouble(valorComSplit[4])));
            } else if (valorComSplit.length == 4){
                lista.inserirVetor(new Conta(Integer.parseInt(valorComSplit[0]), (valorComSplit[1]),
                        (valorComSplit[2]), Double.parseDouble(valorComSplit[3])));
            }
            i++;
        }
    }

    public static void gravarContasESalvarAlteracoes(CadConta cadContaLista) throws IOException {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                FileWriter escrever = new FileWriter( "../arquivosOrdenados/cliente" + vetorQuantidades[i] +
                        vetorNomes[j] + "shellSort" + ".txt", StandardCharsets.UTF_8);
                try {
                    for (Conta c : cadContaLista.getArrayContas()) {
                        if (!(c instanceof ContaEspecial)) {
                            escrever.write(c + "\n");
                        }
                        if (c instanceof ContaEspecial) {
                            ContaEspecial aux = (ContaEspecial) c;
                            escrever.write(c + ";" + aux.saldo() +"\n");
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("\n======== Não foi possível gravar as contas ========\n");
                }
                escrever.close();
            }
        }
    }

}
