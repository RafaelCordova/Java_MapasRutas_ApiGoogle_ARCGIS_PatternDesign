package monitoreo.modelos.impl;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.mongodb.BasicDBObject;
import monitoreo.modelos.Pregunta4.CoordenadasCapturadas;
import monitoreo.modelos.interfaces.IGrafico;
import org.bson.types.ObjectId;

import javax.swing.*;

public class Punto implements IGrafico {

    //PARA GRABAR EL TITULO DE LA VENTANA
    private String tituloVentana;

    //COORDENADAS INICIALES
    private Double latInicial;
    private Double longInicial;

    //COORDENADAS GLOBALES RARAS
    private Double latitudOrigin;
    private Double longitudOrigin;

    //PARA CALCULAR DISTANCIA
    private Double distancia;

    private String tipoPunto;
    private Graphic punto;
    private static final SpatialReference SPATIAL_REFERENCE = SpatialReferences.getWgs84();
    private SimpleMarkerSymbol simbolo;

    private Double latitud;
    private Double longitud;
    private SimpleMarkerSymbol.Style estilo;
    private int color;
    private int tamano;

    public Punto() {
    }

    public Punto(String tituloVentana,Double latitudOrigin,Double longitudOrigin,Double latIni,Double longIni,Double latitud,Double longitud) {
        this.tituloVentana = tituloVentana;

        this.latitudOrigin = latitudOrigin;
        this.longitudOrigin = longitudOrigin;

        this.latInicial = latIni;
        this.longInicial = longIni;

        this.latitud = latitud;
        this.longitud = longitud;

        this.distancia = CoordenadasCapturadas.distanciaCoord(latIni,longIni,latitud,longitud);
          }


    public void generaPunto()   {
        simbolo = new SimpleMarkerSymbol(estilo, color, tamano);
        punto = new Graphic(new Point(longitud, latitud, SPATIAL_REFERENCE), simbolo);


    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public SimpleMarkerSymbol.Style getEstilo() {
        return estilo;
    }

    public void setEstilo(SimpleMarkerSymbol.Style estilo) {
        this.estilo = estilo;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public String getTipoPunto() {
        return tipoPunto;
    }

    public void setTipoPunto(String tipoPunto) {
        this.tipoPunto = tipoPunto;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public Double getLatInicial() {
        return latInicial;
    }

    public void setLatInicial(Double latInicial) {
        this.latInicial = latInicial;
    }

    public Double getLongInicial() {
        return longInicial;
    }

    public void setLongInicial(Double longInicial) {
        this.longInicial = longInicial;
    }

    public Double getLatitudOrigin() {
        return latitudOrigin;
    }

    public void setLatitudOrigin(Double latitudOrigin) {
        this.latitudOrigin = latitudOrigin;
    }

    public Double getLongitudOrigin() {
        return longitudOrigin;
    }

    public void setLongitudOrigin(Double longitudOrigin) {
        this.longitudOrigin = longitudOrigin;
    }

    public Graphic getGrafico(){
        return getPunto();
    }

    @Override
    public void mostrar() {

    }

    @Override
    public void agregar(IGrafico grafico) {

    }

    @Override
    public void eliminar(IGrafico grafico) {

    }

    public Graphic getPunto() {
        // alertas
        System.out.println("[Punto] Obteniendo Punto para agregarlo al mapa");
        return punto;

    }

    @Override
    public void mover(Integer x, Integer Y) {

    }

    public  BasicDBObject toDBObjectPunto(){

        BasicDBObject dbObjectPunto = new BasicDBObject();

        String id_aux = new ObjectId().toString();

        dbObjectPunto.append("_id",id_aux);
        dbObjectPunto.append("TITULO VENTANA ",this.tituloVentana);
        dbObjectPunto.append("COORDENADA_X:",this.latitudOrigin);
        dbObjectPunto.append("COORDENADA_Y",this.longitudOrigin);
        dbObjectPunto.append("LATITUD:",this.latitud);
        dbObjectPunto.append("LONGITUD",this.longitud);
        dbObjectPunto.append("DISTANCIA",Math.round(this.distancia*1000*100.0)/100.0);
        return dbObjectPunto;
    }



}
