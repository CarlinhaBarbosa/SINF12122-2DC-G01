/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import Controller.UserController;
import io.vertx.ext.web.RoutingContext;
import static io.vertx.ext.web.handler.StaticHandler.DEFAULT_WEB_ROOT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carla
 */
public class Handlers {
    
 
    String webRoot = DEFAULT_WEB_ROOT;
       public void idUtilizador(String userName, String password) {
        ResultSet rs = null;
        int numero = 0;
        try {

            String query0 = "SELECT id from user where username= '" + userName + "' AND password='" + password + "'";
            rs = this.returnQuery(query0);

            while (rs.next()) {
                numero = rs.getInt("id");
            }
            UserController.id_utilizadorObtido = numero;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }  private void executaQuery(String query) {
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
}

