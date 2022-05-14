
package Model;

/**
 *
 * @author Carla
 */
public class Faturacao {
     private int id;
    private int valor;
    private int idCliente;

    static int totalID = 0;

    public Faturacao(int valor, int idCliente) {
        this.id = ++totalID;
        this.valor = valor;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
