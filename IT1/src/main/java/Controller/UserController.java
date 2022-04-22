/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.Utilizador;
import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import java.util.ArrayList;

/**
 *
 * @author Carla
 */
public class UserController {
    



    public static int id_utilizadorObtido = 0;
    private static Utilizador ut;
    boolean isAuthenticated = false;

    public static servidor.Handlers cf = new servidor.Handlers();

    public static void obterUserName(RoutingContext routingContext) {
        System.out.println("obterUtilizador() - " + routingContext.toString());
        String json = Json.encodePrettily(ut);
        System.out.println(ut.toString());
        routingContext.response().setStatusCode(200).putHeader("content-type", "application/json; charset=utf-8").end(json);

    }

//    public void addUtilizador(RoutingContext routingContext, String nome, String email, String password, int nif, int telemovel, String matricula ...) {
//        System.out.println("addUtilizador() - " + routingContext.toString());
//        try {
//            Utilizador u = new Utilizador(0, nome, email, password);
//            cf.introduzirUtilizador(nome, email, password);
//            final String json = Json.encodePrettily(u);
//            routingContext.response().setStatusCode(201).putHeader("content-type", "application/json; charset=utf-8").end(json);
//
//        } catch (EncodeException e) {
//            System.out.println("exception: " + e.getMessage());
//            routingContext.response()
//                    .setStatusCode(500)
//                    .putHeader("content-type", "application/json; charset=utf-8")
//                    .end(Json.encodeToBuffer("{erro: 'erro!'}"));
//        }
//    }

    public void login(RoutingContext rc) {
        String userName = rc.request().getParam("userlogin");
        String password = rc.request().getParam("passwordlogin");
        System.out.println(userName + " - " + password);
        cf.idUtilizador(userName, password);
        HttpServerResponse response = rc.response();
        ut = new Utilizador();
        String json = Json.encodePrettily(ut);
        if (UserController.id_utilizadorObtido != 0) {

            isAuthenticated = true;
            response.setStatusCode(200);

            ut = new Utilizador(userName, password);
            json = Json.encodePrettily(ut);
        } else {

            response.setStatusCode(400);
        }
        response.putHeader("content-type", "application/json; charset=utf-8");
        response.end(json.toString());
    }

//    public ArrayList<Utilizador> listarUtilizadores() {
//        return cf.getListaUtilizadores();
//    }

}
