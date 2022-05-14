/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.Utilizador;
import Model.Viatura;
import Model.Parque;
import Model.Plano;
import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import java.util.ArrayList;
import servidor.DAL;
import io.vertx.ext.web.RoutingContext;
import java.sql.SQLException;

/**
 *
 * @author Carla
 */
public class UserController {
    



    public static int id_utilizadorObtido = 0;
    private static Utilizador ut;
    boolean isAuthenticated = false;

    public static servidor.DAL cf = new servidor.DAL();

    public static void obterUserName(RoutingContext routingContext) {
        System.out.println("obterUtilizador() - " + routingContext.toString());
        String json = Json.encodePrettily(ut);
        System.out.println(ut.toString());
        routingContext.response().setStatusCode(200).putHeader("content-type", "application/json; charset=utf-8").end(json);

    }

    public void addUtilizador(RoutingContext routingContext, String email, String nome, String password, String username, String telemovel, String nif) {
        System.out.println("addUtilizador() - " + routingContext.toString());
        try {
            Utilizador u = new Utilizador(email, nome, password, username, telemovel, nif);
            cf.introduzirUtilizador(email, nome, password, username, telemovel, nif);
            final String json = Json.encodePrettily(u);
            routingContext.response().setStatusCode(201).putHeader("content-type", "application/json; charset=utf-8").end(json);

        } catch (EncodeException e) {
            System.out.println("exception: " + e.getMessage());
            routingContext.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodeToBuffer("{erro: 'erro!'}"));
        }
    }

    public void login(RoutingContext rc) {
        String userName = rc.request().getParam("userlogin");
        String password = rc.request().getParam("passwordlogin");
        System.out.println(userName + " - " + password);
        
        HttpServerResponse response = rc.response();
       
        ut = cf.getUser(userName, password);;
        String json = Json.encodePrettily(ut);
        if (UserController.id_utilizadorObtido != 0) {

            isAuthenticated = true;
            response.setStatusCode(200);

            json = Json.encodePrettily(ut);
        } else {

            response.setStatusCode(400);
        }
        response.putHeader("content-type", "application/json; charset=utf-8");
        response.end(json.toString());
    }

    public ArrayList<Utilizador> listarUtilizadores() {
        return cf.getListaUtilizadores();
    }

    public ArrayList<Viatura> listarVeiculos() {
         return cf.getListaVeiculos();
    }

    public int getUtilizador(RoutingContext rc, String username, String password) {
        return cf.getUtilizador(rc,username,password);
    }

    public void addVeiculo(RoutingContext rc, String modelo, String marca, int id, String matricula) {
       System.out.println("addUVeiculo() - " + rc.toString());
        try {
            Viatura u = new Viatura(modelo,marca,id,matricula);
            cf.introduzirViatura(modelo,marca,id,matricula);
            final String json = Json.encodePrettily(u);
            rc.response().setStatusCode(201).putHeader("content-type", "application/json; charset=utf-8").end(json);

        } catch (EncodeException e) {
            System.out.println("exception: " + e.getMessage());
            rc.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodeToBuffer("{erro: 'erro!'}"));
        }
    }

    public ArrayList<Utilizador> listaUtilizadores() {
        return cf.listaUsers();
    }
    public Utilizador Utilizador(RoutingContext rc, int id) {
        return cf.ContaUtilizador(rc,id);
    }
    public void adicionarUser(int id,String nome, String username, String email, String nif, String password, String matricula, String modelo, String plano, String lugar, String marca, String phone) {
        
        cf.atualizarUser(id, nome,  username,  email,  nif,  password,  matricula,  modelo,  plano,  lugar,  marca,  phone) ;
        
    }
     public void AlterarCliente(RoutingContext e) {
        System.out.println("ola");
       
        try {
        int id = Integer.parseInt(e.request().getParam("numero"));

        System.out.println("Id --------------->" + id);
        String nome = e.request().getParam("nome");
        String username = e.request().getParam("username");
        String email = e.request().getParam("email");
        String nif = e.request().getParam("nif");
        String password = e.request().getParam("password");
        String matricula = e.request().getParam("matricula");
        String modelo = e.request().getParam("modelo");
        String plano = e.request().getParam("plano");
        String lugar = e.request().getParam("lugar");
        String marca = e.request().getParam("marca");
        String phone = e.request().getParam("phone");

        cf.atualizarUser(id, nome, username, email, nif, password, matricula, modelo, plano, lugar, marca, phone);
        System.out.println("frefergergegegwgregergerg" + id + nome + username + email + nif + password + matricula + modelo + plano + lugar + marca + "telemovelllllllllll" + phone);

        Utilizador u = new Utilizador();
        final String json = Json.encodePrettily(u);
        HttpServerResponse response = e.response();
        response.putHeader("content-type", "application/json; charset=utf-8");
        response.setStatusCode(200);
        response.end(json);
        } catch (EncodeException ee) {
            System.out.println("exception: " + ee.getMessage());

            e.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodeToBuffer("{erro: 'erro!'}"));
        }

    }
}
