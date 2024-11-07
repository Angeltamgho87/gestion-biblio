package org.gestion;

/**
 * Classe représentant une transaction d'emprunt dans la bibliothèque.
 */
public class Transaction {
    private String utilisateur;
    private String livre;
    private boolean retourne = false;

    /**
     * Constructeur pour créer une transaction avec un utilisateur et un livre.
     */
    public Transaction(String utilisateur, String livre) {
        this.utilisateur = utilisateur;
        this.livre = livre;
    }

    /**
     * Récupère le nom de l'utilisateur associé à la transaction.
     */
    public String getUtilisateur() {
        return utilisateur;
    }

    /**
     * Récupère le titre du livre associé à la transaction.
     */
    public String getLivre() {
        return livre;
    }

    /**
     * Vérifie si le livre a été retourné.
     */
    public boolean isRetourne() {
        return retourne;
    }

    /**
     * Marque le livre comme retourné.
     */
    public void retournerLivre() {
        this.retourne = true;
    }
}
