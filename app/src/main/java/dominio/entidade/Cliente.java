package dominio.entidade;

public final class Cliente extends Pessoa {
    protected int idcli;
    protected String senha;
    protected Empresa empresa;


    public int getIdcli() {
        return idcli;
    }

    public void setIdcli(int idcli) {
        this.idcli = idcli;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}

