package com.example.tp_java;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DefController implements Initializable {
    @FXML
    private Label defdefinition;
    @FXML
    private HBox defhbox;
    @FXML
    private Button defsubmit;
    private ArrayList<TextField> tfTab;
    private String reponse;
    private static int keep = 1;

    public void initialize(URL location, ResourceBundle resources) {
        defdefinition.setText(lireDefinition());
        TextField tf;
        tfTab = new ArrayList<TextField>();
        reponse = lireReponse();
        for (int i = 0; i < reponse.length(); i++) {
            tf = new TextField();
            tf.setFont(Font.font(20));
            tf.setStyle("-fx-alignment: center");
            tf.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            tf.setMinSize(0, 0);
            addTextLimiter(tf, 1);
            defhbox.getChildren().add(tf);
            HBox.setHgrow(tf, Priority.ALWAYS);
            tfTab.add(tf);
        }
        defhbox.setSpacing(10);
        defhbox.setPadding(new Insets(0, 70, 0, 70));
        defsubmit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Plateau.reponse = verifierReponse();
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

    public String lireDefinition() {
        try {
            @SuppressWarnings("resource")
            BufferedReader reader = new BufferedReader(new FileReader("definitions.txt"));
            String definition = null;
            for (int i = 0; i < keep; i++) {
                definition = reader.readLine();
            }
            return definition;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public String lireReponse() {
        try {
            @SuppressWarnings("resource")
            BufferedReader reader = new BufferedReader(new FileReader("reponses.txt"));
            String reponse = null;
            for (int i = 0; i < keep; i++) {
                reponse = reader.readLine();
            }
            keep++;
            return reponse;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public boolean verifierReponse() {
        String current;
        for (int i = 0; i < tfTab.size(); i++) {
            current = Character.toString(reponse.charAt(i));
            if (!tfTab.get(i).getText().equalsIgnoreCase(current)) {
                return false;
            }
        }
        return true;
    }
}