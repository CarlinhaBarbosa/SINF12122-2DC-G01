/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import Controller.UserController;
import Model.Utilizador;
import Model.Viatura;
import Model.Plano;
import Model.Parque;
import io.vertx.ext.web.RoutingContext;

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

    public Utilizador getUser(String Username, String Password) {
        ResultSet rs = null;
        Utilizador o = new Utilizador();
        try {

            String query0 = "SELECT * from Users where Username= '" + Username + "' AND Password='" + Password + "'";
            rs = this.returnQuery(query0);

            while (rs.next()) {

                o = new Utilizador(rs.getInt("Id"), rs.getString("Email"), rs.getString("Name"), rs.getString("Password"), rs.getString("Username"), rs.getString("Phone"), rs.getString("taxID"));
                if (Username.equalsIgnoreCase("admin") && Password.equalsIgnoreCase("admin")) {
                    o.setCargo("admin");
                } else {
                    o.setCargo("cliente");
                }
            }
            UserController.id_utilizadorObtido = o.getId();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("cargo" + o.getCargo());
        return o;
    }

    public void introduzirUtilizador(String email, String nome, String password, String username, String phone, String nif) {
        Integer phoneNumber = Integer.parseInt(phone);
        Integer taxId = Integer.parseInt(nif);

        String query = "INSERT INTO Users (Name,Email,Password,Username,Phone,taxID)  VALUES ('" + nome + "','" + email + "','" + password + "','" + username + "'," + phoneNumber + "," + taxId + ")";
        System.out.println(query);
        executaQuery(query);

    }

    public void registarVeiculo(String marca, String matricula, String modelo, String userId) {
        Integer userId2 = Integer.parseInt(userId);

        String query = "INSERT INTO Vehicles (Brand,Model,Registration,UserId)  VALUES ('" + marca + "','" + modelo + "','" + matricula + "'," + userId2 + ")";
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
                Utilizador ut = new Utilizador(rs.getInt("Id"), rs.getString("Email"), rs.getString("Name"), rs.getString("Password"), rs.getString("Username"), rs.getString("Phone"), rs.getString("taxID"));
                System.out.println(ut.toString());
                listaUtilizadores.add(ut);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        System.out.println(listaUtilizadores.size());
        return listaUtilizadores;
    }

    public ArrayList<Viatura> getListaVeiculos() {
        ArrayList<Viatura> listaViaturas = new ArrayList<>();
        ResultSet rs = null;
        try {
            String querry = "SELECT * FROM Vehicles";
            rs = this.returnQuery(querry);

            while (rs.next()) {

                Viatura ut = new Viatura(rs.getInt("VehiclesId"), rs.getString("Brand"), rs.getString("Model"), rs.getInt("UserId"), rs.getString("Registration"));
                System.out.println(ut.toString());
                listaViaturas.add(ut);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        System.out.println(listaViaturas.size());
        return listaViaturas;
    }

    public int getUtilizador(RoutingContext rc, String username, String password) {
        int u = 0;

        ResultSet rs = null;
        try {
            String querry = "SELECT Id FROM Users where Username='" + username + "'and Password='" + password + "'";

            rs = this.returnQuery(querry);

            while (rs.next()) {
                u = rs.getInt("Id");

            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return u;
    }

    public void introduzirViatura(String modelo, String marca, int id, String matricula) {

        String query = "INSERT INTO Vehicles (Brand, Model, UserId, Registration)  VALUES ('" + modelo + "','" + marca + "'," + id + ",'" + matricula + "')";
        System.out.println(query);
        executaQuery(query);

    }

    public ArrayList<Utilizador> listaUsers() {
        ArrayList<Utilizador> listaClientes = new ArrayList<>();
        ResultSet rs = null;
        try {

            String querry = "select * from Users where Name<>'admin';";
            rs = this.returnQuery(querry);

            while (rs.next()) {
                Utilizador e = new Utilizador(rs.getInt("Id"), rs.getString("Email"), rs.getString("Name"), rs.getString("Password"), rs.getString("Username"), rs.getString("Phone"), rs.getString("taxID"), rs.getString("Seat"), rs.getString("PlanNAME"));
                System.out.println(e.toString());
                ResultSet rs2 = null;
                try {

                    String querry2 = "select Registration from Vehicles where UserId=" + e.getId();
                    rs2 = this.returnQuery(querry2);

                    while (rs2.next()) {
                        e.setMatricula(rs2.getString("Registration"));

                    }
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
                listaClientes.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return listaClientes;
    }

    public Utilizador ContaUtilizador(RoutingContext rc, int id) {
        Utilizador e = null;
        ResultSet rs = null;
        try {
            String querry = "SELECT * FROM pecas WHERE id_peca = " + id + "";

            rs = this.returnQuery(querry);

            while (rs.next()) {

                 e = new Utilizador(rs.getInt("Id"), rs.getString("Email"), rs.getString("Name"), rs.getString("Password"), rs.getString("Username"), rs.getString("Phone"), rs.getString("taxID"), rs.getString("Seat"), rs.getString("PlanNAME"));
                System.out.println(e.toString());
                ResultSet rs2 = null;
                try {

                    String querry2 = "select Registration from Vehicles where UserId=" + id;
                    rs2 = this.returnQuery(querry2);System.out.println(querry2);
                   
                    String querry3 = "select Model from Vehicles where UserId=" +id;
                    rs2 = this.returnQuery(querry3);
                    System.out.println(querry3);
                  
                    String querry4 = "select Brand from Vehicles where UserId=" + id;
                    rs2 = this.returnQuery(querry4);System.out.println(querry4);
                     
                    while (rs2.next()) {
                        e.setMatricula(rs2.getString("Registration"));
                        e.setModelo(rs2.getString("Model"));
                        e.setMarca(rs2.getString("Brand"));

                    }

                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return e;
    }
    

}

