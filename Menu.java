import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Menu {

    static Repas[] menus = Repas.values();

    public static void Menu_Repas(Scanner scan, List<StatutChambres> listeStatuts) { //Cette methode permet de recup le plat selectionne 

        String ChoixMenuRepas = "";

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
                        Menu.Menu_Choisir_Repas(scan, listeStatuts);
                    
                        break;
                    case '2':
                        Clear.clear();
                        System.out.println("Voici les liens pourvant être utilises pour commander en dehors de l'hotel");
                        System.out.println("https://www.ubereats.com/fr/");
                        System.out.println("http://www.deliveroo.fr");
                        System.out.println("https://www.just-eat.fr");
                        System.out.println("");
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
    }

    public static void Menu_Choisir_Repas(Scanner scan, List<StatutChambres> listeStatuts) {

        int ChoixMenuRepas2 = 0;
        int numChambre = 0;

        while (ChoixMenuRepas2 != 6) {

                Clear.clear();
                System.out.println("----------------------------------------");
                System.out.println("             Plats disponibles          ");
                System.out.println("----------------------------------------");
                System.out.println("Vous souhaitez ? :");
                System.out.println("1. Steak Frites");
                System.out.println("2. Lasagnes");
                System.out.println("3. Salade Cesar");
                System.out.println("4. Hamburger");  
                System.out.println("5. Spaghetti Bolognaise");
                System.out.println("6. Revenir en arrière");

                ChoixMenuRepas2 = scan.nextInt();


            if (ChoixMenuRepas2 > 0 && ChoixMenuRepas2 != 6) {

                System.out.println("Veuillez nous indiquer votre numero de chambre :");

                numChambre = scan.nextInt();

                try {

                    if (numChambre >= 1 && numChambre <= 7) {
                        if (listeStatuts.get(numChambre - 1).estReservee()) {
                        listeStatuts.get(numChambre - 1).addRepasCommandes(Repas.values()[ChoixMenuRepas2 - 1]); // On ajoute le repas en fonction du numéro de chambre dans la liste
                        }
                        else {
                            System.out.println("Vous avez entre le numéro d'une chambre non reservee, Veuillez ressayer");
                            ChoixMenuRepas2 = 0;
                        }
                    }
                    else {
                        System.out.println("Vous avez entre un mauvais numéro de chambre, Veuillez ressayer");
                    }
                }
                catch (InputMismatchException e) { // Exception levé si l'utilisateur ne renseigne pas un numéro compris dans le pool 1 à 6

                System.out.println("Veuillez entrer un nombre valide.");
                scan.next(); // Pour consommer la saisie incorrecte

                }
            }
        }
    }
}
