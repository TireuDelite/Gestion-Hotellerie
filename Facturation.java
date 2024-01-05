import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Facturation {
    

    public static void Menu_Facturation(Scanner scan, List<StatutChambres> listeStatuts) { //Cette méthode permet de récup le plat sélectionné 

        Clear.clear();

        String ChoixMenuFacturation = "";

        while (!ChoixMenuFacturation.equals("2")) {
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
                        System.out.println("----------------------------------------");
                        System.out.println("          Édition de la facture         ");
                        System.out.println("----------------------------------------");

                        System.out.println(("Pour quel chambre souhaitez vous éditer une facture ?"));

                        numChambreFacture = scan.nextInt();

                        Ecrire_Factures(listeStatuts.get(numChambreFacture - 1).getRelatedClient(), listeStatuts.get(numChambreFacture - 1).getRepasCommandes(), listeStatuts.get(numChambreFacture - 1).getNbrNuits());
                        System.out.println(ChoixMenuFacturation);
                    
                        break;
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

    public static void Ecrire_Factures(String nom_prenom, List<Repas> plats_commandes , int nbrNuits) {

        String currentDate = getDate.getCurrentDate();

        try {

            String fileName = nom_prenom + " " + currentDate;
            File file = new File("Factures/" + fileName + ".txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(nom_prenom + "\n" + plats_commandes + "\n" + nbrNuits);

            bw.close();

            System.out.println("Facture éditée avec succès");

        }

        catch (IOException e) {

            e.printStackTrace();

        }
    }

}




