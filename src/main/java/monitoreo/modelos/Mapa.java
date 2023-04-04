package monitoreo.modelos;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.CoordinateFormatter;
import com.esri.arcgisruntime.geometry.Point;
//import com.esri.arcgisruntime.mapping.ArcGISMap;
//import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.MapView;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import monitoreo.modelos.Pregunta4.ConexionPatronSingleton;
import monitoreo.modelos.Pregunta4.CoordenadasCapturadas;
import monitoreo.modelos.impl.MapaBaseProxy;
import monitoreo.modelos.impl.Punto;
import monitoreo.modelos.interfaces.IMapa;

import javax.swing.*;

public class Mapa implements IMapa {

    private MapView mapView;
    private int idVentana;

    private double coordenadaXInicial;
    private double coordenadaYInicial;

    private double coordenadaXActual;
    private double coordenadaYActual;

    private RegistroLog registro = RegistroLog.getInstance();

    public Mapa()   {

        // create a MapView to display the map and add it to the stack pane
        mapView = new MapView();
        idVentana++;

        // create an ArcGISMap basemap
        MapaBaseProxy proxy = new MapaBaseProxy();

        int opc = Integer.parseInt(JOptionPane.showInputDialog(null,"-)INGRESE EL TIPO DE MAPA PARA CARGAR(1,2,3) \n-CARGAR ARGIS API GOOGLE (5)"));
        if(opc==1||opc==2||opc==3||opc==4){
            proxy.setTipoMapa(opc);
        }else if(opc==5){
            try {
                Desktop.getDesktop().browse(new URI("http://localhost:4200/route"));
                 System.exit(0);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }

        mapView.setMap(proxy.getMapaBase());

        // latitude, longitude, scale
        //Viewpoint viewpoint = new Viewpoint(27.3805833, 33.6321389, 6E3);
        this.coordenadaXInicial = -12.05847;
        this.coordenadaYInicial = -77.08654;
        Viewpoint viewpoint = new Viewpoint(this.coordenadaXInicial, this.coordenadaYInicial, 12000);   // UNMSM-2

        // take 5 seconds to move to viewpoint
        final ListenableFuture<Boolean> viewpointSetFuture = mapView.setViewpointAsync(viewpoint, 5);
        viewpointSetFuture.addDoneListener(() -> {
            try {
                boolean completed = viewpointSetFuture.get();
                if (completed) {
                    registro.log("IdVentana:["+ idVentana +"] - Acercamiento completado");
                    //ALERTA DEL PUNTO INICIAL
                     JOptionPane.showMessageDialog(null,"COORDENADAS INICIALES "+this.coordenadaXInicial+","+this.coordenadaYInicial);
                }
            } catch (InterruptedException e) {
                registro.log("IdVentana:["+ idVentana +"] - Acercamiento interrumpido");
            } catch (ExecutionException e) {
                // Deal with exception during animation...
            }
        });

        // click event to display the callout with the formatted coordinates of the clicked location
        mapView.setOnMouseClicked(e -> {
            // check that the primary mouse button was clicked and user is not panning
            if (e.isStillSincePress() && e.getButton() == MouseButton.PRIMARY) {
                // get the map point where the user clicked
                Point2D point = new Point2D(e.getX(), e.getY());
                //System.out.println("Coordenadas: " + e.getX() + ", " + e.getY());
                Point mapPoint = mapView.screenToLocation(point);
                // show the callout at the point with the different coordinate format strings
                showCalloutWithLocationCoordinates(mapPoint);
            }
        });
    }


    private void showCalloutWithLocationCoordinates(Point location) {

        CoordenadasCapturadas cc = new CoordenadasCapturadas();

        Callout callout = mapView.getCallout();
        callout.setTitle("         COORDENADAS REGISTRADAS:");
        callout.setMaxSize(900,900);
        callout.setBackgroundColor(new Color(1.0D, 1.0D, 0.0D, 1.0D));

        double coorX, coorY;

        this.coordenadaXActual = location.getX();
        this.coordenadaYActual = location.getY();

        coorX = this.coordenadaXActual;
        coorY = this.coordenadaYActual;

        registro.log("IdVentana:["+ idVentana +"] - Coordenadas: " + this.coordenadaXActual + ", " + this.coordenadaYActual);


        String latLonDecimalDegrees = CoordinateFormatter.toLatitudeLongitude(location, CoordinateFormatter
                .LatitudeLongitudeFormat.DECIMAL_DEGREES, 4);

        String latLonDegMinSec = CoordinateFormatter.toLatitudeLongitude(location, CoordinateFormatter
                .LatitudeLongitudeFormat.DEGREES_MINUTES_SECONDS, 1);

        String utm = CoordinateFormatter.toUtm(location, CoordinateFormatter.UtmConversionMode.LATITUDE_BAND_INDICATORS,true);
        String usng = CoordinateFormatter.toUsng(location, 4, true);

        String aux_coord = formatoCoordenada(latLonDecimalDegrees);
        String arreglo[] = new String[2];

        arreglo = conversionCoordenada(aux_coord);
        double xFinal = (-1)*Double.parseDouble(arreglo[0])/Math.pow(10,4);
        double yFinal = (-1)*Double.parseDouble(arreglo[1])/Math.pow(10,4);

        this.coordenadaXActual = xFinal;
        this.coordenadaYActual = yFinal;

        mapView.getCallout().showCalloutAt(location, new Duration(600));

        System.out.println(latLonDecimalDegrees);
        System.out.println(xFinal+"---"+yFinal);


        CoordenadasCapturadas.coordenadasCapturadas.add(
                new Punto("Sistema de monitoreo de Vehiculos :"
                        // +String.valueOf(this.idVentana),this.coordenadaXInicial, this.coordenadaYInicial,this.coordenadaXActual, this.coordenadaYActual)
                        +String.valueOf(this.idVentana),coorX,coorY,this.coordenadaXInicial,this.coordenadaYInicial,xFinal, yFinal)
        );


        callout.setDetail(
                "LATITUD , LONGITUD :" + this.coordenadaXActual+","+this.coordenadaYActual+ "\n" +
                "Grados Decimales: " + latLonDecimalDegrees + "\n" +
                        "Grados, Minutos y Segundos " + latLonDegMinSec + "\n" +
                        "UTM: " + utm + "\n" +
                        "USNG: " + usng + "\n"
        );


        System.out.println("PREPARANDO PARA REGISTRAR COORDENADAS");
        //cc.mostrarUltimaCoordenada();
        LinkedList<Punto> coord = new LinkedList<Punto>(CoordenadasCapturadas.coordenadasCapturadas);
        try{
            //LLAMANDO AL METODO GET ISNTANCE DE LA CLASE PARA CREAR LA CONEXION
            // Metemos los objetos PUNTOS
            ConexionPatronSingleton.collection.insert(coord.get(coord.size()-1).toDBObjectPunto());
            //  ConexionPatronSingleton.mongoClient.close();

        }catch(Exception e){
            System.out.println("ERROR DE CONEXION A LA BD MONGO :"+e.getMessage());
        }

    }


    public static String formatoCoordenada(String coordenadaGeografica){
        String numberTransform= coordenadaGeografica.replaceAll("[^0-9]","");
        return (numberTransform);
    }


    public static String[] conversionCoordenada(String cadena){

        char arreglo[] = cadena.toCharArray();
        String coordenadas[] = new String[2];

        coordenadas[0] = String.valueOf(arreglo[0])+String.valueOf(arreglo[1])+String.valueOf(arreglo[2])
                +String.valueOf(arreglo[3])+String.valueOf(arreglo[4])+String.valueOf(arreglo[5]);
        coordenadas[1] = String.valueOf(arreglo[6])+String.valueOf(arreglo[7])+String.valueOf(arreglo[8])+
                String.valueOf(arreglo[9])+String.valueOf(arreglo[10])+String.valueOf(arreglo[11]+String.valueOf(arreglo[12]));
        return coordenadas;
    }


    public MapView getMapView() {
        return mapView;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }


    public void imprimeCoordenadasActual()  {

        //System.out.println("Coordenadas actual: [" + this.coordenadaXActual + ", " + this.coordenadaYActual + "]");
        registro.log("IdVentana:["+ idVentana +"] - Coordenadas actual: [" + this.coordenadaXActual + ", " + this.coordenadaYActual + "]");
    }

    @Override
    public IMapa copiar() {
        //return new Mapa(this);
        Mapa m = new Mapa();
        this.idVentana++;
        m.idVentana = this.idVentana;
        m.coordenadaXInicial = this.coordenadaXInicial;
        m.coordenadaYInicial = this.coordenadaYInicial;
        m.coordenadaXActual = this.coordenadaXActual;
        m.coordenadaYActual = this.coordenadaYActual;
        return m;
    }



}