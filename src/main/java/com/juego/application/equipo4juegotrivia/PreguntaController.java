package com.juego.application.equipo4juegotrivia;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class PreguntaController implements Initializable {

    @FXML private Label preguntaLabel;
    @FXML private VBox opcionesContainer;
    @FXML private Button siguienteBtn;
    @FXML private Label tiempoLabel;
    @FXML private ProgressBar tiempoProgress;
    @FXML private Label puntuacionLabel;

    private Pregunta[] preguntas;
    private int preguntaActual;
    private Timeline timeline;
    private final int TIEMPO_POR_PREGUNTA = 15;
    private int tiempoRestante;
    // Temporizador de 15 segundos por pregunta

    private int puntuacion=0;
    private int respuestasCorrectas=0;
    private int respuestasIncorrectas=0;
  // Inicializar puntuacion en cero

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarPreguntas();
        actualizarPuntuacion();
        mostrarSiguientePregunta();
    }
    private void actualizarPuntuacion(){
        puntuacionLabel.setText("Puntuacion: " + puntuacion);
    }

    private void inicializarPreguntas() {
        preguntas = new Pregunta[]{
                new Pregunta("¿Cuál es la capital de Francia?",
                        new String[]{"Londres", "Berlín", "París", "Madrid"}, 2),
                new Pregunta("¿En qué año llegó el hombre a la luna?",
                        new String[]{"1969", "1972", "1958", "1980"}, 0),
                new Pregunta("¿Qué elemento químico tiene el símbolo 'O'?",
                        new String[]{"Oro", "Oxígeno", "Osmio", "Oganesón"}, 1),
                new Pregunta("¿Quién escribió 'Cien años de soledad'?",
                        new String[]{"Gabriel García Márquez", "Mario Vargas Llosa", "Julio Cortázar", "Pablo Neruda"}, 0),
                new Pregunta("¿Cuál es el río más largo del mundo?",
                        new String[]{"Nilo", "Amazonas", "Misisipi", "Yangtsé"}, 1)
        };
        preguntaActual = 0;
    }

    private void mostrarSiguientePregunta() {
        if (preguntaActual >= preguntas.length) {
            // No hay mas preguntas, volver al menu
            volverAlMenu();
            return;
        }

        Pregunta pregunta = preguntas[preguntaActual];
        preguntaLabel.setText(pregunta.getTextoPregunta());
        opcionesContainer.getChildren().clear();
        siguienteBtn.setVisible(false);

        String[] opciones = pregunta.getOpciones();
        for (int i = 0; i < opciones.length; i++) {
            Button botonOpcion = new Button(opciones[i]);
            int indiceRespuesta = i;
            botonOpcion.setOnAction(e -> verificarRespuesta(indiceRespuesta));
            botonOpcion.setPrefWidth(300);
            opcionesContainer.getChildren().add(botonOpcion);
        }

        iniciarTemporizador();
    }

    private void iniciarTemporizador() {
        if (timeline != null) {
            timeline.stop();
        }

        tiempoRestante = TIEMPO_POR_PREGUNTA;
        tiempoLabel.setText("Tiempo: " + tiempoRestante + "s");
        tiempoProgress.setProgress(1.0);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            tiempoRestante--;
            tiempoLabel.setText("Tiempo: " + tiempoRestante + "s");
            tiempoProgress.setProgress((double) tiempoRestante / TIEMPO_POR_PREGUNTA);

            if (tiempoRestante <= 0) {
                timeline.stop();
                siguientePregunta();
            }
        }));
        timeline.setCycleCount(TIEMPO_POR_PREGUNTA);
        timeline.play();
    }

    private void verificarRespuesta(int indiceSeleccionado) {
        timeline.stop();
        siguienteBtn.setVisible(true);
        // Aqui se puede añadir logica para marcar respuestas correctas/incorrectas

        Pregunta preguntaActualObj= preguntas[preguntaActual];
        Boolean esCorrecta= (indiceSeleccionado==preguntaActualObj.getRespuestaCorrecta());

        for (int i=0; i<opcionesContainer.getChildren().size();i++){
            Button boton =(Button) opcionesContainer.getChildren().get(i);

            if (i== preguntaActualObj.getRespuestaCorrecta()){
                boton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                //Se marca la respuesta correcta en verde

            } else if (i==indiceSeleccionado && !esCorrecta) {
                boton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;");
                //Si la respuesta es incorrecta se marca en rojo
            }else{
                boton.setStyle("-fx-background-color: #E0E0E0;");
                //Para opciones se marca gris
            }

            boton.setDisable (true);
            //Permite deshabilitar los botones despues de responder

        }
    if (esCorrecta){
        int puntosGanados = tiempoRestante*10;

        puntuacion += puntosGanados;
        respuestasCorrectas++;
    }else {
        respuestasIncorrectas++;
    }

    actualizarPuntuacion();

    }

    @FXML
    private void siguientePregunta() {
        preguntaActual++;
        mostrarSiguientePregunta();
    }

    private void mostrarResultadosFinales(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("resultados.fxml"));
            Parent root = loader.load();

            Resultadoscontroller controller = loader.getController();
            controller.setResultados(puntuacion,respuestasCorrectas,respuestasIncorrectas,preguntas.length);

            Stage stage = new Stage();
            stage.setScene(new Scene(root,800,600));
            stage.setTitle("Resultados - Trivia Game");
            stage.show();

            Stage currentStage = (Stage) preguntaLabel.getScene().getWindow();
            currentStage.close();
        }catch (Exception e){
            e.printStackTrace();
            volverAlMenu();
        }
    }

    private void volverAlMenu() {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("view.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Trivia Game");
            stage.show();

            Stage currentStage = (Stage) preguntaLabel.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   }


