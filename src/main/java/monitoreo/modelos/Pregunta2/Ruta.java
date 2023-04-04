package monitoreo.modelos.Pregunta2;


import com.esri.arcgisruntime.mapping.view.Graphic;
import monitoreo.modelos.interfaces.IGrafico;
import monitoreo.modelos.interfaces.ITipoServicio;

import java.util.ArrayList;
import java.util.List;

public class Ruta implements IGrafico {

    private String nombre;
    private final List<IGrafico> graficos = new ArrayList<>();
    private final Graphic poligono;

    public Ruta(String nombre, int color,  List<CoordenadasDeMonitoreo> coordenadasRuta) {
        this.nombre = nombre;
        this.poligono = FacadeRutaMonitoreo.getInstance().agregarRuta(color, 3.0f, coordenadasRuta);
    }

    public Graphic getGrafico(){
        return getPoligono();
    }

    public Graphic getPoligono(){
        return this.poligono;
    }

    @Override
    public void mover(Integer x, Integer Y) {

    }

    @Override
    public void mostrar() {
        System.out.println("[Ruta] " + nombre);
        for (IGrafico grafico : graficos) grafico.mostrar();
    }

    @Override
    public void agregar(IGrafico grafico) {
        this.graficos.add(grafico);
    }

    @Override
    public void eliminar(IGrafico grafico) {
        this.graficos.remove(grafico);
    }
}