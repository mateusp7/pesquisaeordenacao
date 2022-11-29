package application;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import entities.Conta;
import entities.ContaEspecial;
import estrutura.Arvore;
import estrutura.Avl;
import estrutura.CadConta;
import estrutura.HashingEncadeado;

public class Program {
    static int[] vetorQuantidades = {500, 1000, 5000, 10000, 50000};
    static String[] vetorNomes = {"alea", "inv", "ord"};
    static Path pathToFiles = Paths.get("../arquivosOrdenados/");
    static Path pathNomes = Paths.get("../nome.txt");
    public static void main(String[] args) {
        try {
            System.out.println("----------------- Procedimentos ABB -------------------");
            realizarProcedimentosAbb();
            /*System.out.println("----------------- Procedimentos AVL -------------------");
            realizarProcedimentosAvl();
            System.out.println("----------------- Procedimentos Hashing Encadeado -------------------");
            realizarProcedimentosHashingEncadeado();*/
            System.out.println("Processos finalizados");
        } catch (IndexOutOfBoundsException | IOException e) {
            System.out.println("\n====== O limite do vetor foi ultrapassado ======\n");
        } catch (StackOverflowError e) {
            System.out.println("Ocorreu um estouro de pilha e não foi possível concluir os procedimentos");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // ------------------------------ Procedimentos para shellsort e quicksort -------------------------------
        /*try {
            lerContasDoArquivoEInserirNoArray();
            System.out.println("\n======== Processos usando shellsort foram realizados com sucesso ========\n");
        }catch (IndexOutOfBoundsException | IOException e) {
            System.out.println("\n====== Não foi possível realizar os procedimentos ======\n");
        }*/
    }

    public static void realizarProcedimentosAbb() throws Exception {
        Path path;
        Arvore arvoreList;
        int i, j;
        long start, end;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 3; j++) {
                try {
                    path = Paths.get("../cliente" + vetorQuantidades[i] + vetorNomes[j] + ".txt");
                    ArrayList<ArrayList<Conta>> array = new ArrayList<>();
                    arvoreList = new Arvore();
                    start = System.currentTimeMillis();
                    carregarArvoreABB(arvoreList, path);
                    arvoreList.CamCentral(array);
                    arvoreList.ArvoreBalanceada(array);
                    pesquisarNaAbbEDevolverParaOArquivo(arvoreList, i, j);
                    end = System.currentTimeMillis();
                    System.out.println("ABB - Tempo para execução do arquivo" + vetorQuantidades[i] + vetorNomes[j] + " " + (end - start) + "ms");
                } catch (FileNotFoundException e) {
                    System.out.println("Arquivo cliente" + vetorQuantidades[i] + vetorNomes[j] + ".txt não foi encontrado");
                }
            }
        }
    }

    public static void realizarProcedimentosAvl() throws Exception {
        Path path;
        Avl avlList;
        int i, j;
        long start, end;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 3; j++) {
                try {
                    path = Paths.get("../cliente" + vetorQuantidades[i] + vetorNomes[j] + ".txt");
                    avlList = new Avl();
                    start = System.currentTimeMillis();
                    carregarArvoreAVL(avlList, path);
                    pesquisarNaAvlEDevolverParaOArquivo(avlList, i, j);
                    end = System.currentTimeMillis();
                    System.out.println("AVL - Tempo para execução do arquivo" + vetorQuantidades[i] + vetorNomes[j] + " " + (end - start) + "ms");
                }
                catch (FileNotFoundException e) {
                    System.out.println("Arquivo cliente" + vetorQuantidades[i] + vetorNomes[j] + ".txt não foi encontrado");
                }
            }
        }
    }

    public static void realizarProcedimentosHashingEncadeado() throws Exception {
        Path path;
        HashingEncadeado hashList;
        int i, j;
        long start, end;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 3; j++) {
                try {
                    path = Paths.get("../cliente" + vetorQuantidades[i] + vetorNomes[j] + ".txt");
                    hashList = new HashingEncadeado(vetorQuantidades[i]);
                    start = System.currentTimeMillis();
                    carregarHashing(hashList, path);
                    pesquisarNoHashingEDevolverParaOArquivo(hashList, i, j);
                    end = System.currentTimeMillis();
                    System.out.println("Hashing - Tempo para execução do arquivo" + vetorQuantidades[i]
                            + vetorNomes[j] + " " + (end - start) + "ms");
                } catch (FileNotFoundException e) {
                    System.out.println("Arquivo cliente" + vetorQuantidades[i] + vetorNomes[j] + ".txt não foi encontrado");
                }
            }
        }
    }


    public static void pesquisarNaAbbEDevolverParaOArquivo(Arvore arvore, int i, int j) throws IOException {
        List<String> linhas = Files.readAllLines(pathNomes, StandardCharsets.UTF_8);
        FileWriter escrever;
        try {
            escrever = new FileWriter("../pesquisasAbbAvlHashing/clienteABB" + vetorQuantidades[i] +
                    vetorNomes[j] + ".txt", StandardCharsets.UTF_8);
            int k = 0;
            String linha;
            while (k < (linhas.size())) {
                linha = linhas.get(k);
                ArrayList<Conta> dado = arvore.pesquisa(linha);
                if (dado != null) {
                    for (Conta conta : dado) {
                        escrever.write("\nNome: " + conta.getNome() + " - ");
                        escrever.write("Conta: " + conta.getNumeroDaConta() + " - " + " Saldo: " + conta.getValorNaConta() + "\n");
                    }
                } else {
                    escrever.write("\n" + linha + " não existe na árvore\n");
                }
                k++;
            }
            escrever.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }
    }

    public static void pesquisarNaAvlEDevolverParaOArquivo(Avl arvore, int i, int j) throws IOException {
        List<String> linhas = Files.readAllLines(pathNomes, StandardCharsets.UTF_8);
        FileWriter escrever;
        try {
            escrever = new FileWriter("../pesquisasAbbAvlHashing/clienteAVL" + vetorQuantidades[i] +
                    vetorNomes[j] + ".txt", StandardCharsets.UTF_8);
            int k = 0;
            String linha;
            while (k < (linhas.size())) {
                linha = linhas.get(k);
                ArrayList<Conta> dado = arvore.pesquisa(linha);
                if (dado != null) {
                    for (Conta conta : dado) {
                        escrever.write("\nNome: " + conta.getNome() + " - ");
                        escrever.write("Conta: " + conta.getNumeroDaConta() + " - " + " Saldo: " + conta.getValorNaConta() + "\n");
                    }
                } else {
                    escrever.write("\n" + linha + " não existe na árvore\n");
                }
                k++;
            }
            escrever.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }
    }
    public static void pesquisarNoHashingEDevolverParaOArquivo(HashingEncadeado hashList, int i, int j) throws IOException {
        List<String> linhas = Files.readAllLines(pathNomes, StandardCharsets.UTF_8);
        FileWriter escrever;
        try {
            escrever = new FileWriter("../pesquisasAbbAvlHashing/clienteHashingEncadeado" + vetorQuantidades[i] +
                    vetorNomes[j] + ".txt", StandardCharsets.UTF_8);
            int k = 0;
            String linha;
            while (k < (linhas.size())) {
                linha = linhas.get(k);
                ArrayList<Conta> dado = hashList.pesquisa(linha);
                if (dado.size() != 0) {
                    for (Conta conta : dado) {
                        escrever.write("\nNome: " + conta.getNome() + " - ");
                        escrever.write("Conta: " + conta.getNumeroDaConta() + " - " + " Saldo: " + conta.getValorNaConta() + "\n");
                    }
                }
                else {
                    escrever.write("\n" + linha + " não existe na árvore\n");
                }
                k++;
            }
            escrever.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }
    }

    public static void carregarArvoreABB(Arvore arvoreList, Path path) throws IOException {
        List<String> linhas = Files.readAllLines(path, StandardCharsets.UTF_8);
        int i = 0;
        String linha;
        String[] valorComSplit;
        while(i < (linhas.size())){
            try {
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
            } catch (NumberFormatException e) {
                System.out.println("Conversão incorreta");
            } catch (NullPointerException e) {
                System.out.println("Linha não possui dados");
            }
        }
    }

    public static void carregarArvoreAVL(Avl arvoreList, Path path) throws IOException {
        List<String> linhas = Files.readAllLines(path, StandardCharsets.UTF_8);
        int i = 0;
        String linha;
        String[] valorComSplit;
        while(i < (linhas.size())){
            try {
                linha = linhas.get(i);
                valorComSplit = linha.split(";");
                if (valorComSplit.length == 5) {
                    arvoreList.insereRaiz(new ContaEspecial(Integer.parseInt(valorComSplit[0]), (valorComSplit[1]),
                            (valorComSplit[2]), Double.parseDouble(valorComSplit[3]), Double.parseDouble(valorComSplit[4])));
                } else if (valorComSplit.length == 4){
                    arvoreList.insereRaiz(new Conta(Integer.parseInt(valorComSplit[0]), (valorComSplit[1]),
                            (valorComSplit[2]), Double.parseDouble(valorComSplit[3])));
                }
                i++;
            } catch (NumberFormatException e) {
                System.out.println("Conversão incorreta");
            } catch (NullPointerException e) {
                System.out.println("Linha não possui dados");
            }
        }
    }

    public static void carregarHashing(HashingEncadeado hashList, Path path) throws Exception {
        List<String> linhas = Files.readAllLines(path, StandardCharsets.UTF_8);
        int i = 0;
        String linha;
        String[] valorComSplit;
        while(i < (linhas.size())){
            try {
                linha = linhas.get(i);
                valorComSplit = linha.split(";");
                if (valorComSplit.length == 5) {
                    hashList.inserirInHashing(new ContaEspecial(Integer.parseInt(valorComSplit[0]), (valorComSplit[1]),
                            (valorComSplit[2]), Double.parseDouble(valorComSplit[3]), Double.parseDouble(valorComSplit[4])));
                } else if (valorComSplit.length == 4){
                    hashList.inserirInHashing(new Conta(Integer.parseInt(valorComSplit[0]), (valorComSplit[1]),
                            (valorComSplit[2]), Double.parseDouble(valorComSplit[3])));
                }
                i++;
            } catch (NumberFormatException e) {
                System.out.println("Conversão incorreta");
            } catch (NullPointerException e) {
                System.out.println("Linha não possui dados");
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

    public static void lerContasDoArquivoEInserirNoArray() throws IOException {
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
    }
}
