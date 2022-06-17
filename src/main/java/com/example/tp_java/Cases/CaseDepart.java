package com.example.tp_java.Cases;

import com.example.tp_java.Joueur;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class CaseDepart extends Case {
    public CaseDepart(int numero, Button button) {
        super(numero, button);
        setColor(Color.YELLOW);
    }

    @Override
    public void deplacer(Joueur joueur) {
    }

    public void changerScore(Label score) {

    }
}
