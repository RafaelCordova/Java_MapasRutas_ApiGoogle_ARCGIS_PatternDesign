package monitoreo.modelos.impl;

import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

import monitoreo.modelos.interfaces.IPuntoMonitoreo;

public class PuntoMonitoreoBuilder implements IPuntoMonitoreo {
    
    private String tipoPunto;
    private Double latitud;
    private Double longitud;
    private SimpleMarkerSymbol.Style estilo;
    private int color;
    private int tamano;


    public PuntoMonitoreoBuilder(String tipoPunto)  {
        this.tipoPunto = tipoPunto;
        this.estilo = SimpleMarkerSymbol.Style.TRIANGLE;
        this.color = 0xFFFFFF00;
        this.tamano = 10;
    }

    public PuntoMonitoreoBuilder withSimbolo(SimpleMarkerSymbol.Style estilo, int color, int tamano)  {
        this.estilo = estilo;
        this.color = color;
        this.tamano = tamano;
        return this;
    }

    public PuntoMonitoreoBuilder withUbicacion(Double latitud, Double longitud)  {
        this.latitud = latitud;
        this.longitud = longitud;
        return this;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getTipoPunto() {
        return tipoPunto;
    }

    public void setTipoPunto(String tipoPunto) {
        this.tipoPunto = tipoPunto;
    }

    @Override
    public Punto build() {

        Punto punto = new Punto();
        punto.setTipoPunto(this.tipoPunto);
        punto.setColor(this.color);
        punto.setEstilo(this.estilo);
        punto.setLatitud(this.latitud);
        punto.setLongitud(this.longitud);
        punto.setTamano(this.tamano);
        punto.generaPunto();
        return punto;
    }

}
