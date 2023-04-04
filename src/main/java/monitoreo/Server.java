package monitoreo;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import monitoreo.modelos.Pregunta4.ConexionPatronSingleton;
import monitoreo.modelos.RegistroLog;
import monitoreo.modelos.Ventana;
import javafx.application.Application;

public class Server {

    public static void main(String[] args) {

        ConexionPatronSingleton.getInstance(ConexionPatronSingleton.ip,ConexionPatronSingleton.port).close();
        RegistroLog.getInstance().log("Iniciando servidor...");
        ArcGISRuntimeEnvironment.setInstallDirectory("C://Users//Rafael//Desktop//arcgis-runtime-sdk-java-100.9.0");

        // ArcGISRuntimeEnvironment.setInstallDirectory("C://Aplicaciones//arcgis-runtime-sdk-java-100.9.0");
        //ArcGISRuntimeEnvironment.setInstallDirectory("C://Users//Rafael//Desktop//arcgis-runtime-sdk-java-100.9.0");

        Application.launch(Ventana.class, args);

        }

    }


