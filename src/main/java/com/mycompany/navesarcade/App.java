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

    @Override
    public void start(Stage stage) {
        //pantalla
        var root = new Pane();
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
        //fondo
        Image fondo = new Image(getClass().getResourceAsStream("/imagenes/fondo.jpg"));
        ImageView imageViewFondo = new ImageView(fondo);
        root.getChildren().add(imageViewFondo);

        //nave x y L A
        Rectangle naveBase = new Rectangle(55,50,25,7);
        Rectangle naveCentral = new Rectangle(60,45,15,7);
        Rectangle naveCabina = new Rectangle(65,40,5,7);
        //colorear nave
        naveBase.setFill(Color.WHITE);
        naveCentral.setFill(Color.WHITE);
        naveCabina.setFill(Color.WHITE);
        //grupo nave
        Group nave = new Group();
        root.getChildren().add(naveBase);
        root.getChildren().add(naveCentral);
        root.getChildren().add(naveCabina);
        //mostrar el grupo de la nave
        root.getChildren().add(nave);
        
        //dar movimientom colocarla en su sitio hacer q dispare
     
    }   

    public static void main(String[] args) {
        launch();
    }

}