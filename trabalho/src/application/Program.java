package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;

import dados.FileOperations;
import entities.Conta;
import entities.ContaEspecial;
import estrutura.Arvore;
import estrutura.CadConta;

public class Program {
    static int[] vetorQuantidades = {500, 1000, 5000, 10000, 50000};
    static String[] vetorNomes = {"alea", "inv", "ord"};
    static Path pathToFiles = Paths.get("../arquivosOrdenados/");
    static Path pathNomes = Paths.get("../nome.txt");
    public static void main(String[] args) {
        try {
            lerArquivosEInserirNaArvore();
            System.out.println("Processos finalizados");
        } catch (IndexOutOfBoundsException | IOException e) {
            System.out.println("\n====== Não foi possível realizar os procedimentos ======\n");
        }
        /*try {
            lerContasDoArquivoEInserirNoArray();
            System.out.println("\n======== Processos usando shellsort foram realizados com sucesso ========\n");
        }catch (IndexOutOfBoundsException | IOException e) {
            System.out.println("\n====== Não foi possível realizar os procedimentos ======\n");
        }*/
    }

    public static void lerArquivosEInserirNaArvore() throws IOException {
        Path path;
        Arvore arvoreList;
        int i, j;
        long start, end;
        CadConta cadConta = new CadConta(500);
        path = Paths.get("../cliente500ord.txt");
        arvoreList = new Arvore();
        start = System.currentTimeMillis();

        carregarArvore(arvoreList, path);
        System.out.println(arvoreList.CamCentral(cadConta));
        //pesquisarNaArvoreEDevolverParaOArquivo(arvoreList);
        //pesquisarNaArvoreEDevolverParaOArquivo(arvoreList);
        /*arvoreList.CamCentral(cadConta);
        arvoreList.pesquisa("SANDRA MAIA MADEIRA VARGAS");
        arvoreList.ArvoreBalanceada(cadConta);*/

        end = System.currentTimeMillis();
        System.out.println("Tempo para execução do arquivo " + (end - start) + "ms");
    }
    public static void carregarArvore(Arvore arvoreList, Path path) throws IOException {
        List<String> linhas = Files.readAllLines(path, StandardCharsets.UTF_8);
        int i = 0;
        String linha;
        String[] valorComSplit;
        while(i < (linhas.size())){
            linha = linhas.get(i);
            valorComSplit = linha.split(";");
            if (valorComSplit.length == 5) {
                arvoreList.insere(new ContaEspecial(Integer.parseInt(valorComSplit[0]), (valorComSplit[1]),
                        (valorComSplit[2]), Double.parseDouble(valorComSplit[3]), Double.parseDouble(valorComSplit[4])));
            } else if (valorComSplit.length == 4){
                arvoreList.insere(new Conta(Integer.parseInt(valorComSplit[0]), (valorComSplit[1]),
                        (valorComSplit[2]), Double.parseDouble(valorComSplit[3])));
            }
            i++;
        }
    }

    public static void pesquisarNaArvoreEDevolverParaOArquivo(Arvore arvore) throws IOException {
        List<String> linhas = Files.readAllLines(pathNomes, StandardCharsets.UTF_8);
        FileWriter escrever;
        try {
            escrever = new FileWriter("../arquivosNomesABB/clienteABB500alea.txt", StandardCharsets.UTF_8);
            int i = 0;
            String linha;
            while (i < (linhas.size())) {
                linha = linhas.get(i);
                if (arvore.pesquisa(linha) != null) {
                    escrever.write("\nNome: " + arvore.pesquisa(linha).get(0).getNome() + " - ");
                    escrever.write("Conta: " + arvore.pesquisa(linha).get(0).getNumeroDaConta() +"\n");
                } else {
                    escrever.write("\n" + linha + " não existe na árvore\n");
                }
                i++;
            }
            escrever.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
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

    /*public static void lerContasDoArquivoEInserirNoArray() throws IOException {
        Path path;
        CadConta listaCad;
        int i, j;
        long start, end;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 3; j++) {
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
                System.out.println("Tempo para execução do arquivo" + vetorQuantidades[i] + vetorNomes[j] + " " + (end - start) + "ms");

                gravarContasESalvarAlteracoes(listaCad, i, j);
            }
        }
    }


    public static void gravarContasESalvarAlteracoes(CadConta cadContaLista, int i, int j) throws IOException {
        if (!(Files.exists(pathToFiles))) {
            Files.createDirectories(Paths.get("../arquivosOrdenados/"));
        } else {
            FileWriter escrever = new FileWriter("../arquivosOrdenados/cliente" + vetorQuantidades[i] +
                    vetorNomes[j] + "shellsort" + ".txt", StandardCharsets.UTF_8);
            try {
                for (Conta c : cadContaLista.getArrayContas()) {
                    if (!(c instanceof ContaEspecial)) {
                        escrever.write(c + "\n");
                    }
                    if (c instanceof ContaEspecial) {
                        ContaEspecial aux = (ContaEspecial) c;
                        escrever.write(c + ";" + aux.getLimite() + "\n");
                    }
                }
            } catch (Exception e) {
                System.out.println("\n======== Não foi possível gravar as contas ========\n");
            }
            escrever.close();
        }
    }*/

}
