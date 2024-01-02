import java.util.Scanner;
import java.util.LinkedList;

public class GestionHotellerieConsole {

    public GestionHotellerieConsole() {

        System.out.println("----------------------------------------");
        System.out.println("Bienvenue dans votre logiciel de gestion");
        System.out.println("----------------------------------------");
        System.out.println("Veuillez faire votre choix :");
        System.out.println("1. Afficher le détail des chambres");
        System.out.println("2. Afficher la disponibilité des chambres");
        System.out.println("3. Gestion des réservations");
        System.out.println("4. Commander un repas");
        System.out.println("5. Enregistrer une facture");
        System.out.println("6. Quitter");

        Scanner scan = new Scanner(System.in);
        String getchoixMenu = scan.next();

        Chambres[] chambres = Chambres.values();

        if (getchoixMenu.length() >= 0) {
            switch (getchoixMenu.charAt(0)) {
            case '1':
                System.out.println("----------------------------------------");
                System.out.println("            Détail des chambres         ");
                System.out.println("----------------------------------------");
                for (Chambres chambre : chambres) {

                    int surfaceChambres = chambre.getSurface();
                    int nbr_lits_Chambres = chambre.getNombre_Lits();
                    int prix_chambres = chambre.getPrix();

                    System.out.println("La "+ chambre.name() +" à une surface de "+ surfaceChambres +"m² puis elle a "+ nbr_lits_Chambres +" lits "+ prix_chambres +" \u20AC");
                
                }
                break;
            case '2':
                System.out.println("----------------------------------------");
                System.out.println("        Disponibilité des chambres      ");
                System.out.println("----------------------------------------");
                break;
            case '3':
                
                LinkedList<String> Listes_Chambres = new LinkedList<String>();

                for (Chambres chambre : chambres) {

                Listes_Chambres.add(chambre.name()+ " 0");


                }

                System.out.println("----------------------------------------");
                System.out.println("         Système de réservation         ");
                System.out.println("----------------------------------------");
                System.out.println("");
                System.out.println("1. Effectuer une réservation");
                System.out.println("2. Revenir au menu principal");
                System.out.println("Contenu de la liste");

                for (String chambre : Listes_Chambres) {
                    System.out.println(chambre);
                }


                break;
            case '4':
                System.out.println("----------------------------------------");
                System.out.println("             Commande de Repas          ");
                System.out.println("----------------------------------------");
                break;
            case '5':
                System.out.println("----------------------------------------");
                System.out.println("        Enregistrement de Facture       ");
                System.out.println("----------------------------------------");
                break;
            case '6':
                System.out.println("Aurevoir !!");
                System.exit(-1);
                break;
            default:
                System.out.println("Erreur, veuillez choisir un des menus proposées");
                break;
            }
        } 

        scan.close();

    }

    public static void main(String[] args) {

    new GestionHotellerieConsole();

    }
}