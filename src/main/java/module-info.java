module com.juego.application.equipo4juegotrivia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.juego.application.equipo4juegotrivia to javafx.fxml;
    exports com.juego.application.equipo4juegotrivia;
}