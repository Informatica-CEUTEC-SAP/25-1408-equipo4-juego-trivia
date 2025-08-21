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

public class Resultadoscontroller implements Initializable {

    @FXML
    private Label puntuacionFinalLabel;
    @FXML
    private Label respuestasCorrectasLabel;
    @FXML
    private Label respuestasIncorrectasLabel;
    @FXML
    private Label porcentajeAciertosLabel;
    @FXML
    private VBox estrellasContainer;
    @FXML
    private Button volverMenuBtn;

    private int puntuacion;
    private int respuestasCorrectas;
    private int respuestasIncorrectas;
    private int totalPreguntas;

    public void setResultados(int puntuacion, int correctas, int incorrectas, int total) {
        this.puntuacion = puntuacion;
        this.respuestasCorrectas = correctas;
        this.respuestasIncorrectas = incorrectas;
        this.totalPreguntas = total;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarResultados();

        volverMenuBtn.setOnAction(e ->{
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

    private void mostrarResultados() {
        puntuacionFinalLabel.setText("Puntuación Final: " + puntuacion);
        respuestasCorrectasLabel.setText("Respuestas Correctas: " + respuestasCorrectas + "/" + totalPreguntas);
        respuestasIncorrectasLabel.setText("Respuestas Incorrectas: " + respuestasIncorrectas);


        double porcentaje = (double) respuestasCorrectas / totalPreguntas * 100;
        porcentajeAciertosLabel.setText(String.format("Porcentaje de aciertos: %.1f%%", porcentaje));

        // Mostrar estrellas según el rendimiento
        mostrarEstrellas(porcentaje);
    }

    private void mostrarEstrellas(double porcentaje) {
        int estrellas = 0;

        if (porcentaje >= 80) estrellas = 3;
        else if (porcentaje >= 60) estrellas = 2;
        else if (porcentaje >= 40) estrellas = 1;

        for (int i = 0; i < estrellas; i++) {
            Label estrella = new Label("★");
            estrella.setStyle("-fx-text-fill: gold; -fx-font-size: 24px;");
            estrellasContainer.getChildren().add(estrella);


        }
    }
}