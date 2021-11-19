package com.example.juegardo;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Juego extends BorderPane {
    private Rectangle paredIzquierda, paredDerecha, paredSuperior, paredInferior,
            palaIzquierda, palaDerecha;
    private Circle bola;
    private StackPane pista;
    private JuegoController controlador;

    public Juego(){
        this.paredIzquierda=new Rectangle();
        this.paredDerecha=new Rectangle();
        this.paredSuperior=new Rectangle();
        this.paredInferior=new Rectangle();
        this.palaIzquierda = new Rectangle();
        this.palaDerecha = new Rectangle();
        this.bola =new Circle();
        this.pista=new StackPane();
        this.controlador = new JuegoController(
                paredIzquierda, paredDerecha, paredSuperior, paredInferior,
                bola, palaIzquierda, palaDerecha, pista);

        paredIzquierda.setFill(Color.BLACK);
        paredDerecha.setFill(Color.BLACK);
        paredSuperior.setFill(Color.RED);
        paredInferior.setFill(Color.RED);

        bola.setFill(Color.BLUE);

        palaIzquierda.setFill(Color.WHITE);
        palaDerecha.setFill(Color.WHITE);

        pista.getChildren().addAll(
                paredIzquierda, paredDerecha, paredSuperior, paredInferior, bola, palaIzquierda, palaDerecha);
        pista.setAlignment(paredIzquierda, Pos.CENTER_LEFT);
        pista.setAlignment(paredDerecha, Pos.CENTER_RIGHT);
        pista.setAlignment(paredSuperior, Pos.TOP_CENTER);
        pista.setAlignment(paredInferior, Pos.BOTTOM_CENTER);
        pista.setAlignment(bola, Pos.CENTER);
        pista.setAlignment(palaIzquierda, Pos.CENTER_LEFT);
        pista.setAlignment(palaDerecha, Pos.CENTER_RIGHT);

        palaIzquierda.translateXProperty().bind(paredIzquierda.widthProperty());
        palaDerecha.translateXProperty().bind(paredDerecha.widthProperty().multiply(-1));

        paredIzquierda.heightProperty().bind(pista.heightProperty());
        paredIzquierda.widthProperty().bind(pista.widthProperty().divide(20));

        paredDerecha.heightProperty().bind(pista.heightProperty());
        paredDerecha.widthProperty().bind(pista.widthProperty().divide(20));

        paredSuperior.heightProperty().bind(pista.heightProperty().divide(20));
        paredSuperior.widthProperty().bind(pista.widthProperty());

        paredInferior.heightProperty().bind(pista.heightProperty().divide(20));
        paredInferior.widthProperty().bind(pista.widthProperty());

        bola.radiusProperty().bind(paredIzquierda.widthProperty().divide(3));

        palaIzquierda.heightProperty().bind(paredIzquierda.widthProperty().multiply(3.25));
        palaIzquierda.widthProperty().bind(paredIzquierda.widthProperty().divide(1.75));

        palaDerecha.heightProperty().bind(paredIzquierda.widthProperty().multiply(3.25));
        palaDerecha.widthProperty().bind(paredIzquierda.widthProperty().divide(1.75));

        pista.setBackground(new Background(new BackgroundFill(Color.rgb(30,30,30), null, null)));

        this.setCenter(pista);
    }
}