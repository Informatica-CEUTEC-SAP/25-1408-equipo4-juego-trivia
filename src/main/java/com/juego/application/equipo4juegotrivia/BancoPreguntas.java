package com.juego.application.equipo4juegotrivia;

public class BancoPreguntas {
    private Pregunta[] preguntas;
    private int preguntaActual = 0;

    // Añadir después de la definición de la clase
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
    }

    private Pregunta obtenerSiguientePregunta() {
        if (preguntaActual < preguntas.length) {
            return preguntas[preguntaActual++];
        }
        return null;
    }
}
