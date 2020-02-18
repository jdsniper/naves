package com.mycompany.navesarcade;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
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
    short naveEnemigaPosY=55; 
    short naveDirectionEnemiga = 1;
    
    short balaPosX = -10;
    short balaPosY= -10; 
    short balaDirectionY = 0;       
    
    short vidasEnemigo = 30;       
    short vidasNave = 1;
    
    final short TEXT_SIZE = 24;
    Text textScore;
     byte naveSpeedEnemiga = 1;
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

        
         //sonido de fondo
////        final URL resource = getClass().getResource("resource/sonidoFondo.mp3");
////        Media media = new Media (resource.toString());
////        MediaPlayer MediaPlayer = new MediaPlayer(media);
////        MediaPlayer.play();
////        
        
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
        
        Rectangle naveEnemigaI = new Rectangle(0,0,45,35);
        naveEnemigaI.setFill(Color.BLACK);
        root.getChildren().add(naveEnemigaI);

        
// puntiacion del juego
        
          // Panel para mostrar textos (puntuaciones)
        HBox paneTextScore = new HBox();
        paneTextScore.setTranslateY(20);
        paneTextScore.setMinWidth(SCENE_WIDTH);
        paneTextScore.setAlignment(Pos.CENTER);
        root.getChildren().add(paneTextScore);
        // Texto de etiqueta para la puntuación
        Text textTitleScore = new Text("Vida: ");
        textTitleScore.setFont(Font.font(TEXT_SIZE));
        textTitleScore.setFill(Color.WHITE);
        // Texto para la puntuación
        textScore = new Text("0");
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.WHITE);
        paneTextScore.getChildren().add(textTitleScore);
        paneTextScore.getChildren().add(textScore);
        
//        texto para fin de partida si has ganado
        HBox paneTextGanado = new HBox();
        paneTextGanado.setTranslateY(240);
        paneTextGanado.setMinWidth(SCENE_WIDTH);
        paneTextGanado.setAlignment(Pos.CENTER);
        root.getChildren().add(paneTextGanado);
        // Texto de etiqueta para la puntuación
        Text textTitleGanado = new Text("Has Ganado");
        textTitleGanado.setFont(Font.font(TEXT_SIZE));
        textTitleGanado.setFill(Color.WHITE);

//        texto para fin de partida si has perdido
        HBox paneTextPerdido = new HBox();
        paneTextPerdido.setTranslateY(210);
        paneTextPerdido.setMinWidth(SCENE_WIDTH);
        paneTextPerdido.setAlignment(Pos.CENTER);
        root.getChildren().add(paneTextPerdido);
        // Texto de etiqueta para la puntuación
        Text textTitlePerdido = new Text("Has Perdido");
        textTitlePerdido.setFont(Font.font(TEXT_SIZE));
        textTitlePerdido.setFill(Color.WHITE);
        //intentarlo de nuevo
        HBox paneTextPerdidoR = new HBox();
        paneTextPerdidoR.setTranslateY(260);
        paneTextPerdidoR.setMinWidth(SCENE_WIDTH);
        paneTextPerdidoR.setAlignment(Pos.CENTER);
        root.getChildren().add(paneTextPerdidoR);
        // Texto de etiqueta para la puntuación
        Text textTitlePerdidoR = new Text("Intentalo de nuevo:");
        textTitlePerdidoR.setFont(Font.font(TEXT_SIZE));
        textTitlePerdidoR.setFill(Color.WHITE);
        
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
                    case R:
                    this.reinicio();
                }

            }

            private void reinicio() {
                navePosX = (short)((SCENE_WIDTH)/2);
        navePosY=420; 
        naveDirection = 0;

        naveEnemigaPosX =0;
        naveEnemigaPosY=55; 
        // cuando se recinicie el juego se le añadira mayor dificultad
        naveDirectionEnemiga = (short) (naveDirectionEnemiga +1);

        balaPosX = -10;
        balaPosY= -10; 
        balaDirectionY = 0;       

        vidasEnemigo = 30;       
        vidasNave = 1;
            }
            
        });
        //para que lo sume de uno en uno y no seguido
        scene.setOnKeyReleased((KeyEvent keyEvent) -> {
        naveDirection = 0;
//        balaDirectionY = 0;
        });

     TimerTask timerTask = new TimerTask()
     {
         public void run() 
         {
             // Aquí el código que queremos ejecutar.
         }
     };
     Timer timer = new Timer();
     timer.scheduleAtFixedRate(timerTask, 0, 1000);
        
        
        
        Timeline timeline = new Timeline(
            // 0.017 ~= 60 FPS
            new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    textScore.setText(String.valueOf(vidasEnemigo));
                    
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
                    naveEnemigaPosY = (short) (naveEnemigaPosY + 35);
                   }

                 // colision entre la bala y la nave enemiga 
                Shape.intersect(naveEnemigaF,bala );
        
                Shape shapeColision = Shape.intersect(naveEnemigaF, bala);
         
                boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();
               
                
                    if(colisionVacia == false ){
                    vidasEnemigo = (short) (vidasEnemigo -1);
                    }
                   
                // la animacion se para por que has ganado la partida
                    if(vidasEnemigo == 0){
                    naveEnemigaPosX = -20;
                    naveEnemigaPosY = -20;
                    naveDirectionEnemiga = 0;
                    naveEnemigaPosX = 0;
                    naveDirection = 0;
                    balaDirectionY = 0;
                    paneTextGanado.getChildren().add(textTitleGanado);
                    }
                    
//                 la animacion se para por que has perdido la partida
                if(naveEnemigaPosY >= 420){
                    naveEnemigaPosX = -20;
                    naveEnemigaPosY = -20;
                    naveDirectionEnemiga = 0;
                    naveEnemigaPosX = 0;
                    naveDirection = 0;
                    balaDirectionY = 0;
                    paneTextPerdido.getChildren().add(textTitlePerdido);
                    paneTextPerdidoR.getChildren().add(textTitlePerdidoR);
                    }
                }
                
            })
            
        );
        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play(); 
        
    }   
      public void reinicio (){
        navePosX = (short)((SCENE_WIDTH)/2);
        navePosY=420; 
        naveDirection = 0;

        naveEnemigaPosX =0;
        naveEnemigaPosY=55; 
        naveDirectionEnemiga = 1;

        balaPosX = -10;
        balaPosY= -10; 
        balaDirectionY = 0;       

        vidasEnemigo = 30;       
        vidasNave = 1;
    }
    public static void main(String[] args) {
        launch();
    }

}