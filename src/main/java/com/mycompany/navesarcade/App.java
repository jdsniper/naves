package com.mycompany.navesarcade;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    final short SCENE_HEIGHT=480 ;
    final short SCENE_WIDTH=640 ;
    short navePosX = (short)((SCENE_WIDTH)/2);
    short navePosY=420; 
    short balaPosX = (short)((SCENE_WIDTH)/2);
    short balaPosY=navePosY; 
    byte naveDirection = 0;
    byte balaDirection = 0;
    
    @Override
    public void start(Stage stage) {
        //pantalla
        var root = new Pane();
        var scene = new Scene(root,SCENE_WIDTH,SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();
        
        
          short stickPosY = (short)((SCENE_HEIGHT)/2);
          byte naveSpeed = 4;
          byte balaSpeed = 4;
          
        //fondo
        Image fondo = new Image(getClass().getResourceAsStream("/imagenes/fondo.jpg"));
        ImageView imageViewFondo = new ImageView(fondo);
        root.getChildren().add(imageViewFondo);

        //nave x y L A
        
        //tomar posicion inicial del grupo
        Rectangle naveBase = new Rectangle(0,0,25,7);
        Rectangle naveCentral = new Rectangle(5,-5,15,7);
        Rectangle naveCabina = new Rectangle(10,-10,5,7);
        //colorear nave
        naveBase.setFill(Color.WHITE);
        naveCentral.setFill(Color.WHITE);
        naveCabina.setFill(Color.WHITE);
                    //grupo nave
        Group nave = new Group();
        nave.getChildren().add(naveBase);
        nave.getChildren().add(naveCentral);
        nave.getChildren().add(naveCabina);
        //mostrar el grupo de la nave
        root.getChildren().add(nave);
        //Disparo de la nave
        //tomar posicion inicial del grupo
        Circle bala = new Circle ();
        bala.setCenterX(0);
        bala.setCenterY(0);
        bala.setRadius(2.5);
        bala.setFill(Color.YELLOWGREEN);
        root.getChildren().add(bala);
        

//posicion inicial de la bala temporal
        
        
        // CONTROL DEL TECLADO
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                switch(keyEvent.getCode()) {
                    case RIGHT:
                        naveDirection = 1;
                        break;
                    case LEFT:
                        naveDirection = -1;
                        break;
                    //disparo de la bala
                    case SPACE:
                        balaDirection = -1;
                        break;
                }
                System.out.println(balaDirection);
            }
        });
        //para que lo sume de uno en uno y no seguido
        scene.setOnKeyReleased((KeyEvent keyEvent) -> {
        naveDirection = 0;
        });
        
        
        Timeline timeline = new Timeline(
            // 0.017 ~= 60 FPS
            new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {

                    // ANIMACIÓN DE LA NAVE
                    //nave.setLayoutY(navePosX);
                    navePosX += naveSpeed * naveDirection;
                    if(navePosX <= 0 || navePosX >= SCENE_WIDTH) {
                        naveDirection = 0;
                    }
                    if(navePosX <= 0) {
                        naveDirection = 0;
                        navePosX = 0;
                    } else if (navePosX >= SCENE_WIDTH) {
                        naveDirection = 0;
                        navePosX = (short)(SCENE_WIDTH);
                    }
                    // Animacion de la bala
                    balaPosY += balaSpeed * balaDirection;
                    if(balaPosY <= 0 || balaPosY >= SCENE_HEIGHT) {
                        balaDirection = 0;
                    }
                    if(balaPosY <= 0) {
                        balaDirection = 0;
                        balaPosY = 0;
                    } else if (balaPosY >= SCENE_HEIGHT) {
                        balaDirection = 0;
                        balaPosY = (short)(SCENE_HEIGHT);
                    }
                   // System.out.println(navePosX);
        
                    //MOVIMIENTO DE LA NAVE 
                    nave.setTranslateX(navePosX);
                    nave.setTranslateY(navePosY);
                    //Poicion de la bala antes de ser disparada
                    bala.setTranslateX(navePosX);
                    
                    //Poicion de la bala cuando es disparada debo hacer q se mueba de forma independiante a la nave
                    
                   if(balaPosY <= 0){
                    balaPosY  =  navePosY;
                   }
                }
                
            })
            
        );
        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play(); 
        
    }   

    public static void main(String[] args) {
        launch();
    }

}