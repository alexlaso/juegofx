package com.example.juegardo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class JuegoController {
    private Rectangle paredIzquierda, paredDerecha, paredSuperior, paredInferior, palaIzquierda, palaDerecha;
    private Circle bola;
    private StackPane pista;
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
    }}

    private void colisionLados(){
        if (bola.getBoundsInParent().intersects(paredDerecha.getBoundsInParent())|
        bola.getBoundsInParent().intersects(paredIzquierda.getBoundsInParent())){
            bola.setTranslateX(0);
            bola.setTranslateY(0);
            palaIzquierda.setTranslateY(0);
            palaDerecha.setTranslateY(0);
            movBolaX=1.5;
         //   movBolaY=1.5;
    }}

    private void colisionPalas(){
        if (bola.getBoundsInParent().intersects(palaIzquierda.getBoundsInParent())|
        bola.getBoundsInParent().intersects(palaDerecha.getBoundsInParent())){
            if (movBolaX<20&movBolaX>-20){
            movBolaX=-movBolaX*1.1;
        }else{
                movBolaX=-movBolaX;
            }
        }
    }

    private void colisionPalasMuros(){
        if (palaIzquierda.getBoundsInParent().intersects(paredSuperior.getBoundsInParent())|
                palaIzquierda.getBoundsInParent().intersects(paredInferior.getBoundsInParent())){
            movPalaIzq=0;
        }
        if (palaDerecha.getBoundsInParent().intersects(paredSuperior.getBoundsInParent())|
                palaDerecha.getBoundsInParent().intersects(paredInferior.getBoundsInParent())){
            movPalaDer=0;
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

    private void inicializarControles(){
        pista.setOnKeyPressed(e->{
            animacion.play();
        });
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
                case W:movPalaIzq=0;
                case S:movPalaIzq=0;
                    break;
                case UP:movPalaDer=0;
                case DOWN:movPalaDer=0;
                    break;
            }
        });
    }

}
