package bless.leandro.Vendas.exception;

public class SenhaInvalidaException extends RuntimeException{
    public SenhaInvalidaException(){
        super("Senha Inválida!");
    }
}
