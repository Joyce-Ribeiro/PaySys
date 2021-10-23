package dominio.entidade;

public class Pagamento{
    protected String data;
    protected int idpag;
    public Cliente cliente;
    public Empresa empresa;
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
}
