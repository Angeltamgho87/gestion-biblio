package org.gestion;

import java.util.Objects;

/**
 * Classe représentant un utilisateur de la bibliothèque.
 */
public class Utilisateur {
    private String nom;

    /**
     * Constructeur pour créer un utilisateur avec un nom donné.
     */
    public Utilisateur(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le nom de l'utilisateur.
     */
    public String getNom() {
        return nom;
    }

    // Redéfinir equals() pour éviter les duplications
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Utilisateur that = (Utilisateur) obj;
        return Objects.equals(nom, that.nom);
    }

    // Redéfinir hashCode() si equals() est redéfini
    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

}
