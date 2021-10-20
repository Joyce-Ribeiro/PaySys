package dominio.entidade;

public abstract class Pessoa {
    protected String nome;
    protected String numero;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String nome) {
        this.numero = numero;
    }
}
