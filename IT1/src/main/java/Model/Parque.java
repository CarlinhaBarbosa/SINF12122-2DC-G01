
package Model;

/**
 *
 * @author Beatriz Neves
 */
public class Parque {
      private int id;
    private int numero;
    private String zona;
    private int id_piso;

    static int totalID = 0;

    public Parque(int numero, String id_zona, int id_piso) {
        this.id = ++totalID;
        this.numero = numero;
        this.zona = id_zona;
        this.id_piso = id_piso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getId_zona() {
        return zona;
    }

    public void setId_zona(String zona) {
        this.zona = zona;
    }

    public int getId_piso() {
        return id_piso;
    }

    public void setId_piso(int id_piso) {
        this.id_piso = id_piso;
    }

   
}
