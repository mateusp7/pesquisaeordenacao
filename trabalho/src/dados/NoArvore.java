package dados;

import entities.Conta;

import java.util.LinkedList;

public class NoArvore {
        private LinkedList<Conta> info;
        private NoArvore dir, esq;

        public NoArvore (Conta _info){
            info = new LinkedList<Conta>();
            this.info.add(_info);
        }

        public NoArvore getDir() {
            return dir;
        }
        public void setDir(NoArvore dir) {
            this.dir = dir;
            this.dir.getInfo().add(dir.getInfo().get(0));
        }
        public NoArvore getEsq() {
            return esq;
        }
        public void setEsq(NoArvore esq) {
            this.esq = esq;
            this.esq.getInfo().add(esq.getInfo().get(0));
        }
        public LinkedList<Conta> getInfo() {
            return info;
        }

        public void insereIgual(Conta conta) {
            this.getInfo().add(conta);
        }
}
