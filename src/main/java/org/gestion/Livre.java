package org.gestion;

import java.util.Objects;


/**
 * Classe représentant un livre dans la bibliothèque.
 */
public class Livre {
    private String titre;

    /**
     * Constructeur pour créer un livre avec un titre donné.
     */
    public Livre(String titre) {
        this.titre = titre;
    }

    /**
     * Récupère le titre du livre.
     */
    public String getTitre() {
        return titre;
    }
    // Redéfinir equals() pour éviter les duplications
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Livre that = (Livre) obj;
        return Objects.equals(titre, that.titre);
    }

    // Redéfinir hashCode() si equals() est redéfini
    @Override
    public int hashCode() {
        return Objects.hash(titre);
    }

}
