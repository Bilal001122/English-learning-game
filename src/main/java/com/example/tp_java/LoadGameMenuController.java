package com.example.tp_java;

import com.example.tp_java.Plateau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadGameMenuController implements Initializable {

    @FXML
    private Button startLoadGame;
    @FXML
    private ComboBox<String> chooseThePlayerYouWantToLoad;
    public static String existingplayername;

    public void initialize(URL url, ResourceBundle resourceBundle) {
    	for (int i = 0; i < MenuPrincipaleController.jeu.lastindx; i++) {    		
    		chooseThePlayerYouWantToLoad.getItems().add(MenuPrincipaleController.jeu.getTabJoueur()[i].getNom());        
    	}
    }

    public void load(ActionEvent actionEvent) {
        try {
        	existingplayername = chooseThePlayerYouWantToLoad.getValue();
        	if (existingplayername != null) {        		
        		Stage stage = new Stage();
        		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("plateau.fxml"));
        		Parent root = fxmlLoader.load();
        		Plateau plateau = fxmlLoader.getController();
        		Scene scene = new Scene(root);
        		stage.setTitle("Jeu de l'oie");
        		stage.setScene(scene);
        		Image icon = new Image(getClass().getResourceAsStream("impostor.png"));
        		stage.getIcons().add(icon);
        		stage.setMaximized(true);
        		stage.show();
        		Node source = (Node) actionEvent.getSource();
        		Stage stagex = (Stage) source.getScene().getWindow();
        		stagex.close();
        	}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
