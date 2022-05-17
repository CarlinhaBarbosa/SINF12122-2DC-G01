package Model;

/**
 *
 * @author Beatriz Neves
 */

public class Plano {
    private int preco;
    private String nome;

    public Plano(int preco, String nome) {
        this.preco = preco;
        this.nome = nome;
    }

    public int getPreco() {
        return this.preco;
    }

    public String getNome() {
        return nome;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
