/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import Controller.UserController;
import Model.Utilizador;

import static io.vertx.ext.web.handler.StaticHandler.DEFAULT_WEB_ROOT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carla
 */
public class Handlers {
    
 
    String webRoot = DEFAULT_WEB_ROOT;

    private void executaQuery(String query) {
        try {
            Statement stmt = DBFactory.getConnection().createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    private ResultSet returnQuery(String query) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Connection conn = DBFactory.getConnection();
            statement = conn.prepareStatement(query);
            rs = statement.executeQuery();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return rs;
    }

       public void idUtilizador(String Username, String Password) {
        ResultSet rs = null;
        int numero = 0;
        try {
           
            String query0 = "SELECT id from Users where Username= '" + Username + "' AND Password='" + Password + "'";
            rs = this.returnQuery(query0);

            while (rs.next()) {
                numero = rs.getInt("id");
            }
            UserController.id_utilizadorObtido = numero;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }  

    public void introduzirUtilizador(String email, String nome, String password, String username, String phone, String nif) {
        Integer phoneNumber = Integer.parseInt(phone);
        Integer taxId = Integer.parseInt(nif);

        String query = "INSERT INTO Users (Name,Email,Password,Username,Phone,taxID)  VALUES ('" + nome + "','" + email + "','" + password + "','" +username + "',"+ phoneNumber + "," + taxId +")";
        System.out.println(query);
        executaQuery(query);

    }

    public void registarVeiculo(String marca, String matricula, String modelo, String userId) {
        Integer userId2 = Integer.parseInt(userId);
        

        String query = "INSERT INTO Vehicles (Brand,Model,Registration,UserId)  VALUES ('" + marca + "','" + modelo + "','" + matricula + "'," +userId2  +")";
        System.out.println(query);
        executaQuery(query);

    }
     public ArrayList<Utilizador> getListaUtilizadores() {
        ArrayList<Utilizador> listaUtilizadores = new ArrayList<>();
        ResultSet rs = null;
        try {
            String querry = "SELECT * FROM Users";
            rs = this.returnQuery(querry);

            while (rs.next()) {
                System.out.println("utilizador");
                Utilizador ut = new Utilizador(rs.getInt("Id"), rs.getString("Email"), rs.getString("Name"), rs.getString("Password"),  rs.getString("Username"), rs.getString("Phone"),  rs.getString("taxID"));
                System.out.println(ut.toString());
                listaUtilizadores.add(ut);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
         System.out.println(listaUtilizadores.size());
        return listaUtilizadores;
    }
}
