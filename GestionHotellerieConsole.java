import java.util.Scanner;

public class GestionHotellerieConsole {

    public static void main(String[] args) {

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
                System.out.println("Voici le détail des chambres :");
                for (Chambres chambre : chambres) {

                    int surfaceChambres = chambre.getSurface();
                    int nbr_lits_Chambres = chambre.getNombre_Lits();
                    int prix_chambres = chambre.getPrix();

                    System.out.println("La "+ chambre.name() +" à une surface de "+ surfaceChambres +"m² puis elle a "+ nbr_lits_Chambres +" lits "+ prix_chambres +" \u20AC");
                   
                }
                break;
            case '2':
                break;
            case '3':
                
                break;
            case '4':
                
                break;
            case '5':
                System.out.println("");
                break;
            case '6':
                System.exit(-1);
                break;
            default:
                break;
            }
        } 
        
    }
}