package monitoreo.modelos.Pregunta4;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class ConexionPatronSingleton extends Mongo{

    public  static String ip = "localhost"; public static int port = 27017;
    public  static ConexionPatronSingleton mongoClient = getInstance(ip,port);

    public  static  DB db = mongoClient.getDB("Monitoreo");
    public  static  DBCollection collection = db.getCollection("Parametros");
    private static ConexionPatronSingleton instance = null;

    private ConexionPatronSingleton(String ip, int port) {
        super(ip, port);
                                                          }

    public static ConexionPatronSingleton getInstance(String ip, int port) {
        if (instance == null){
            instance =  new ConexionPatronSingleton(ip,port);
                              }
        return instance;
                                }
                                            }

