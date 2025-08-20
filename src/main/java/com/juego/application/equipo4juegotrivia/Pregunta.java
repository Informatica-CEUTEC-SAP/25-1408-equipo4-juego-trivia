package com.juego.application.equipo4juegotrivia;

public class Pregunta {
    private String textoPregunta;
    private String[] opciones;
    private int respuestaCorrecta;

    public Pregunta(String textoPregunta, String[] opciones, int respuestaCorrecta) {
        this.textoPregunta = textoPregunta;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public boolean validarRespuesta(int respuesta) {
        return respuesta == respuestaCorrecta;
    }
}
