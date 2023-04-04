package monitoreo.modelos.Pregunta2;


import com.esri.arcgisruntime.geometry.*;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import monitoreo.modelos.impl.Punto;
import monitoreo.modelos.impl.PuntoMonitoreoBuilder;

import java.util.List;

public class FacadeRutaMonitoreo {

    private PuntoMonitoreoBuilder puntoBuilder;
    private Punto punto;
    private Ruta ruta;
    private static final SpatialReference SPATIAL_REFERENCE = SpatialReferences.getWgs84();
    private static FacadeRutaMonitoreo facadeRutaMonitoreo;

    public FacadeRutaMonitoreo() {
    }

    public Graphic addPuntoMonitoreo(String name, double lat, double lon){

        puntoBuilder = new PuntoMonitoreoBuilder("Punto Monitoreo: "+name);
        puntoBuilder.withSimbolo(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 10);
        puntoBuilder.withUbicacion(lat, lon);

        punto = puntoBuilder.build();

        return this.punto.getGrafico();
    }

    public String mostrarPuntosMonitoreo(){
        StringBuilder salida = new StringBuilder();

        salida.append(puntoBuilder.getTipoPunto());
        salida.append("\nLatitud: ").append(puntoBuilder.getLatitud());
        salida.append("\nLongitud: ").append(puntoBuilder.getLongitud()).append("\n");

        return salida.toString();

    }

    //Prueba  de ruta
    public Graphic agregarRuta(int color, float grosor, List<CoordenadasDeMonitoreo> coordenadasRuta) {

        PointCollection points = new PointCollection(SPATIAL_REFERENCE);
        Double[][] puntos;

        puntos = generarPuntosRuta(coordenadasRuta);
        for (Double[] punto: puntos) {
            points.add(new Point(punto[1],punto[0]));
        }

        return new Graphic(
                new Polyline(points),
                new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, color, grosor)
        );
    }

    public Double[][] generarPuntosRuta(List<CoordenadasDeMonitoreo> coordenadasRuta){
        Double[][] puntosMonitoreo = new Double[coordenadasRuta.size()][2];

        for(int i=0; i<puntosMonitoreo.length; i++){
            for(int j=0; j<puntosMonitoreo[i].length; j++){
                switch (j){
                    case 0: puntosMonitoreo[i][j] = coordenadasRuta.get(i).getLatitud();
                        break;
                    case 1: puntosMonitoreo[i][j] = coordenadasRuta.get(i).getLongitud();
                        break;
                }
            }
        }
        return puntosMonitoreo;
    }

    public static FacadeRutaMonitoreo getInstance() {
        if (facadeRutaMonitoreo == null) {
            facadeRutaMonitoreo = new FacadeRutaMonitoreo();
        }
        return facadeRutaMonitoreo;
    }

    public PuntoMonitoreoBuilder getPuntoBuilder() {
        return puntoBuilder;
    }

    public void setPuntoBuilder(PuntoMonitoreoBuilder puntoBuilder) {
        this.puntoBuilder = puntoBuilder;
    }
}