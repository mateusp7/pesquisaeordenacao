package entities;

import interfaces.Transacao;

public class Conta implements Transacao {
    private String nome;
    private String cpf;
    private int numeroDaConta;
    private double valorNaConta;

    public Conta(String nome, String cpf, int numeroDaConta) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroDaConta = numeroDaConta;
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
        return "Nome: " + this.nome + "\n" + "Cpf: " + this.cpf + "\n" + "Número da conta: " + this.numeroDaConta + "\n";
    }
}
