package Model;

/**
 *
 * @author Carla
 */
public class Viatura {

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Viatura() {
        this.brand = String_POR_OMISSAO;
        this.model = String_POR_OMISSAO;
        this.registration = String_POR_OMISSAO;
        this.userId = Int_POR_OMISSAO;
    }

    public Viatura(String brand, String model, int userID, String registration) {
        this.brand = brand;
        this.model = model;
        this.registration = registration;
        this.userId = userID;
    
    }
    public Viatura(int id,String brand, String model,int userID, String registration) {
        this.brand = brand;
        this.model = model;
        this.registration = registration;
        this.userId = userID;

    
    }
}
