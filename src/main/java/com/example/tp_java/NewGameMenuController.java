package com.example.tp_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewGameMenuController implements Initializable {

    @FXML
    private Button startg;
    @FXML
    private TextField playername;
    @FXML
    private ComboBox<String> existingplayer;
    public static String name;
    public static String existingname;

    public void initialize(URL location, ResourceBundle resources) {
    	for (int i = 0; i < MenuPrincipaleController.jeu.lastindx; i++) {
    		existingplayer.getItems().add(MenuPrincipaleController.jeu.getTabJoueur()[i].getNom());
    	}    	
    }

    public void start(ActionEvent actionEvent) {
        try {
            name = playername.getText();
            existingname = existingplayer.getValue();
            if ((name.length() > 0) || (existingname != null)) {
            	if (name.length() > 0) {
            		MenuPrincipaleController.jeu.addJoueur(new Joueur(name));
            	}
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
    
    public void ClickOnTextField(ActionEvent event) {    	
    	existingplayer.setValue(null);
    }
    
    public void ClickOnComboBox(ActionEvent event) {
    	playername.setText("");
    }

}
