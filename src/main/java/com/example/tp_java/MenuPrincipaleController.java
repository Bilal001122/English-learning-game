package com.example.tp_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipaleController implements Initializable {

    @FXML
    private Button newgame;
    @FXML
    private Button loadgame;
    @FXML
    private Label highscore;
    @FXML
    private Label bestplayer;
    public static Jeu jeu;
    public static boolean loaded;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            FileInputStream fileIn = new FileInputStream("jeu.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            jeu = (Jeu) in.readObject();
            in.close();
            fileIn.close();
            Joueur a = jeu.findBestJoueur();
            if (a != null) {
                bestplayer.setText("Meilleur Joueur : " + a.getNom());
                highscore.setText("Meilleur score : " + Integer.toString(a.getHighScore()));
            } else {
                bestplayer.setText("Meilleur Joueur : N/A");
                highscore.setText("Meilleur score : N/A");
            }
        } catch (FileNotFoundException e) {
            jeu = new Jeu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void startNewGame(ActionEvent actionEvent) {
        try {
            loaded = false;
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("new_game_menu.fxml"));
            Parent root = fxmlLoader.load();
            NewGameMenuController newGameMenuController = fxmlLoader.getController();
            Scene scene = new Scene(root);
            stage.setTitle("Jeu de l'oie");
            stage.setScene(scene);
            Image icon = new Image(getClass().getResourceAsStream("impostor.png"));
            stage.getIcons().add(icon);
            stage.show();
            Node source = (Node) actionEvent.getSource();
            Stage stagex = (Stage) source.getScene().getWindow();
            stagex.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadGame(ActionEvent actionEvent) {
        try {
            loaded = true;
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("load_game_menu.fxml"));
            Parent root = fxmlLoader.load();
            LoadGameMenuController loadGameMenuController = fxmlLoader.getController();
            Scene scene = new Scene(root);
            stage.setTitle("Jeu de l'oie");
            stage.setScene(scene);
            Image icon = new Image(getClass().getResourceAsStream("impostor.png"));
            stage.getIcons().add(icon);
            stage.show();
            Node source = (Node) actionEvent.getSource();
            Stage stagex = (Stage) source.getScene().getWindow();
            stagex.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
