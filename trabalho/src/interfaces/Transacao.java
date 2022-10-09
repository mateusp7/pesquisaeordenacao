package interfaces;

import entities.Conta;

public interface Transacao {
    void deposito(double valor);

    boolean saque(double valor);

    boolean transferir(double valor, Conta conta);

    double saldo();
}
