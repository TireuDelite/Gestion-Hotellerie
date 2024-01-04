import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Menu {

    static Repas[] menus = Repas.values();

    public static String Menu_Repas(Scanner scan) {

        String ChoixMenuRepas = "";
        String test = "";

        while (!ChoixMenuRepas.equals("3")) {

                System.out.println("----------------------------------------");
                System.out.println("             Commande de Repas          ");
                System.out.println("----------------------------------------");
                System.out.println("1. Afficher les plats disponibles");
                System.out.println("2. Commander un plat en dehors de l'hotel");
                System.out.println("3. Retour au menu principal");
                
            if (ChoixMenuRepas.length() >= 0) {
                try {
                    ChoixMenuRepas = scan.next();

                    switch (ChoixMenuRepas.charAt(0)) {

                    case '1':
                        System.out.println("----------------------------------------");
                        System.out.println("             Plats disponibles          ");
                        System.out.println("----------------------------------------");

                        for (Repas menu : menus) {

                            String menu_et_prix = menu.toString();

                            System.out.println(menu_et_prix);

                        }

                        System.out.println(ChoixMenuRepas);
                        test = Menu.Menu_Choisir_Repas(scan);

                        System.out.println(test);
                    
                        break;
                    case '2':
                        System.out.println("Voici les liens pourvant être utilisés pour commander en dehors de l'hotel");
                        System.out.println("https://www.ubereats.com/fr/");
                        System.out.println("http://www.deliveroo.fr");
                        System.out.println("https://www.just-eat.fr");
                        break;
                    case '3':
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
        return test;
    }

    public static String Menu_Choisir_Repas(Scanner scan) {

        String ChoixMenuRepas2 = "";

        while (!ChoixMenuRepas2.equals("6")) {

                System.out.println("----------------------------------------");
                System.out.println("             Plats disponibles          ");
                System.out.println("----------------------------------------");
                System.out.println("Vous souhaitez ? :");
                System.out.println("1. Steak Frites");
                System.out.println("2. Lasagnes");
                System.out.println("3. Salade César");
                System.out.println("4. Hamburger");  
                System.out.println("5. Spaghetti Bolognaise");
                System.out.println("6. Revenir en arrière");

            if (ChoixMenuRepas2.length() >= 0) {
                try {
                    ChoixMenuRepas2 = scan.next();

                    switch (ChoixMenuRepas2.charAt(0)) {

                    case '1':
                        
                        return "Steak Frites";
                    case '2':

                        return "Lasagnes";
                    case '3':

                        return "Salade César";
                    case '4':

                        return "Hambuger";
                    case '5':

                        return "Spaghetti Bolognaise";
                    case '6':
                    default:
                        System.out.println("Veuillez utiliser un des choix diponibles");
                        
                    }
                }

                catch (InputMismatchException e) {

                System.out.println("Veuillez entrer un nombre valide.");
                scan.next(); // Pour consommer la saisie incorrecte

                }
            }
        }

        return "Choix invalide ou retour en arrière";
    }

    public static void Ecrire_Factures(String nom_prenom, String plats_commandes, String nbrNuits) {

        String currentDate = getDate.getCurrentDate();


        try {

            String fileName = nom_prenom + " " + currentDate;
            File file = new File("~/Factures/" + fileName);

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
