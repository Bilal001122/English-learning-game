package com.example.tp_java.Cases;

import com.example.tp_java.Joueur;
import com.example.tp_java.Plateau;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Random;

public class CaseSaut extends Case {

    public CaseSaut(int numero, Button button) {
        super(numero, button);
        setColor(Color.ORANGE);
    }

    public class AnimationImpostor extends AnimationTimer {
        private long i = 0;
        private long previous = 0;
        private boolean advance = false;

        public AnimationImpostor() {
            if (Plateau.pos > Plateau.oldPos) {
                advance = true;
            }
        }

        @Override
        public void handle(long l) {
            if (advance) {
                if (Plateau.pos == Plateau.oldPos - 1) {
                    stop();
                }
                if (l - previous > 90000000) {
                    GridPane.setConstraints(Plateau.impostor, GridPane.getColumnIndex(Plateau.tabButtons[Plateau.oldPos]), GridPane.getRowIndex(Plateau.tabButtons[Plateau.oldPos]));
                    Plateau.oldPos++;
                    previous = l;
                }
            } else {
                if (Plateau.pos == Plateau.oldPos + 1) {
                    stop();
                }
                if (l - previous > 90000000) {
                    GridPane.setConstraints(Plateau.impostor, GridPane.getColumnIndex(Plateau.tabButtons[Plateau.oldPos]), GridPane.getRowIndex(Plateau.tabButtons[Plateau.oldPos]));
                    Plateau.oldPos--;
                    previous = l;
                }
            }
        }
    }

    @Override
    public void deplacer(Joueur joueur) {
        AnimationImpostor animationImpostor = new AnimationImpostor();
        animationImpostor.start();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CASE ORANGE");
        Random random = new Random();
        int newPos = random.nextInt(98) + 1;
        int oldPos = Plateau.pos;
        if (oldPos == newPos) {
            newPos -= 1;
        }
        alert.setHeaderText(">> Vous devez vous déplacer à la case : " + (newPos + 1));
        Plateau.caseIsClicked = false;
        Plateau.sautage = true;
        alert.showAndWait();
        Plateau.pos = newPos;
    }

    public void changerScore(Label score) {

    }
}
