package com.example.juegardo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class JuegoController {
    private final Rectangle paredIzquierda;
    private final Rectangle paredDerecha;
    private final Rectangle paredSuperior;
    private final Rectangle paredInferior;
    private final Rectangle palaIzquierda;
    private final Rectangle palaDerecha;
    private final Circle bola;
    private Label scoreUno, scoreDos;
    private int puntuacionUno, puntuacionDos;
    private final StackPane pista;
    private double movBolaY, movBolaX,
    movPalaIzq, movPalaDer;
    private Timeline animacion;

    public JuegoController(
            Rectangle paredIzquierda, Rectangle paredDerecha, Rectangle paredSuperior, Rectangle paredInferior,
            Circle bola, Rectangle palaIzquierda, Rectangle palaDerecha, StackPane pista) {
        this.paredIzquierda = paredIzquierda;
        this.paredDerecha = paredDerecha;
        this.paredSuperior = paredSuperior;
        this.paredInferior = paredInferior;
        this.bola = bola;
        this.palaIzquierda=palaIzquierda;
        this.palaDerecha=palaDerecha;
        this.pista=pista;
        this.movBolaY = 1.5;
        this.movBolaX = 1.5;

        puntuacionUno=0;
        puntuacionDos=0;

        inicializarJuego();
        inicializarControles();
    }

    private void inicializarJuego(){
        this.animacion = new Timeline(new KeyFrame(Duration.millis(17),t->{
            initMove();
            initWalls();
        }));
        animacion.setCycleCount(Animation.INDEFINITE);
    }

    private void initWalls(){
        colisionLados();
        colisionVertical();
        colisionPalas();
        colisionPalasMuros();
    }

    private void colisionVertical() {
        if (bola.getBoundsInParent().intersects(paredSuperior.getBoundsInParent())
        | bola.getBoundsInParent().intersects(paredInferior.getBoundsInParent())){
        movBolaY=-movBolaY*1.1;
            cambiaColor();
    }}

    private void colisionLados(){
        if (bola.getBoundsInParent().intersects(paredDerecha.getBoundsInParent())){
            resetearPosicion();
            scoreUno.setText("Puntuación: "+puntuacionUno);
            puntuacionUno=puntuacionUno+1;
    }if(bola.getBoundsInParent().intersects(paredIzquierda.getBoundsInParent())){
            resetearPosicion();
            scoreDos.setText("Puntuación: "+puntuacionDos);
            puntuacionDos=puntuacionDos+1;
        }
    }

    private void colisionPalas(){
        if (bola.getBoundsInParent().intersects(palaIzquierda.getBoundsInParent())|
        bola.getBoundsInParent().intersects(palaDerecha.getBoundsInParent())){
            if (movBolaX<20&movBolaX>-20){
            movBolaX=-movBolaX*1.1;
            }else{
                movBolaX=-movBolaX;
            }
            cambiaColor();
        }
    }

    private void resetearPosicion(){
        bola.setTranslateX(0);
        bola.setTranslateY(0);
        palaIzquierda.setTranslateY(0);
        palaDerecha.setTranslateY(0);
        movBolaX=1.5;
        movBolaY=1.5;
    }
    private void colisionPalasMuros(){
        if (palaIzquierda.getBoundsInParent().intersects(paredSuperior.getBoundsInParent())){
            palaIzquierda.setTranslateY(paredSuperior.getHeight());
        }
        if (palaIzquierda.getBoundsInParent().intersects(paredInferior.getBoundsInParent())){
            palaIzquierda.setTranslateY(paredInferior.getY()-palaIzquierda.getHeight());
        }
    }

    private void initMove(){
        moverBola();
        moverPalaIzq();
        moverPalaDer();
    }

    private void moverBola(){
        bola.setTranslateY(bola.getTranslateY()+movBolaY);
        bola.setTranslateX(bola.getTranslateX()+movBolaX);
       // animacion.setRate(2);

    }

    private void moverPalaIzq(){
        controles();
        palaIzquierda.setTranslateY(palaIzquierda.getTranslateY()+movPalaIzq);
        // animacion.setRate(2);

    }

    private void moverPalaDer(){
        controles();
        palaDerecha.setTranslateY(palaDerecha.getTranslateY()+movPalaDer);
        // animacion.setRate(2);
    }

    private void cambiaColor(){
        bola.setFill(Color.rgb((int)Math.floor(Math.random()*256),
                (int)Math.floor(Math.random()*256),
                (int)Math.floor(Math.random()*256)));
    }

    private void inicializarControles(){
        pista.setOnKeyPressed(e -> animacion.play());
        pista.setFocusTraversable(true);
    }

    private void controles(){
        pista.setOnKeyPressed(e->{
            switch (e.getCode()){
                case W:movPalaIzq=-4;
                    break;
                case S:movPalaIzq=4;
                    break;
                case UP:movPalaDer=-4;
                    break;
                case DOWN:movPalaDer=4;
                    break;
            }
        });
        pista.setOnKeyReleased(e->{
            switch (e.getCode()){
                case W:
                case S:movPalaIzq=0;
                    break;
                case UP:
                case DOWN:movPalaDer=0;
                    break;
            }
        });
    }



}
