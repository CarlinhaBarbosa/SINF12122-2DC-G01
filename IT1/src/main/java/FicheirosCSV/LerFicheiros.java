
package FicheirosCSV;

import Controller.UserController;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.exit;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class LerFicheiros {

    public static void lerFicheiros() {
        int valor = -1;
        Scanner ler = new Scanner(System.in);

        do {
            System.out.println("1. Ler lista users");
            
            System.out.println("2. Parar leitura");

            valor = ler.nextInt();

            switch (valor) {
                case 1:
//                    leitorUser();
                    break;
                
                default:
                    System.out.println("TENTE NOVAMENTE");
                    break;
            }
        } while (valor != 5);
    }


    public static void leitorUser(File s) {
        try {
           
            Scanner leitorUser = new Scanner(s);
            leitorUser.useDelimiter(";|,");

            UserController e = new UserController();
            try {
                while (leitorUser.hasNext()) {
                    String nome = leitorUser.next().trim();
                    String email = leitorUser.next().trim();
                    String password = leitorUser.next().trim();
                    String telemovel = leitorUser.next().trim();
                    
                    e.adicionarUtilizador(nome, email, password, telemovel);

                }

            } catch (NoSuchElementException ex) {
                System.out.println("Erro na leitura do ficheiro.");
                exit(0);
            }
            leitorUser.close();

        } catch (FileNotFoundException e) {
            System.out.println("O ficheiro indicado não existe. ");
            exit(0);
        }
    }

   
}
