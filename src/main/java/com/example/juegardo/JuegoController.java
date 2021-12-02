package com.example.juegardo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class JuegoController {
    private final Rectangle paredIzquierda;
    private final Rectangle paredDerecha;
    private final Rectangle paredSuperior;
    private final Rectangle paredInferior;
    private final Rectangle palaIzquierda;
    private final Rectangle palaDerecha;
    private final Circle bola;
    private Text scoreUno, scoreDos, ganador;
    private final StackPane pista;
    private double movBolaY, movBolaX,
    movPalaIzq, movPalaDer;
    private Timeline animacion;
    private Color ultimoColor;
    private DropShadow ds2;
    private IntegerProperty puntuacionUno, puntuacionDos;

    public JuegoController(
            Rectangle paredIzquierda, Rectangle paredDerecha, Rectangle paredSuperior, Rectangle paredInferior,
            Circle bola, Rectangle palaIzquierda, Rectangle palaDerecha, StackPane pista, DropShadow ds2,
            Text scoreUno, Text scoreDos, Text ganador) {
        this.paredIzquierda = paredIzquierda;
        this.paredDerecha = paredDerecha;
        this.paredSuperior = paredSuperior;
        this.paredInferior = paredInferior;
        this.bola = bola;
        this.palaIzquierda=palaIzquierda;
        this.palaDerecha=palaDerecha;
        this.pista=pista;
        this.ds2=ds2;
        this.movBolaY = 1.5;
        this.movBolaX = 1.5;
        this.scoreUno= scoreUno;
        this.scoreDos = scoreDos;
        this.ganador = ganador;

        puntuacionUno = new SimpleIntegerProperty(0);
        puntuacionDos = new SimpleIntegerProperty(0);

        inicializarJuego();
        inicializarControles();
        puntuacionesFinales();
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
            bola.setFill(ultimoColor);
            ds2.setColor(ultimoColor);
            bola.setEffect(ds2);
    }}

    private void colisionLados(){
        if (bola.getBoundsInParent().intersects(paredDerecha.getBoundsInParent())){
            resetearPosicion();
            puntuacionUno.setValue(puntuacionUno.getValue()+1);
            scoreUno.textProperty().bind(puntuacionUno.asString("Jugador 1: %d"));
    }if(bola.getBoundsInParent().intersects(paredIzquierda.getBoundsInParent())){
            resetearPosicion();
            puntuacionDos.setValue(puntuacionDos.getValue()+1);
            scoreDos.textProperty().bind(puntuacionDos.asString("Jugador 2: %d"));
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
            bola.setFill(ultimoColor);
            ds2.setColor(ultimoColor);
            bola.setEffect(ds2);
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
            palaIzquierda.setTranslateY(15+palaIzquierda.getHeight()/2+paredSuperior.getHeight()*2-pista.getHeight()/2);
        }
        if (palaIzquierda.getBoundsInParent().intersects(paredInferior.getBoundsInParent())){
            palaIzquierda.setTranslateY(-15-palaIzquierda.getHeight()/2-paredInferior.getHeight()*2+pista.getHeight()/2);
        }
        if (palaDerecha.getBoundsInParent().intersects(paredSuperior.getBoundsInParent())){
            palaDerecha.setTranslateY(15+palaDerecha.getHeight()/2+paredSuperior.getHeight()*2-pista.getHeight()/2);
        }
        if (palaDerecha.getBoundsInParent().intersects(paredInferior.getBoundsInParent())){
            palaDerecha.setTranslateY(-15-palaDerecha.getHeight()/2-paredInferior.getHeight()*2+pista.getHeight()/2);

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
        ultimoColor=(Color.rgb((int)Math.floor(Math.random()*256),
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

    private void puntuacionesFinales(){
        if(puntuacionUno.equals(7)){
            animacion.stop();
            ganador.setText("HA GANADO EL JUGADOR 1");
        }
        if(puntuacionDos.equals(7)){
            animacion.stop();
            ganador.setText("HA GANADO EL JUGADOR 2");
        }
    }

}
