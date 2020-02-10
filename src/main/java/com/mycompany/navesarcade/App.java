package com.mycompany.navesarcade;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
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
    short naveDirection = 0;
    
    short naveEnemigaPosX =0;
    short naveEnemigaPosY=0; 
    short naveDirectionEnemiga = 1;
    
    short balaPosX = -10;
    short balaPosY= -10; 
    short balaDirectionY = 0;       
    
    short vidasEnemigo = 30;       
    short vidasNave = 1;
    
    @Override
    public void start(Stage stage) {
        //pantalla
        var root = new Pane();
        var scene = new Scene(root,SCENE_WIDTH,SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();
        
        
          short stickPosY = (short)((SCENE_HEIGHT)/2);
          byte naveSpeed = 4;
          byte naveSpeedEnemiga = 1;
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

       //tomar posicion inicial del grupo
        Circle bala = new Circle ();
        bala.setCenterX(0);
        bala.setCenterY(0);
        bala.setRadius(2.5);
        bala.setFill(Color.YELLOWGREEN);
        root.getChildren().add(bala);
        //Desaparicion nave enemiga
        Rectangle naveEnemigaF = new Rectangle(0,0,45,35);
        naveEnemigaF.setFill(Color.TRANSPARENT);
        //grupo nave enemiga
        
        //nave enemiga Imagen
        Image naveEnemiga = new Image(getClass().getResourceAsStream("/imagenes/naveEnemiga.png"));
        ImageView imageViewNaveEnemiga = new ImageView(naveEnemiga);
        root.getChildren().add(imageViewNaveEnemiga);
        
        Group enemigo = new Group();
        enemigo.getChildren().add(naveEnemigaF);
        enemigo.getChildren().add(imageViewNaveEnemiga);
        root.getChildren().add(enemigo);
        
//        Rectangle naveEnemigaI = new Rectangle(0,0,45,35);
//        naveEnemigaI.setFill(Color.BLACK);
//        root.getChildren().add(naveEnemigaI);
//posicion inicial de la bala temporal
        
// puntiacion del juego
        // panel principal
        HBox paneScores = new HBox();
        paneScores.setTranslateY(20);
        paneScores.setMinWidth(SCENE_WIDTH);
        paneScores.setAlignment(Pos.CENTER);
        paneScores.setSpacing(100);
        root.getChildren().add(paneScores);
        
        // layout vidas nave enemiga
        HBox paneCurrentScoreE = new HBox();
        paneCurrentScoreE.setSpacing(10);
        paneScores.getChildren().add(paneCurrentScoreE);
         // layout vidas nave 
        HBox paneCurrentScore = new HBox();
        paneCurrentScore.setSpacing(10);
        paneScores.getChildren().add(paneCurrentScore);
        
        // etiquetas para vidas
        Text textTitleScore = new Text("vidas nave enemiga:");


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
                        balaDirectionY = -1;
                        balaPosX =  (short) (navePosX + 13);
                        balaPosY = navePosY ;
                        break;
                        // hacer q en el momento del disparo la bala tenga la posicion de la nave
                }
                
            }
        });
        //para que lo sume de uno en uno y no seguido
        scene.setOnKeyReleased((KeyEvent keyEvent) -> {
        naveDirection = 0;
//        balaDirectionY = 0;
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
                    balaPosY += balaSpeed * balaDirectionY;
                    if(balaPosY <= 0 || balaPosY >= SCENE_HEIGHT) {
                        balaDirectionY = 0;
                    }
                    if(balaPosY <= 0) {
                        balaDirectionY = 0;
                        balaPosY = 0;
                    } else if (balaPosY >= SCENE_HEIGHT) {
                        balaDirectionY = 0;
                        balaPosY = (short)(SCENE_HEIGHT);
                    }
                    
                    // ANIMACIÓN DE LA NAVE ENEMIGA
                    //nave.setLayoutY(navePosX);
                    naveEnemigaPosX += naveSpeedEnemiga * naveDirectionEnemiga;
                    if(naveEnemigaPosX <= 0 || naveEnemigaPosX >= SCENE_WIDTH) {
                        naveDirectionEnemiga = 0;
                    }
                    if(naveEnemigaPosX <= 0) {
                        naveDirectionEnemiga = 0;
                        naveEnemigaPosX = 0;
                    } else if (naveEnemigaPosX >= SCENE_WIDTH) {
                        naveDirectionEnemiga = 0;
                        naveEnemigaPosX = (short)(SCENE_WIDTH);
                    }
                    
                //MOVIMIENTO DE LA NAVE 
                nave.setTranslateX(navePosX);
                nave.setTranslateY(navePosY);
                    
                //MOVIMIENTO DE LA NAVE ENEMIGA
                enemigo.setTranslateX(naveEnemigaPosX);
                enemigo.setTranslateY(naveEnemigaPosY);
                    
                //Poicion de la bala antes de ser disparada
                    bala.setTranslateY(balaPosY);
                    bala.setTranslateX(balaPosX);
                //Poicion de la bala cuando es disparada debo hacer q se mueba de forma independiante a la nave
                   if(balaPosY <= 0){
                    balaPosY  = 500;
                   }
                //movimiento de la nave eneemiga por pantalla
                 if(naveEnemigaPosX >= 640){
                    naveEnemigaPosX = 0;
                    naveDirectionEnemiga = 1;
                    naveEnemigaPosY = (short) (naveEnemigaPosY + 30);
                   }
                
               Shape.intersect(naveEnemigaF,bala );
        
               Shape shapeColision = Shape.intersect(naveEnemigaF, bala);
         
               boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();
               
                
                    if(colisionVacia == false ){
                    vidasEnemigo = (short) (vidasEnemigo -1);
                    }
                    System.out.print(vidasEnemigo);
                    
                    if(vidasEnemigo == 0){
                    naveEnemigaPosX = -20;
                    naveEnemigaPosY = -20;
                    naveDirectionEnemiga = 0;
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