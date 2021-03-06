package servidor;

import Controller.UserController;

import Model.Reservar;

import FicheirosCSV.LerFicheiros;

import Model.Utilizador;
import Model.Viatura;

import io.vertx.core.json.EncodeException;

import Model.Utilizador;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.file.FileSystemOptions;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.streams.Pump;
import io.vertx.core.streams.ReadStream;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import static io.vertx.ext.web.handler.StaticHandler.DEFAULT_WEB_ROOT;
import java.io.File;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import com.mysql.cj.result.LocalTimeValueFactory;

import org.json.simple.JSONObject;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.streams.Pump;
import io.vertx.core.streams.ReadStream;
import io.vertx.ext.web.RoutingContext;

public class Servidor extends AbstractVerticle  {

    String webRoot = DEFAULT_WEB_ROOT;
    Router router;
    Utilizador u;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {

        DAL handlers = new DAL();
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

    private Router routes(DAL handlers) {
        router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route(HttpMethod.GET, "/GestorScreen").handler(StaticHandler.create(webRoot + "/" + "GestorScreen.html").setDefaultContentEncoding("UTF-8"));
        router.route(HttpMethod.GET, "/ClienteScreen/").handler(StaticHandler.create(webRoot + "/" + "ClienteScreen.html").setDefaultContentEncoding("UTF-8"));
        router.route(HttpMethod.GET, "/ClienteScreen/FinancialScreen.html").handler(StaticHandler.create(webRoot + "/" + "FinancialScreen.html").setDefaultContentEncoding("UTF-8"));
        router.route(HttpMethod.GET, "/ClienteScreen/Reserva.html").handler(StaticHandler.create(webRoot + "/" + "Reserva.html").setDefaultContentEncoding("UTF-8"));
        router.route(HttpMethod.GET, "/ClienteScreen/MapScreen.html").handler(StaticHandler.create(webRoot + "/" + "MapScreen.html").setDefaultContentEncoding("UTF-8"));
          router.route(HttpMethod.GET, "/InfoUser/AMinhaConta.html").handler(StaticHandler.create(webRoot + "/" + "AMinhaConta.html").setDefaultContentEncoding("UTF-8"));
           router.route(HttpMethod.GET, "/InfoUser/InformationScreen.html").handler(StaticHandler.create(webRoot + "/" + "InformationScreen.html").setDefaultContentEncoding("UTF-8"));
            router.route(HttpMethod.GET, "/InfoUser/FinancialScreen.html").handler(StaticHandler.create(webRoot + "/" + "FinancialScreen.html").setDefaultContentEncoding("UTF-8"));
            router.route(HttpMethod.GET, "/InfoUser/MapScreen.html").handler(StaticHandler.create(webRoot + "/" + "MapScreen.html").setDefaultContentEncoding("UTF-8"));
            router.route(HttpMethod.GET, "/InfoUser/Reserva.html").handler(StaticHandler.create(webRoot + "/" + "Reserva.html").setDefaultContentEncoding("UTF-8"));
             router.route(HttpMethod.GET, "/InfoUser/ClienteScreen.html").handler(StaticHandler.create(webRoot + "/" + "ClienteScreen.html").setDefaultContentEncoding("UTF-8"));
                  router.route(HttpMethod.GET, "/InfoUser/index.html").handler(StaticHandler.create(webRoot + "/" + "index.html").setDefaultContentEncoding("UTF-8"));
        router.route(HttpMethod.GET, "/ClienteScreen/InformationScreen.html").handler(StaticHandler.create(webRoot + "/" + "InformationScreen.html").setDefaultContentEncoding("UTF-8"));
        router.route(HttpMethod.GET, "/AMinhaConta").handler(StaticHandler.create(webRoot + "/" + "AMinhaConta.html").setDefaultContentEncoding("UTF-8"));
        router.route(HttpMethod.GET, "/Estatisticas").handler(StaticHandler.create(webRoot + "/" + "Estatistica.html").setDefaultContentEncoding("UTF-8"));
        router.route().handler(StaticHandler.create().setWebRoot(webRoot).setDefaultContentEncoding("UTF-8"));
        //serve index
        router.route("/").handler(StaticHandler.create(webRoot).setDefaultContentEncoding("UTF-8"));
        router.post("/submitLoginForm").handler(new UserController()::login);
        router.route(HttpMethod.POST, "/ListarClientes").handler(this::ListarClientes);
        router.route(HttpMethod.POST, "/ListarStats").handler(this::ListarStats);
        router.get("/ClienteScreen/*").handler((this::PaginaCliente));
        router.get("/InfoUser/*").handler((this::ContaPessoal));
        
        router.post("/cliente/:id").handler((this::AMinhaConta));
        router.post("/SendFile").handler((this::Sending));

        router.route(HttpMethod.POST, "/edicao").handler(new UserController()::AlterarCliente);
     


        router.route().handler(BodyHandler.create());
        router.route(HttpMethod.POST, "/addUtilizador").handler(this::verificarUtilizador);
        router.route(HttpMethod.POST, "/addViatura").handler(this::verificarViatura);
        router.route(HttpMethod.POST, "/addReserva").handler(this::inserirReserva);

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


    private void ListarStats(RoutingContext e) {
        JSONObject json1 = new JSONObject();


        DAL cf = new DAL();

//        json1.put("NumeroViaturas", cf.NumeroViaturasReal());

        json1.put("NumeroReservas", cf.NumeroReservas());
        json1.put("NumeroModelo", cf.NumeroModel());
        //json1.put("NumeroPlano",cf.NumeroPlano());
        JSONObject finalJson = new JSONObject();
        finalJson.put("stats", json1);
        HttpServerResponse response = e.response();
        response.putHeader("content-type", "application/json; charset=utf-8");
        response.setStatusCode(200);
        response.end(finalJson.toJSONString());
    }


    public void inserirReserva(RoutingContext rc){
        UserController rcc = new UserController();
        String plano = rc.request().getParam("tipoReserva");
        String zona = rc.request().getParam("zona");
        int lugar = Integer.parseInt(rc.request().getParam("lugar"));
        Date dataInicio = new Date();
        try {
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String date = rc.request().getParam("entrada");
            dataInicio = format.parse(date);
        }catch (ParseException e){
            System.out.println(e);
        }
        Date dataFim = new Date();
        try {
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String date = rc.request().getParam("saida");
            dataFim = format.parse(date);
        }catch (ParseException e){
            System.out.println(e);
        }
        int viatura = Integer.parseInt(rc.request().getParam("viatura"));
        LocalTime HoraEntrada = LocalTime.now();
        LocalTime HoraSaida = LocalTime.now();
        Reservar r = new Reservar(plano, dataInicio, dataFim, HoraEntrada, HoraSaida, viatura, lugar, u.getId());
    }

    private void Sending(RoutingContext e) {
        DeliveryOptions options = new DeliveryOptions();
        HttpServerResponse response = e.response();
        response.putHeader(HttpHeaders.CONTENT_TYPE, "application/csv")
                .putHeader("Content-Disposition", "attachment; filename=sinf.csv")
                .putHeader(HttpHeaders.TRANSFER_ENCODING, "chunked").setChunked(true);

        ReadStream<Buffer> consumer = this.vertx.eventBus().<Buffer>consumer("3fb24b47-ae9d-46fd-a915-480245c8ebda")
                .bodyStream();
        consumer.handler(result -> {
            JsonArray array = (JsonArray) result;
          response.setStatusCode(200);
          response.end(toString(array));
        });

      
    }

    public String toString(final JsonArray data) {
        return String.join(",", "" + data.getValue(0), "" + data.getValue(1), "" + data.getValue(2), "" + data.getValue(3), "" + data.getValue(4), "" + data.getValue(5), "\r\n");
    }
}

