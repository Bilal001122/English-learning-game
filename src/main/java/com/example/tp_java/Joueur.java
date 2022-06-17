package com.example.tp_java;

import javafx.scene.control.Alert;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Joueur implements Serializable {
    private String nom;
    private int score;
    private int highScore;
    private int position;
    private int[] tab;

    public Joueur(String nom) {
        this.nom = nom;
        highScore = 0;
        score = 0;
        position = 0;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int[] getTab() {
        return tab;
    }

    public void setTab(int[] tab) {
        this.tab = tab;
    }

    public void beatScore() {
        if (highScore < score) {
            highScore = score;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("FÉLICITATIONS !!!");
            alert.setHeaderText(">> Vous avez terminé le jeu !\n>> Vous avez un nouveau meilleur score !\n>> Votre nouveau meilleur score est : " + highScore + " !\n>> Le meilleur joueur est : " + MenuPrincipaleController.jeu.findBestJoueur().getNom() + " avec un score de : " + MenuPrincipaleController.jeu.findBestJoueur().getHighScore() + ".");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("FÉLICITATIONS !!!");
            alert.setHeaderText(">> Vous avez terminé le jeu !\n>> Vous n'avez pas battu votre meilleur Score : " + highScore + ", plus de chance la prochaine fois !\n>> Le meilleur joueur est : " + MenuPrincipaleController.jeu.findBestJoueur().getNom() + " avec un score de : " + MenuPrincipaleController.jeu.findBestJoueur().getHighScore() + ".");
            alert.showAndWait();
        }
    }
}
