package com.example.tp_java;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ImgController implements Initializable {
    @FXML
    private Button imgsubmit;
    @FXML
    private Label imgquestion;
    @FXML
    private ComboBox<String> answerbutton;
    @FXML
    private Label imgword;
    @FXML
    private ImageView iv1;
    @FXML
    private ImageView iv2;
    @FXML
    private ImageView iv3;
    @FXML
    private ImageView iv4;
    private int[] tab = {0, 0, 0, 0};
    private int reponse;
    private static int n = 0;

    public void initialize(URL location, ResourceBundle resources) {
        imgsubmit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Plateau.reponse = verifierReponse();
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });
        answerbutton.getItems().addAll("Image A", "Image B", "Image C", "Image D");
        imgword.setText(lireMot());
        imgword.textAlignmentProperty();
        imgword.setTextAlignment(TextAlignment.CENTER);
        remplirImage(reponse);
    }


    public String lireMot() {
        try {
            @SuppressWarnings("resource")
            BufferedReader reader = new BufferedReader(new FileReader("mots.txt"));
            n++;
            String definition = null;
            for (int i = 0; i < n; i++) {
                definition = reader.readLine();
            }
            reponse = n;
            return definition;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public void remplirImage(int a) {
        int length = 1;
        tab[0] = a;
        Random random = new Random();
        boolean exists = false;
        while (length < 4) {
            int n = random.nextInt(32) + 1;
            for (int i = 0; i < 4; i++) {
                if (tab[i] == n) {
                    exists = true;
                }
            }
            if (!exists) {
                tab[length] = n;
                length++;
            }
            exists = false;
        }
        int n = random.nextInt(4);
        reponse = n;
        for (int i = 0; i < 4; i++) {
            if (n == 0) {
                iv1.setImage(new Image(getClass().getResourceAsStream("imagescaseimg/img" + tab[i] + ".jpg")));
            }
            if (n == 1) {
                iv2.setImage(new Image(getClass().getResourceAsStream("imagescaseimg/img" + tab[i] + ".jpg")));
            }
            if (n == 2) {
                iv3.setImage(new Image(getClass().getResourceAsStream("imagescaseimg/img" + tab[i] + ".jpg")));
            }
            if (n == 3) {
                iv4.setImage(new Image(getClass().getResourceAsStream("imagescaseimg/img" + tab[i] + ".jpg")));
            }
            n = (n + 1) % 4;
        }
    }

    public boolean verifierReponse() {
        if (answerbutton.getItems().get(reponse).equals((answerbutton).getValue())) {
            return true;
        } else return false;
    }
}
