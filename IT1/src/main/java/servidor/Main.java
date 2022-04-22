
package servidor;

import io.vertx.core.Vertx;
/**
 *
 * @author Misterio, ACS, SINF1, LES, ISEP, 2022
 */

public class Main {
    
    public static void main(String[] args){
        
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new Servidor());
    }
    
}
