package dados;

import entities.Conta;
import entities.ContaEspecial;
import estrutura.Arvore;
import estrutura.CadConta;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileOperations {
    private Path path;
    private Files file;
    
    public FileOperations(String url) {
        this.path = Paths.get(url);
    }
    
    public void carregarArvore(Arvore arvoreList) {
        try {
            List<String> linhas = Files.readAllLines(this.path, StandardCharsets.UTF_8);
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
        }catch(FileNotFoundException e) {
            System.out.println("Arquivo não encontrado" + e);
        }catch (IOException e){
            System.out.println("Algum problema");
        }
    }
}
