package com.example.tp_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu_principale.fxml"));
        Parent root = fxmlLoader.load();
        MenuPrincipaleController menuPrincipaleController = fxmlLoader.getController();
        Scene scene = new Scene(root);
        stage.setTitle("Jeu de l'oie");
        stage.setScene(scene);
        Image icon = new Image(getClass().getResourceAsStream("impostor.png"));
        stage.getIcons().add(icon);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}