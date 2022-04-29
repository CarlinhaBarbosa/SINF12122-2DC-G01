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


    private static final String String_POR_OMISSAO = "SemString";

    private static final String Int_POR_OMISSAO = "SemInT";
    private static final int ID_POR_OMISSAO = -1;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
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

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
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
    }

    public Utilizador(int id, String email, String nome, String password, String username, String telemovel, String nif) {
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

        this.telemovel = telemovel;
        this.nif = nif;
        
    }

}

