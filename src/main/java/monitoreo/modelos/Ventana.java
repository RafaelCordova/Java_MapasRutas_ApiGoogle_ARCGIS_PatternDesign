package monitoreo.modelos;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import monitoreo.modelos.Pregunta2.CoordenadasDeMonitoreo;
import monitoreo.modelos.Pregunta2.FacadeRutaMonitoreo;
import monitoreo.modelos.impl.*;
//import monitoreo.modelos.interfaces.ITipoServicio;

//import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

import java.util.ArrayList;
import java.util.List;

public class Ventana extends Application {

    private Mapa mapa;
    //private GraphicsOverlay graphicsOverlay;

    @Override
    public void start(Stage stage) throws Exception {

        // Crea una fachada par el Mapa
        FachadaMapa facade = new FachadaMapa(stage);
        //facade.getMapaBase().imprimeCoordenadasActual();
        this.mapa = facade.getMapa();

        // Crea la imagen para el botón
        Image img = new Image("https://upload-icon.s3.us-east-2.amazonaws.com/uploads/icons/png/4498062351543238871-512.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(40);
        view.setPreserveRatio(true);

        // Crea el botón de Ventana nueva
        Button btnNuevo = new Button();
        btnNuevo.setGraphic(view);
        btnNuevo.setText("Nuevo");
        facade.getStackPane().getChildren().add(btnNuevo);
        facade.getStackPane().setAlignment(btnNuevo, Pos.BOTTOM_CENTER);
        facade.getStackPane().setMargin(btnNuevo, new Insets(10, 10, 10, 10));
        btnNuevo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                muestraNuevaVentana();
            }
        });

        // Agrega el punto
        PuntoMonitoreoBuilder puntoBuilder = new PuntoMonitoreoBuilder("Inicio del día");

        // PuntoMonitoreoBuilder puntoBuilder2 = new PuntoMonitoreoBuilder("2DO PUNTO PRUEBA");
        puntoBuilder.withSimbolo(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 10);
        // puntoBuilder2.withSimbolo(SimpleMarkerSymbol.Style.SQUARE, 0xFFFF0000, 10);
        // puntoBuilder.withUbicacion(-12.054901, -77.085470);

        //PUNTO INICIAL PINTADO DE ROJO
        puntoBuilder.withUbicacion(-12.05847, -77.08654);
        //  puntoBuilder2.withUbicacion(-12.06,-77.09);

        Punto puntoInicial = puntoBuilder.build();
        //  Punto puntoInicial2 = puntoBuilder2.build();

        puntoBuilder = new PuntoMonitoreoBuilder("Fin del día");
        //puntoBuilder.withSimbolo(SimpleMarkerSymbol.Style.DIAMOND, 0xFF000000, 20);
        puntoBuilder.withUbicacion(-12.055901, -77.086470);
        Punto puntoFinal = puntoBuilder.build();
        //   Punto puntoFinal2 = puntoBuilder2.build();

        facade.addGraphicsOverlay(puntoInicial.getGrafico());

        //   facade.addGraphicsOverlay(puntoInicial2.getGrafico());

        facade.addGraphicsOverlay(puntoFinal.getGrafico());

        //
        //Generando Puntos de Monitoreo
        FacadeRutaMonitoreo fm = new FacadeRutaMonitoreo();
        List<CoordenadasDeMonitoreo> coordenadasRuta = new ArrayList<>();
        List<CoordenadasDeMonitoreo> coordenadasRuta2 = new ArrayList<>();
        List<CoordenadasDeMonitoreo> coordenadasRuta3 = new ArrayList<>();

        facade.addGraphicsOverlay(fm.addPuntoMonitoreo("Puerta 7", -12.054901, -77.085470));
        System.out.println("\n\nLatitud: "+fm.getPuntoBuilder().getLatitud()+"\n\n");
        coordenadasRuta.add(new CoordenadasDeMonitoreo(fm.getPuntoBuilder().getLatitud(), fm.getPuntoBuilder().getLongitud()));
        coordenadasRuta3.add(new CoordenadasDeMonitoreo(fm.getPuntoBuilder().getLatitud(), fm.getPuntoBuilder().getLongitud()));
        System.out.println(fm.mostrarPuntosMonitoreo());

        facade.addGraphicsOverlay(fm.addPuntoMonitoreo("Puerta 8", -12.055901, -77.086470));
        coordenadasRuta.add(new CoordenadasDeMonitoreo(fm.getPuntoBuilder().getLatitud(), fm.getPuntoBuilder().getLongitud()));
        System.out.println(fm.mostrarPuntosMonitoreo());

        facade.addGraphicsOverlay(fm.addPuntoMonitoreo("Puerta 9", -12.056901, -77.087470));
        coordenadasRuta.add(new CoordenadasDeMonitoreo(fm.getPuntoBuilder().getLatitud(), fm.getPuntoBuilder().getLongitud()));
        coordenadasRuta3.add(new CoordenadasDeMonitoreo(fm.getPuntoBuilder().getLatitud(), fm.getPuntoBuilder().getLongitud()));
        System.out.println(fm.mostrarPuntosMonitoreo());

        facade.addGraphicsOverlay(fm.addPuntoMonitoreo("Puerta 10", -12.057901, -77.088470));
        coordenadasRuta.add(new CoordenadasDeMonitoreo(fm.getPuntoBuilder().getLatitud(), fm.getPuntoBuilder().getLongitud()));
        System.out.println(fm.mostrarPuntosMonitoreo());

        facade.addGraphicsOverlay(fm.addPuntoMonitoreo("Puerta 11", -12.058901, -77.089470));
        coordenadasRuta.add(new CoordenadasDeMonitoreo(fm.getPuntoBuilder().getLatitud(), fm.getPuntoBuilder().getLongitud()));
        System.out.println(fm.mostrarPuntosMonitoreo());


//Ruta 2
        facade.addGraphicsOverlay(fm.addPuntoMonitoreo("Puerta 1", -12.0546, -77.087));
        System.out.println("\n\nLatitud: "+fm.getPuntoBuilder().getLatitud()+"\n\n");
        coordenadasRuta2.add(new CoordenadasDeMonitoreo(fm.getPuntoBuilder().getLatitud(), fm.getPuntoBuilder().getLongitud()));
        System.out.println(fm.mostrarPuntosMonitoreo());

        facade.addGraphicsOverlay(fm.addPuntoMonitoreo("Puerta 2", -12.0563, -77.0901));
        coordenadasRuta2.add(new CoordenadasDeMonitoreo(fm.getPuntoBuilder().getLatitud(), fm.getPuntoBuilder().getLongitud()));
        System.out.println(fm.mostrarPuntosMonitoreo());

        facade.addGraphicsOverlay(fm.addPuntoMonitoreo("Puerta 3", -12.0583, -77.086));
        coordenadasRuta2.add(new CoordenadasDeMonitoreo(fm.getPuntoBuilder().getLatitud(), fm.getPuntoBuilder().getLongitud()));
        coordenadasRuta3.add(new CoordenadasDeMonitoreo(fm.getPuntoBuilder().getLatitud(), fm.getPuntoBuilder().getLongitud()));
        System.out.println(fm.mostrarPuntosMonitoreo());

        facade.addGraphicsOverlay(fm.addPuntoMonitoreo("Puerta 4", -12.0595, -77.0866));
        coordenadasRuta2.add(new CoordenadasDeMonitoreo(fm.getPuntoBuilder().getLatitud(), fm.getPuntoBuilder().getLongitud()));
        System.out.println(fm.mostrarPuntosMonitoreo());

        /*
         * -12.0546 -77.087
         * -12.0563 -77.0901
         * -12.0583 -77.086
         * -12.0595 -77.0866
         * */

        //Generando una ruta
        facade.addGraphicsOverlay(fm.agregarRuta(0xFFFFFF00, 3.0f, coordenadasRuta));
        facade.addGraphicsOverlay(fm.agregarRuta(0xFFFF0000, 5.0f, coordenadasRuta2));
        facade.addGraphicsOverlay(fm.agregarRuta(0xFF000000, 3.0f, coordenadasRuta3));

        //

        // Dibuja el mapa en la ventana
        //facade.stackAddMapView();
        facade.mostrarVentana();
    }

    public void muestraNuevaVentana() {
        Stage stage = new Stage();
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.setTitle("Sistema de Monitoreo de Vehiculos NUEVO");
        stage.setWidth(800);
        stage.setHeight(700);

        //  Clonacion de MapaBase
        Mapa mapaBase2 = (Mapa)mapa.copiar();

        mapaBase2.imprimeCoordenadasActual();
        stackPane.getChildren().add(mapaBase2.getMapView());

        stage.show();
    }


}
