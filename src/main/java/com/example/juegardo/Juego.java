package com.example.juegardo;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Juego extends BorderPane {

    public Juego(){
        Label scoreUno = new Label();
        Label scoreDos = new Label();
        Rectangle paredIzquierda = new Rectangle();
        Rectangle paredDerecha = new Rectangle();
        Rectangle paredSuperior = new Rectangle();
        Rectangle paredInferior = new Rectangle();
        Rectangle palaIzquierda = new Rectangle();
        Rectangle palaDerecha = new Rectangle();
        Circle bola = new Circle();
        StackPane pista = new StackPane();
        DropShadow ds = new DropShadow();
        DropShadow ds2 = new DropShadow();
        DropShadow dsMuro = new DropShadow();

        JuegoController controlador = new JuegoController(
                paredIzquierda, paredDerecha, paredSuperior, paredInferior,
                bola, palaIzquierda, palaDerecha, pista, ds2);

        paredIzquierda.setFill(Color.rgb(30,30,30));
        paredDerecha.setFill(Color.rgb(30,30,30));
        paredSuperior.setFill(Color.RED);
        paredInferior.setFill(Color.RED);

        bola.setFill(Color.BLUE);

        palaIzquierda.setFill(Color.WHITE);
        palaDerecha.setFill(Color.WHITE);

        pista.getChildren().addAll(
                paredIzquierda, paredDerecha, paredSuperior, paredInferior, bola, palaIzquierda, palaDerecha,
                scoreUno, scoreDos);
        StackPane.setAlignment(paredIzquierda, Pos.CENTER_LEFT);
        StackPane.setAlignment(paredDerecha, Pos.CENTER_RIGHT);
        StackPane.setAlignment(paredSuperior, Pos.TOP_CENTER);
        StackPane.setAlignment(paredInferior, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(bola, Pos.CENTER);
        StackPane.setAlignment(palaIzquierda, Pos.CENTER_LEFT);
        StackPane.setAlignment(palaDerecha, Pos.CENTER_RIGHT);
        StackPane.setAlignment(scoreUno, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(scoreDos, Pos.BOTTOM_RIGHT);

        ds.setColor(Color.WHITE);
        ds2.setColor(Color.BLUE);
        dsMuro.setColor(Color.RED);

        palaIzquierda.translateXProperty().bind(paredIzquierda.widthProperty());
        palaDerecha.translateXProperty().bind(paredDerecha.widthProperty().multiply(-1));

        bola.setEffect(ds2);
        palaIzquierda.setEffect(ds);
        palaDerecha.setEffect(ds);
        paredSuperior.setEffect(dsMuro);
        paredInferior.setEffect(dsMuro);

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

       // scoreUno.widthProperty().bind(paredInferior.widthProperty().divide(5));
      //  scoreUno.heightProperty().bind(paredInferior.heightProperty());
        scoreUno.setText("nwjefjwernfjwerngwoi");
        scoreUno.setTextFill(Color.WHITE);

       // scoreDos.widthProperty().bind(paredInferior.widthProperty().divide(5));
       // scoreDos.heightProperty().bind(paredInferior.heightProperty());
        scoreDos.setText("Hola K Ase");
        scoreDos.setTextFill(Color.WHITE);

        pista.setBackground(new Background(new BackgroundFill(Color.rgb(30,30,30),
                null, null)));

        this.setCenter(pista);
    }
}