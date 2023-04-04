package monitoreo.modelos.Pregunta4;

import monitoreo.modelos.impl.Punto;

import java.util.*;

public class CoordenadasCapturadas {

public static List<Punto> coordenadasCapturadas = new LinkedList<Punto>();

    public void mostrarLista(){
            for(int i=0 ;i<coordenadasCapturadas.size() ; i++){
                System.out.println(coordenadasCapturadas.get(i).getLatitud()+"---" +
                        "--"+coordenadasCapturadas.get(i).getLongitud());
                                                        }
                            }

    public void mostrarUltimaCoordenada(){
        System.out.println("ULTIMA COORDENADA CLICKEADA");
            System.out.println(coordenadasCapturadas.get(coordenadasCapturadas.size()-1).getLatitud()+"---" +
                    "--"+coordenadasCapturadas.get(coordenadasCapturadas.size()-1).getLongitud());
                                        }

    //METODO PARA CALCULAR LA DISTANCIA
    public static double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
        //double radioTierra = 3958.75;//en millas
        double radioTierra = 6371;//en kilómetros
        double dLat = (Math.toRadians(lat2 - lat1));
        double dLng = (Math.toRadians(lng2 - lng1));

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;

        return distancia;
                                                                                        }
                             }
