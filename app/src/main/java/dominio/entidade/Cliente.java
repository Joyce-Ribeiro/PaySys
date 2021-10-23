package dominio.entidade;
import java.io.Serializable;
public final class Cliente extends Pessoa implements Serializable   {
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

