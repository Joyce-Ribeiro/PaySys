package dominio.entidade;

public class Pagamento{
    protected String data;
    public Cliente cliente;
    protected float valor;
    protected boolean status;
    protected int frequencia;

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


        }
