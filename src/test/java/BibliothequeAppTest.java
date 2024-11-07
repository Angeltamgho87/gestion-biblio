package org.gestion;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour les classes Livre, Utilisateur, et Transaction.
 */
public class BibliothequeAppTest {

    @Test
    public void testAjouterLivre() {
        Livre livre = new Livre("Les Misérables");
        assertEquals("Les Misérables", livre.getTitre(), "Le titre du livre ne correspond pas.");
    }

    @Test
    public void testAjouterUtilisateur() {
        Utilisateur utilisateur = new Utilisateur("Jean Dupont");
        assertEquals("Jean Dupont", utilisateur.getNom(), "Le nom de l'utilisateur ne correspond pas.");
    }

    @Test
    public void testEmpruntEtRetourTransaction() {
        Transaction transaction = new Transaction("Jean Dupont", "Les Misérables");

        // Vérifie que le livre est initialement non retourné
        assertFalse(transaction.isRetourne(), "Le livre devrait être en cours d'emprunt.");

        // Marque le livre comme retourné et vérifie
        transaction.retournerLivre();
        assertTrue(transaction.isRetourne(), "Le livre devrait être marqué comme retourné.");
    }
}
