package dominio.entidade;

public class Pagamento{
    protected String data;
    protected int idpag;
    public int id_cli ;
    public int id_emp;
    protected float valor;
    protected boolean status;
    protected int frequencia;
    protected String pdf;

    public String getData() {
        return data;
        }

    public void setData(String data) {
        this.data = data;
        }

    public float getValor() {
        return valor;
        }

    public void setValor(float valor) {
        this.valor = valor;
        }

    public boolean isStatus() {
        return status;
        }

    public void setStatus(boolean status) {
        this.status = status;
        }

    public int getFrequencia() {
        return frequencia;
        }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
        }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }
}
