package monitoreo.modelos.Pregunta4;

public class Test {

    public static void main(String[] args) {
        // SMP : -11.988315, -77.101524
        // SJL : -11.980413, -76.981046

        String aux = "12.0558S 077.0858W";

     //double value = Double.parseDouble(aux.replaceAll("[^0-9]", ""));
        String prueba = formatoCoordenada(aux);
        System.out.println(prueba);
        String arreglo[] = new String[2];

        arreglo = conversionCoordenada(prueba);

        double x,y;
            x = (-1)*Double.parseDouble(arreglo[0])/Math.pow(10,4);
            y = (-1)*Double.parseDouble(arreglo[1])/Math.pow(10,4);

        System.out.println(x);
        System.out.println(y);

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

}
