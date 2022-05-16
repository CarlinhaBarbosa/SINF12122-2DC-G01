package FicheirosCSV;

import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GravarFicheiro {

    public static void gravarFicheiro(String nome, String data, String dataAquisicao, String importancia, String peso, String valorPeca, String idColecao, String imagem, String filePath) {

        try {
            FileWriter outputfile = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(outputfile);

            PrintWriter pw = new PrintWriter(bw);

            pw.println(nome + "," + importancia + "," + dataAquisicao + "," + data + "," + imagem + "," + peso + "," + valorPeca + "," + idColecao);

            pw.flush();
            pw.close();
            JOptionPane.showMessageDialog(null, "Guardado");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void gravarPeca() {
        String nome = JOptionPane.showInputDialog(null, "Qual o nome da peca?");
        String data = JOptionPane.showInputDialog(null, "Qual a data da peca?  'aaaa-mm-dd'");
        String dataAquisicao = JOptionPane.showInputDialog(null, "Qual a data de aquisicao da peca?    'aaaa-mm-dd'");
        String importancia = JOptionPane.showInputDialog(null, "Qual a importancia da peca?");
        String peso = JOptionPane.showInputDialog(null, "Qual o peso da peca?");
        String valorPeca = JOptionPane.showInputDialog(null, "Qual o valor da peca?");
        String nomeColecao = JOptionPane.showInputDialog(null, "Qual o nome da colecao da peca?");
        String imagem = JOptionPane.showInputDialog(null, "Qual a localizacao da imagem da peca?");
        String filePath = JOptionPane.showInputDialog(null, "Qual o nome do ficheiro?");

        gravarFicheiro(nome, data, dataAquisicao, importancia, peso, valorPeca, nomeColecao, imagem, filePath);
    }
}
