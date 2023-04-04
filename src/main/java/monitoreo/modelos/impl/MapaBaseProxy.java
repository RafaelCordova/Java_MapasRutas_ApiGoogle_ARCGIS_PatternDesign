package monitoreo.modelos.impl;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;

import monitoreo.modelos.interfaces.IMapaBase;

public class MapaBaseProxy implements IMapaBase {

    private int tipoMapa = 0;
    private MapaBase mapaBase;

    public void setTipoMapa(int tipo)  { this.tipoMapa = tipo; }

    @Override
    public ArcGISMap getMapaBase() {
        
        ArcGISMap map = null;

        if (tipoMapa != 0  )   {

            mapaBase = new MapaBase(this.tipoMapa);
            map = mapaBase.getMapaBase();
        }
        else    {
            System.out.println("Creando el mapabase por defecto del Proxy...");
            map = new ArcGISMap(Basemap.createImagery());
        }

        return map;
    }
    
}
