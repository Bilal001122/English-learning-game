package com.example.tp_java;

import java.io.Serializable;

public class Jeu implements Serializable {
	private Joueur[] tabJoueur;
	int lastindx;
	public Jeu() {
		tabJoueur = new Joueur[20];
		lastindx = 0;
	}
	public void addJoueur(Joueur joueur) {
		tabJoueur[lastindx] = joueur;
		lastindx++;
	}
	
	public Joueur[] getTabJoueur() {
		return tabJoueur;
	}
	
	public Joueur getLastJoueur() {
		return tabJoueur[lastindx - 1];
	}
	
	public Joueur findJoueur(String name) {
		for (int i = 0; i < lastindx; i++) {
			if (tabJoueur[i].getNom().equals(name)) {
				return tabJoueur[i];
			}
		}
		return null;
	}
	
	public Joueur findBestJoueur() {
		int indice = 0;
		int best = 0;
		for (int i = 0; i < lastindx; i++) {
			if (tabJoueur[i].getHighScore() > best) {
				indice = i;
				best = tabJoueur[i].getHighScore();
			}
		}
		return tabJoueur[indice];
	}
	
	public void setJoueur(Joueur joueur) {
		
		for (int i = 0; i < lastindx; i++) {
			if (tabJoueur[i].getNom().equals(joueur.getNom())) {
				tabJoueur[i] = joueur;
			}
		}
	}
}
