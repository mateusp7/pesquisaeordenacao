package dados;

import entities.Conta;

import java.util.ArrayList;
import java.util.LinkedList;

public class NoArvore {
        private ArrayList<Conta> info;
        private NoArvore dir, esq;

        public NoArvore (Conta _info){
            info = new ArrayList<>();
            this.info.add(_info);
        }

        public NoArvore (ArrayList<Conta> _info){
            this.info = _info;
        }

        public NoArvore getDir() {
            return dir;
        }
        public void setDir(NoArvore dir) {
            this.dir = dir;
        }
        public NoArvore getEsq() {
            return esq;
        }
        public void setEsq(NoArvore esq) {
            this.esq = esq;
        }
        public ArrayList<Conta> getInfo() {
            return info;
        }

        public void insereIgual(Conta conta) {
            this.getInfo().add(conta);
        }

        public void insereIgualCamCentral(ArrayList<Conta> conta) {
            this.info = conta;
        }
}
