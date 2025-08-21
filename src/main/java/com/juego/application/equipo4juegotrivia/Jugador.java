package com.juego.application.equipo4juegotrivia;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Jugador implements Initializable {

    @FXML private Label puntuacionFinalLabel;
    @FXML private Label respuestasCorrectasLabel;
    @FXML private Label respuestasIncorrectasLabel;
    @FXML private Label porcentajeAciertosLabel;
    @FXML private VBox estrellasContainer;
    @FXML private Button volverMenuBtn;

    private int puntuacion;
    private int respuestasCorrectas;
    private int respuestasIncorrectas;
    private int totalPreguntas;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarResultados();

        volverMenuBtn.setOnAction(e -> {
            try {
                Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("view.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 800, 600));
                stage.setTitle("Trivia Game");
                stage.show();

                Stage currentStage = (Stage) volverMenuBtn.getScene().getWindow();
                currentStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void setResultados(int puntuacion, int correctas, int incorrectas, int total) {
        this.puntuacion = puntuacion;
        this.respuestasCorrectas = correctas;
        this.respuestasIncorrectas = incorrectas;
        this.totalPreguntas = total;


        mostrarResultados();
    }

    private void mostrarResultados() {
        // Asegurarse de que los valores se están recibiendo correctamente
        System.out.println("Debug - Puntuación: " + puntuacion);
        System.out.println("Debug - Correctas: " + respuestasCorrectas);
        System.out.println("Debug - Incorrectas: " + respuestasIncorrectas);
        System.out.println("Debug - Total: " + totalPreguntas);

        puntuacionFinalLabel.setText("Puntuación Final: " + puntuacion);
        respuestasCorrectasLabel.setText("Respuestas Correctas: " + respuestasCorrectas + "/" + totalPreguntas);
        respuestasIncorrectasLabel.setText("Respuestas Incorrectas: " + respuestasIncorrectas + "/" + totalPreguntas); // ✅ Mostrar también el total

        double porcentaje = totalPreguntas > 0 ? (double) respuestasCorrectas / totalPreguntas * 100 : 0;
        porcentajeAciertosLabel.setText(String.format("Porcentaje de aciertos: %.1f%%", porcentaje));
    }

}