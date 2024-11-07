package org.gestion;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Classe principale de l'application de gestion de bibliothèque.
 * Cette application permet de gérer des livres, des utilisateurs, et des transactions d'emprunt.
 */
public class BibliothequeApp extends Application {
    // Listes pour stocker les livres, utilisateurs et transactions d'emprunt
    private ArrayList<Livre> livres = new ArrayList<>();
    private ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();
    // variable combox et label pas de donneés dans la liste
    private ComboBox<String> choixLivre;
    private ComboBox<String> choixUtilisateur;


    @Override
    public void start(Stage primaryStage) {
        // Crée un TabPane contenant les trois onglets de l'application
        TabPane tabPane = new TabPane();

        // Contenu Onglet transaction
        Tab transactionsContents = creerOngletTransactions();
        tabPane.getTabs().addAll(creerOngletLivres(), creerOngletUtilisateurs(),transactionsContents );

        tabPane.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue == transactionsContents) {
                        rafraichirListeChoix();
                    }
                });
        // Définition de la scène et ajout du fichier CSS depuis le dossier resources
        Scene scene = new Scene(tabPane, 800, 600);
        String css = getClass().getClassLoader().getResource("style.css").toExternalForm(); // Chemin vers le CSS
        scene.getStylesheets().add(css);
        // Définition de l'icône de l'application
        String iconPath = getClass().getResource("/icon_app.png").toExternalForm();
        primaryStage.getIcons().add(new Image(iconPath));
        primaryStage.setTitle("Bibliothèque Management");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /**
     * Crée l'onglet pour la gestion des livres.
     */
    private Tab creerOngletLivres() {
        Tab tabLivres = new Tab("Livres");
        // Mettre le texte base en white
        tabLivres.setStyle("-fx-text-base-color: white;");
        // Espacer les elements du contenu
        VBox vbox = new VBox(15);
        // empecher l'utitilisateur de fermer l'onglet
        tabLivres.setClosable(false);
        // Center les element
        vbox.setAlignment(Pos.CENTER);

        // Champs pour saisir le titre du livre et boutons pour ajouter/supprimer un livre
        TextField titreLivre = new TextField();
        titreLivre.setPromptText("Titre du livre");
        Button btnAjouterLivre = new Button("Ajouter Livre");
        Button btnSupprimerLivre = new Button("Supprimer Livre");
        ListView<String> listeLivres = new ListView<>();

        // Action : vérifier l'existance avant ajouter un livre
        btnAjouterLivre.setOnMouseClicked(e -> {
            if (!titreLivre.getText().isEmpty()){
                Livre livre = new Livre(titreLivre.getText());
                if(!livres.contains(livre)) {
                    livres.add(livre);
                }else{
                    Alerte("Le livre que vous avez entrez existe déjà !");
                }
                rafraichirListeLivres(listeLivres);
                titreLivre.clear();

            }else{
                Alerte("Veuillez remplir le champt : Titre du livre");
            }
        });

        // verifier si la biblio n'est pas vide et  supprimer le livre sélectionné
        btnSupprimerLivre.setOnMouseClicked(e -> {

            if (!livres.isEmpty()) {
                int selectedIdx = listeLivres.getSelectionModel().getSelectedIndex();
                if (selectedIdx != -1) {
                    livres.remove(selectedIdx);
                    rafraichirListeLivres(listeLivres);
                }
            }else{
                Alerte("Attention la bibliothèque ne contient pas de livre !");
            }
        });

        rafraichirListeLivres(listeLivres); // Rafraîchit la liste des livres au démarrage
        vbox.getChildren().addAll(titreLivre, btnAjouterLivre, btnSupprimerLivre, listeLivres);
        vbox.setStyle("-fx-padding: 20px");
        tabLivres.setContent(vbox);
        return tabLivres;
    }

    /**
     * Crée l'onglet pour la gestion des utilisateurs.
     */
    private Tab creerOngletUtilisateurs() {
        Tab tabUtilisateurs = new Tab("Utilisateurs");
        // Mettre le texte base en white
        tabUtilisateurs.setStyle("-fx-text-base-color: white;");
        // Espacer les elements du contenu
        VBox vbox = new VBox(15);
        // empecher l'utitilisateur de fermer l'onglet
        tabUtilisateurs.setClosable(false);
        // Center les element
        vbox.setAlignment(Pos.CENTER);
        // Champs pour saisir le nom de l'utilisateur et boutons pour ajouter/supprimer un utilisateur
        TextField nomUtilisateur = new TextField();
        nomUtilisateur.setPromptText("Nom de l'utilisateur");
        Button btnAjouterUtilisateur = new Button("Ajouter Utilisateur");
        Button btnSupprimerUtilisateur = new Button("Supprimer Utilisateur");
        ListView<String> listeUtilisateurs = new ListView<>();

        // Vérifie : l'existance, et le champ avant  ajouter un utilisateur
        btnAjouterUtilisateur.setOnMouseClicked(e -> {
            if(!nomUtilisateur.getText().isEmpty()){
                Utilisateur utilisateur = new Utilisateur(nomUtilisateur.getText());
                if(!utilisateurs.contains(utilisateur)) {
                    utilisateurs.add(utilisateur);
                }else{
                    Alerte("L'utilisateur existe déjà !");
                }
                rafraichirListeUtilisateurs(listeUtilisateurs);
                nomUtilisateur.clear();
            }else {
                Alerte("Veuillez remplir le champs : Nom d'utilisateur");
            }

        });

        // Vérifier existe au moins 1 utilisateur avant de supprimer l'utilisateur sélectionné
        btnSupprimerUtilisateur.setOnMouseClicked(e -> {
            if(!utilisateurs.isEmpty()) {
                int selectedIdx = listeUtilisateurs.getSelectionModel().getSelectedIndex();
                if (selectedIdx != -1) {
                    utilisateurs.remove(selectedIdx);
                    rafraichirListeUtilisateurs(listeUtilisateurs);
                }
            }else{
                Alerte("Aucun utilisateur a été enregistré !");
            }
        });

        rafraichirListeUtilisateurs(listeUtilisateurs); // Rafraîchit la liste des utilisateurs au démarrage
        vbox.getChildren().addAll(nomUtilisateur, btnAjouterUtilisateur, btnSupprimerUtilisateur, listeUtilisateurs);
        vbox.setStyle("-fx-padding: 20px");
        tabUtilisateurs.setContent(vbox);
        return tabUtilisateurs;
    }

    /**
     * Crée l'onglet pour la gestion des emprunts.
     */
    private Tab creerOngletTransactions() {
        Tab tabTransactions = new Tab("Emprunts");
        // Mettre le texte base en white
        tabTransactions.setStyle("-fx-text-base-color: white;");
        // Espacer les elements du contenu
        VBox vbox = new VBox(15);
        // empecher l'utitilisateur de fermer l'onglet
        tabTransactions.setClosable(false);
        // Center les element
        vbox.setAlignment(Pos.CENTER);
        // ComboBox pour choisir le livre et l'utilisateur
        choixLivre = new ComboBox<>();
        choixUtilisateur = new ComboBox<>();
        Button btnEmprunter = new Button("Emprunter Livre");
        Button btnRetourner = new Button("Retourner Livre");
        ListView<String> listeTransactions = new ListView<>();

        // Rafraîchit les choix dans les ComboBox à louverture
        rafraichirListeChoix();

        // Verifier les selections et enregistrer un emprunt
        btnEmprunter.setOnMouseClicked(e -> {
            if (choixLivre.getValue() != null && choixUtilisateur.getValue() != null) {
                Transaction transaction = new Transaction(choixUtilisateur.getValue(), choixLivre.getValue());
                transactions.add(transaction);
                rafraichirListeTransactions(listeTransactions);
            }else{
                Alerte("Veuillez selectionner l'utilisateur et le livre à emprunter!");
            }
        });

        // Action pour retourner un livre
        btnRetourner.setOnMouseClicked(e -> {
            if(!transactions.isEmpty() || !livres.isEmpty() || !utilisateurs.isEmpty()) {
                int selectedIdx = listeTransactions.getSelectionModel().getSelectedIndex();
                if (selectedIdx != -1 && !transactions.get(selectedIdx).isRetourne()) {
                    transactions.get(selectedIdx).retournerLivre();
                    rafraichirListeTransactions(listeTransactions);
                }else {
                    Alerte("Le livre : ["+transactions.get(selectedIdx).getLivre() + "] a déjà été retourné!");
                }
            }else {
                Alerte("Il n'existe aucun transactions actuellement!");
            }
        });
        vbox.getChildren().addAll( choixUtilisateur, choixLivre, btnEmprunter, btnRetourner, listeTransactions);
        vbox.setStyle("-fx-padding: 20px");
        tabTransactions.setContent(vbox);
        return tabTransactions;
    }

    /**
     * Met à jour l'affichage de la liste des livres.
     */
    private void rafraichirListeLivres(ListView<String> listeLivres) {
        listeLivres.getItems().clear();
        for (Livre livre : livres) {
            listeLivres.getItems().add(livre.getTitre());
        }
    }

    /**
     * Met à jour l'affichage de la liste des utilisateurs.
     */
    private void rafraichirListeUtilisateurs(ListView<String> listeUtilisateurs) {
        listeUtilisateurs.getItems().clear();
        for (Utilisateur utilisateur : utilisateurs) {
            listeUtilisateurs.getItems().add(utilisateur.getNom());
        }
    }

    /**
     * Met à jour l'affichage de la liste des transactions d'emprunt.
     */
    private void rafraichirListeTransactions(ListView<String> listeTransactions) {
        listeTransactions.getItems().clear();
        for (Transaction transaction : transactions) {
            String statut = transaction.isRetourne() ? "Retourné" : "En cours";
            listeTransactions.getItems().add(transaction.getLivre() + " - " + transaction.getUtilisateur() + " (" + statut + ")");
        }
    }

    /**
     * Met à jour les éléments disponibles dans les ComboBox pour les choix de livres et d'utilisateurs.
     */
    private void rafraichirListeChoix() {
        // Vider les choix livre  et utilisateur
        choixLivre.getItems().clear();
        choixUtilisateur.getItems().clear();
        // Pas de livres/utilisateur ? : ajout un message comme option
        if(livres.isEmpty()) {
            choixLivre.getItems().add("Pas de livres enregistrés!");
        } else {
            for (Livre livre : livres) {
                choixLivre.getItems().add(livre.getTitre());
            }
        }
        if (utilisateurs.isEmpty()) {
            choixUtilisateur.getItems().add("Pas d'utilisateur enregistrés !");
        }else {
            for (Utilisateur utilisateur : utilisateurs) {
                choixUtilisateur.getItems().add(utilisateur.getNom());
            }
        }
        //Selectionné par défaut les premiers options
        choixUtilisateur.getSelectionModel().select(0);
        choixLivre.getSelectionModel().select(0);

    }

    /**
     * Alerter l'utilisateur pour une action à faire
     * @msg:message
     */

    private Alert Alerte( String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attention !");
        alert.setHeaderText(msg);
        alert.showAndWait();
        return  alert;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
