/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import Controller.UserController;
import Model.Utilizador;
import Model.Viatura;

import io.vertx.core.json.EncodeException;

import Model.Utilizador;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystemOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import static io.vertx.ext.web.handler.StaticHandler.DEFAULT_WEB_ROOT;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;

public class Servidor extends AbstractVerticle {

    String webRoot = DEFAULT_WEB_ROOT;
    Router router;
    Utilizador u;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {

        Handlers handlers = new Handlers();
        router = routes(handlers);

        HttpServerOptions options = new HttpServerOptions();
        options.setHost("127.0.0.1").setPort(8004);

        vertx.createHttpServer(options)
                .requestHandler(router) //usa o router para manipular qualquer pedido
                .listen(res -> {
                    if (res.succeeded()) {
                        startPromise.complete();
                        System.out.println("Servidor HTTP no porto " + options.getPort());
                    } else {
                        startPromise.fail(res.cause());
                        System.out.println("Nao foi possivel iniciar o servidor HTTP");
                    }
                });
    }

    private Router routes(Handlers handlers) {
        router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route(HttpMethod.GET, "/GestorScreen").handler(StaticHandler.create(webRoot + "/" + "GestorScreen.html").setDefaultContentEncoding("UTF-8"));
        router.route(HttpMethod.GET, "/ClienteScreen/").handler(StaticHandler.create(webRoot + "/" + "ClienteScreen.html").setDefaultContentEncoding("UTF-8"));
        router.route(HttpMethod.GET, "/AMinhaConta").handler(StaticHandler.create(webRoot + "/" + "AMinhaConta.html").setDefaultContentEncoding("UTF-8"));
        router.route().handler(StaticHandler.create().setWebRoot(webRoot).setDefaultContentEncoding("UTF-8"));
        // serve index
        router.route("/").handler(StaticHandler.create(webRoot).setDefaultContentEncoding("UTF-8"));
        router.post("/submitLoginForm").handler(new UserController()::login);
        router.route(HttpMethod.POST, "/ListarClientes").handler(this::ListarClientes);
        router.get("/ClienteScreen/*").handler((this::PaginaCliente));
        router.get("/InfoUser/*").handler((this::ContaPessoal));
        router.post("/cliente/:id").handler((this::AMinhaConta));
        
        router.route(HttpMethod.POST, "/editarCliente").handler(this::AlterarCliente);
        
        router.route().handler(BodyHandler.create());
        router.route(HttpMethod.POST, "/addUtilizador").handler(this::verificarUtilizador);
        router.route(HttpMethod.POST, "/addViatura").handler(this::verificarViatura);
     
        return router;

    }

    private void verificarUtilizador(RoutingContext rc) {
        UserController rcc = new UserController();
        String username = rc.request().getParam("username");
        String nif = rc.request().getParam("nif");
        String nome = rc.request().getParam("nome");
        String phone = rc.request().getParam("phone");
        String email = rc.request().getParam("email");
        String password = rc.request().getParam("password");

        ArrayList<Utilizador> utilizadores = rcc.listarUtilizadores();
        System.out.println("Lista de Utilizadores: " + utilizadores.size());

        for (int i = 0; i < utilizadores.size(); i++) {
            System.out.println(utilizadores.get(i).getNome());
            if (utilizadores.get(i).getEmail().equalsIgnoreCase(email) || utilizadores.get(i).getNome().equalsIgnoreCase(nome)) {
                nome = "false";
                email = "false";
            }
        }
        System.out.println("h" + nome + email + username);
        HttpServerResponse response = rc.response();
        System.out.println("resposta: " + response.getStatusCode());
        response.putHeader("content-type", "application/json; charset=utf-8");
        System.out.println("Nome:" + nome + "email:" + email);
        if (!nome.equals("false") && !email.equals("false")) {
            rcc.addUtilizador(rc, email, nome, password, username, phone, nif);
            u = new Utilizador(rcc.getUtilizador(rc, username, password), email, nome, password, username, phone, nif);
            response.setStatusCode(201);
        } else {
            response.setStatusCode(400);
        }
        response.putHeader("content-type", "application/json; charset=utf-8");
        response.end();
    }

    private void verificarViatura(RoutingContext rc) {
        UserController rcc = new UserController();
        String modelo = rc.request().getParam("modelo");
        String marca = rc.request().getParam("marca");
        String matricula = rc.request().getParam("licenseplate");

        ArrayList<Viatura> viatura = rcc.listarVeiculos();

        for (int i = 0; i < viatura.size(); i++) {
            System.out.println(viatura.get(i).getMatricula());
            if (viatura.get(i).getMatricula().equalsIgnoreCase(matricula)) {
                matricula = "false";

            }
        }

        HttpServerResponse response = rc.response();
        System.out.println("resposta: " + response.getStatusCode());
        response.putHeader("content-type", "application/json; charset=utf-8");

        if (!matricula.equals("false")) {
            rcc.addVeiculo(rc, modelo, marca, u.getId(), matricula);

            response.setStatusCode(201);
        } else {
            response.setStatusCode(400);
        }
        response.putHeader("content-type", "application/json; charset=utf-8");
        response.end();
    }

    private void ListarClientes(RoutingContext e) {

        JSONObject json2 = new JSONObject();
        UserController pc = new UserController();
        ArrayList<Utilizador> listaUsers = pc.listaUtilizadores();
        int i = 1;
        for (Utilizador u : listaUsers) {
            JSONObject json1 = new JSONObject();
            json1.put("nome", u.getNome());
            json1.put("matricula", u.getMatricula());
            json1.put("telemovel", u.getTelemovel());
            json1.put("email", u.getEmail());
            json1.put("nif", u.getNif());
            json1.put("lugar", u.getLugar());
            json1.put("plano", u.getPlano());
            json2.put(i, json1);
            i++;
        }

        JSONObject finalJson = new JSONObject();
        finalJson.put("clientes", json2);
        HttpServerResponse response = e.response();
        response.putHeader("content-type", "application/json; charset=utf-8");
        response.setStatusCode(200);
        response.end(finalJson.toJSONString());
    }

    private void AMinhaConta(RoutingContext rc) {
        UserController rcc = new UserController();
        JSONObject json1 = new JSONObject();
        int id = Integer.parseInt(rc.request().getParam("id"));
        System.out.println("Id --------------->" + id);
        Utilizador ut = rcc.Utilizador(rc, id);
        json1.put("id", ut.getId());
        json1.put("nome", ut.getNome());
        json1.put("matricula", ut.getMatricula());
        json1.put("telemovel", ut.getTelemovel());
        json1.put("email", ut.getEmail());
        json1.put("nif", ut.getNif());
        json1.put("lugar", ut.getLugar());
        json1.put("plano", ut.getPlano());
        json1.put("username", ut.getUsername());
        json1.put("password", ut.getPassword());
        json1.put("modelo", ut.getModelo());
        json1.put("marca", ut.getMarca());

        JSONObject finalJson = new JSONObject();
        finalJson.put("cliente", json1);
        HttpServerResponse response = rc.response();
        response.putHeader("content-type", "application/json; charset=utf-8");
        response.setStatusCode(200);
        response.end(finalJson.toJSONString());
    }

    private void PaginaCliente(RoutingContext context) {
        context.response().putHeader("content-type", "text/html");
        context.response().sendFile(webRoot + "/" + "ClienteScreen.html");
        context.response().setStatusCode(202);
        context.response().end();
    }

    private void ContaPessoal(RoutingContext e) {
        e.response().putHeader("content-type", "text/html");
        e.response().sendFile(webRoot + "/" + "AMinhaConta.html");
        e.response().setStatusCode(202);
        e.response().end();
    }

    private void AlterarCliente(RoutingContext e) {

        Handlers cf = new Handlers();

        try {
            int id = Integer.parseInt(e.request().getParam("id"));

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
            Utilizador u = new Utilizador();
            final String json = Json.encodePrettily(u);
            e.response().setStatusCode(201).putHeader("content-type", "application/json; charset=utf-8").end(json);

        } catch (EncodeException ee) {
            System.out.println("exception: " + ee.getMessage());

            e.response()
                    .setStatusCode(500)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodeToBuffer("{erro: 'erro!'}"));
        }
        
    }
}
