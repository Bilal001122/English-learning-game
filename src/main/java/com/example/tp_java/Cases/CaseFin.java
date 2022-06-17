package com.example.tp_java.Cases;

import com.example.tp_java.Joueur;
import com.example.tp_java.Plateau;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class CaseFin extends Case {
    public CaseFin(int numero, Button button) {
        super(numero, button);
        setColor(Color.BLACK);
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
    }

    public void changerScore(Label score) {

    }
}
