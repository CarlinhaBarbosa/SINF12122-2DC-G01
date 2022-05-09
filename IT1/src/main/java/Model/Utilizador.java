package Model;

/**
 *
 * @author Carla
 */
public class Utilizador {

    private String username;
    private String nome;
    private String email;
    private String password;
    private int id;
    private String telemovel;
    private String nif;
    private String cargo;
    private String lugar;
    private String plano;
    private String matricula;
    private static final String String_POR_OMISSAO = "SemString";

    private static final String Int_POR_OMISSAO = "SemInT";
    private static final int ID_POR_OMISSAO = -1;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public String getLugar() {
        return lugar;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public static String getNOME_POR_OMISSAO() {
        return String_POR_OMISSAO;
    }

    public static String getEMAIL_POR_OMISSAO() {
        return String_POR_OMISSAO;
    }

    public static String getPASSWORD_POR_OMISSAO() {
        return Int_POR_OMISSAO;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Utilizador() {
        this.nome = String_POR_OMISSAO;
        this.email = String_POR_OMISSAO;
        this.password = Int_POR_OMISSAO;
        this.cargo = String_POR_OMISSAO;
    }

    public Utilizador(int id, String email, String nome, String password, String username, String telemovel, String nif) {
        this.cargo = String_POR_OMISSAO;
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.id = id;
        this.telemovel = telemovel;
        this.nif = nif;
        

    }

    public Utilizador(String username, String password) {
        this.username = username;

        this.password = password;

    }

    public Utilizador(String email, String nome, String password, String username, String telemovel, String nif) {
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.cargo = String_POR_OMISSAO;
        this.telemovel = telemovel;
        this.nif = nif;
      

    }
      public Utilizador(int id, String email, String nome, String password, String username, String telemovel, String nif, String lugar,String plano) {
        this.cargo = String_POR_OMISSAO;
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.id = id;
        this.telemovel = telemovel;
        this.nif = nif;
        this.lugar=lugar;
        this.plano=plano;

    }
}
