package servidor;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLClient;

public class DataBaseVerticle extends AbstractVerticle {
	public void start(Future<Void> future) throws Exception {
		super.start((Promise<Void>) future);
		SQLClient client = JDBCClient.createShared(vertx, getMySQLConfigForJDBC());
		DatabaseHandler dbHandler = new DatabaseHandler(client, vertx.eventBus().consumer(DatabaseHandler.ADDRESS), vertx);
                
		dbHandler.execute();
	}

	private JsonObject getMySQLConfig() {
		JsonObject config = new JsonObject();
		config.put("host", "localhost");
		config.put("port", 3306);
		config.put("database", "greymetrics");
                
		config.put("username", "root");
		config.put("password", "root@1010");
		config.put("charset", "UTF-8");
		config.put("maxPoolSize", 50);
		return config;
	}
  

   
	private JsonObject getMySQLConfigForJDBC() {
		JsonObject config = new JsonObject();
		config.put("url", "jdbc:mysql://ctesp.dei.isep.ipp.pt:3306/2022sinf1_016");
		
		config.put("user", "2022sinf1_016");
		config.put("password", "Qh48833hJE!");
		
		return config;
	}
}