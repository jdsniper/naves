package com.mycompany.navesarcade;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    final short SCENE_HEIGHT=480 ;
    final short SCENE_WIDTH=640 ;
    short navePosX = (short)((SCENE_WIDTH)/2);
    short navePosY=50;        
    
    @Override
    public void start(Stage stage) {
        //pantalla
        var root = new Pane();
        var scene = new Scene(root,SCENE_WIDTH,SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();
        
        
          short stickPosY = (short)((SCENE_HEIGHT)/2);
          byte naveSpeed = 4;
          byte naveDirection = 0;
//
//         // DIBUJO DE LA PALA
//        Rectangle rectStick = new Rectangle();
//        rectStick.setWidth(10);
//        rectStick.setHeight(stickHeight);
//        rectStick.setX(SCENE_WIDTH - 40);
//        rectStick.setY(stickPosY);
//        rectStick.setFill(Color.WHITE);        
//        root.getChildren().add(rectStick);

        //fondo
        Image fondo = new Image(getClass().getResourceAsStream("/imagenes/fondo.jpg"));
        ImageView imageViewFondo = new ImageView(fondo);
        root.getChildren().add(imageViewFondo);

        //nave x y L A
        Rectangle naveBase = new Rectangle(navePosX,navePosY,25,7);
        Rectangle naveCentral = new Rectangle(navePosX+5,navePosY-5,15,7);
        Rectangle naveCabina = new Rectangle(navePosX+10,navePosY-10,5,7);
        //colorear nave
        naveBase.setFill(Color.WHITE);
        naveCentral.setFill(Color.WHITE);
        naveCabina.setFill(Color.WHITE);
        //grupo nave
        Group nave = new Group();
        root.getChildren().add(naveBase);
        root.getChildren().add(naveCentral);
        root.getChildren().add(naveCabina);
        //MOVIMIENTO DE LA NAVE 
        nave.setLayoutX(navePosX);
        nave.setLayoutY(navePosY);
        //mostrar el grupo de la nave
        root.getChildren().add(nave);
        
        //dar movimientom colocarla en su sitio hacer q dispare
         // CONTROL DEL TECLADO
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            public void handle(final KeyEvent keyEvent) {
//                switch(keyEvent.getCode()) {
//                    case UP:
//                        naveDirection = -1;
//                        break;
//                    case DOWN:
//                        naveDirection = 1;
//                        break;
//                }                
//            }
//        });
        // ANIMACIÓN DE LA NAVE
                    nave.setLayoutY(navePosX);
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

    
     
    }   

    public static void main(String[] args) {
        launch();
    }

}