package monitoreo.modelos;


import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FachadaMapa {

    private Stage stage;
    private StackPane stackPane;
    private GraphicsOverlay graphicsOverlay;
    private Mapa mapa;
    
    public FachadaMapa(Stage stage) {

        this.stage = stage;

        // set the title and size of the stage and show it
        this.stage.setTitle("Sistema de Monitoreo de Vehiculos");
        this.stage.setWidth(800);
        this.stage.setHeight(700);
        //this.stage.show();

        // create a JavaFX scene with a stack pane as the root node and add it to the scene
        this.stackPane = new StackPane();
        //Scene scene = new Scene(stackPane);
        //stage.setScene(scene);

        this.graphicsOverlay = new GraphicsOverlay();

        this.mapa = new Mapa();
        this.stackPane.getChildren().add(mapa.getMapView());

    }

    public void mostrarCoordenadas()  {

        mapa.imprimeCoordenadasActual();
    }

    public void addGraphicsOverlay(Graphic graphic) {
        this.graphicsOverlay.getGraphics().add(graphic);

    }

    public void stackAddMapView()   {
        this.mapa.getMapView().getGraphicsOverlays().add(this.graphicsOverlay);
        //this.stackPane.getChildren().add(mapaBase.getMapView());
    }

    public StackPane getStackPane() {
        return this.stackPane;
    }

    public Mapa getMapa()   {
        return this.mapa;
    }

    public GraphicsOverlay getGraphicsOverlay() {
        return this.graphicsOverlay;
    }

    public void mostrarVentana()   {

        // Muestra la ventana
        Scene scene = new Scene(this.stackPane);
        this.stage.setScene(scene);
        this.stage.show();        
    }
}