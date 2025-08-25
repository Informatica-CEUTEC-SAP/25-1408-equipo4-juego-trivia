package com.juego.application.equipo4juegotrivia;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorDeGestiones {

    private final Stage stage;

    // Constructor recibe el Stage principal
    public ControladorDeGestiones(Stage stage) {
        this.stage = stage;
    }

    /**
     * Cambia la escena actual a la vista FXML indicada.
     *
     * @param fxml el nombre del archivo FXML (ubicado en resources/com/juego/application/equipo4juegotrivia/)
     * @param width ancho de la nueva escena
     * @param height alto de la nueva escena
     */
    public void cambiarEscena(String fxml, double width, double height) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource(fxml));
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
