package com.example.tp_java.Cases;

import com.example.tp_java.Joueur;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public abstract class Case {
    private Color color;
    private int numero;
    private Button button;

    public Case(int numero, Button button) {
        this.numero = numero;
        this.button = button;
    }

    public abstract void deplacer(Joueur joueur);

    public abstract void changerScore(Label score);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

}

