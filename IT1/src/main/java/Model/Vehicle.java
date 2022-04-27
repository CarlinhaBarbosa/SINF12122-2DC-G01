package Model;

/**
 *
 * @author Carla
 */
public class Vehicle {

    private String brand;
    private String model;
    private String registration;
    private int userId;
    private int id;
    
    private static final String String_POR_OMISSAO = "SemString";

    private static final int Int_POR_OMISSAO = 0;
    private static final int ID_POR_OMISSAO = -1;


    public String getModelo() {
        return model;
    }

    public void setModelo(String modelo) {
        this.model = modelo;
    }

    public String getMarca() {
        return brand;
    }

    public void setMarca(String marca) {
        this.brand = marca;
    }

    public String getMatricula() {
        return registration;
    }

    public void setMatricula(String matricula) {
        this.registration = matricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle() {
        this.brand = String_POR_OMISSAO;
        this.model = String_POR_OMISSAO;
        this.registration = String_POR_OMISSAO;
        this.userId = Int_POR_OMISSAO;
    }

    public Vehicle(String brand, String model, String registration, String userID) {
        this.brand = brand;
        this.model = model;
        this.registration = registration;
        this.userId = userId;
    
    }
    public Vehicle(String brand, String model, String registration, String userID, String id) {
        this.brand = brand;
        this.model = model;
        this.registration = registration;
        this.userId = userId;
        this.id = Integer.parseInt(id);
    
    }
}
