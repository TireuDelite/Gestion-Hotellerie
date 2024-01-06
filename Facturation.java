import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Facturation {
    

    public static void Menu_Facturation(Scanner scan, List<StatutChambres> listeStatuts) { // Cette methode permet de recup le plat selectionne 

        Clear.clear();

        String ChoixMenuFacturation = "";

        while (!ChoixMenuFacturation.equals("2")) { // Tant que ChoixMenuFacturation n'égal pas 2
                System.out.println("----------------------------------------");
                System.out.println("                Facturation             ");
                System.out.println("----------------------------------------");
                System.out.println("1. Enregistrer une facture");
                System.out.println("2. Retour au menu principal");
                
                int numChambreFacture = 0;

            if (ChoixMenuFacturation.length() >= 0) {
                try {
                    ChoixMenuFacturation = scan.next();

                    switch (ChoixMenuFacturation.charAt(0)) {

                    case '1':
                        while(numChambreFacture<1 || numChambreFacture>7) { // On vérifie que le numéro entrée est bien dans le pool de chambres

                            System.out.println("----------------------------------------");
                            System.out.println("          edition de la facture         ");
                            System.out.println("----------------------------------------");

                            System.out.println(("Pour quel chambre souhaitez vous editer une facture ?"));

                            numChambreFacture = scan.nextInt();

                            if ((numChambreFacture>= 1 && numChambreFacture <= 7)) {
                                if (listeStatuts.get(numChambreFacture - 1).estReservee()) {

                                    // On lance Ecrire_Factures avec les paramètres en fonction de leurs positions dans la liste listeStatuts puis on effectue les méthodes nécéssaires a nos demandes
                                    Ecrire_Factures(listeStatuts.get(numChambreFacture - 1).getRelatedClient(), listeStatuts.get(numChambreFacture - 1).getRepasCommandes(), listeStatuts.get(numChambreFacture - 1).getNbrNuits(), listeStatuts.get(numChambreFacture - 1).getPrixReservation());

                                    listeStatuts.get(numChambreFacture - 1).resetStatut(); // Une fois la facture édité alors on reset le status de la chambre
                                    System.out.println(ChoixMenuFacturation);
                                }
                                else {
                                    System.out.println("La chambre designe n'est pas reserve");
                                    numChambreFacture = 0; // On indique 0 pour numChambreFacture car on souhaite repartir dans la boucle
                                }
                            }
                            else {
                                System.out.println("La chambre indique n'existe pas, veuillez réessayer...");
                            }
                            break;
                    }
                    case '2':
                        break;
                    default:
                        System.out.println("Veuillez utiliser un des choix diponibles");
                        break;
                    }
                }

                catch (InputMismatchException e) {

                System.out.println("Veuillez entrer un nombre valide.");
                scan.next(); // Pour consommer la saisie incorrecte

                }
            }
        }
    }

    public static void Ecrire_Factures(String nom_prenom, List<Repas> plats_commandes , int nbrNuits, int Cout_total) {

        String currentDate = getDate.getCurrentDate(); // On récupere la date depuis getDate.java
        String userDirectory = System.getProperty("user.home"); // Cela permet de recuperer le home dir de l'user qui lance le programme tel que : /home/utilisateur

        try {

            String fileName = nom_prenom + " " + currentDate;
            File file = new File(userDirectory + "/" + fileName + ".txt");

            if (!file.exists()) { // Si un fichier de même nom n'existe pas alors on le créer
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            // Écriture dans le fichier
            bw.write("Nom et prénom du client : " + nom_prenom + "\nRepas commandés : " + plats_commandes + "\nNombre de nuits passés dans l'hotel : " + nbrNuits + "\nCout total : " + Cout_total + " \u20AC");

            bw.close();

            System.out.println("Facture editee avec succes");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}