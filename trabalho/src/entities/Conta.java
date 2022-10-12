package entities;

import interfaces.Transacao;

public class Conta implements Transacao {
    private String nome;
    private String cpf;
    private int numeroDaConta;
    private double valorNaConta;

    public Conta(int numeroDaConta, String cpf, String nome) {
        this.numeroDaConta = numeroDaConta;
        this.cpf = cpf;
        this.nome = nome;
        this.valorNaConta = 10;
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    private void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    private void setNumeroDaConta(int numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public double getValorNaConta() {
        return this.valorNaConta;
    }

    public void deposito(double valor) {
        this.valorNaConta += valor;
    }

    public boolean saque(double valor) {
        if(this.valorNaConta < valor) {
            return false;
        } else {
            this.valorNaConta -= valor;
            return true;
        }
    }

    public boolean transferir(double valor, Conta contaRecebeTransferencia) {
        if (this.valorNaConta < valor) {
            return false;
        } else {
            this.valorNaConta -= valor;
            contaRecebeTransferencia.valorNaConta += valor;
            return true;
        }
    }

    @Override
    public double saldo() {
        return this.getValorNaConta();
    }

    @Override
    public String toString() {
        return this.numeroDaConta + " - " + this.cpf + " - " + this.nome + " - " + this.saldo();
    }
}
