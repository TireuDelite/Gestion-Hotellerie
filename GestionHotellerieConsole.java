import java.util.Scanner;

public class GestionHotellerieConsole {

    public static void main(String[] args) {
<<<<<<< HEAD

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

                    System.out.println("Le numéro de chambres est "+chambre.name()+" Sa surface est de "+surfaceChambres+" puis elle a "+nbr_lits_Chambres+" lits");
                   
                }
                break;
            case '2':
                break;
            case '3':
                
                break;
            case '4':
                
                break;
            case '5':
                
                break;
            case '6':
                System.exit(-1);
                break;
            default:
                break;
            }
        } 
        
    }
    
=======
        
        System.out.println("------------------------------------------------------");
        System.out.println("Bienvenue dans votre logiciel de Gestion Hotelliere");
        System.out.println("------------------------------------------------------");

        int i = 0; // Boucle du menu tant que pas répondu

        System.out.println("Vous souhaitez ?");
        System.out.println("1. Afficher les détails d'une chambre");
        System.out.println("2. Afficher les chambres et leurs disponibilités");
        System.out.println("3. Commander un repas ?");
        System.out.println("4. Effectuer la réservation d'une chambre");
        System.out.println("5. Enregistrer une facture ");

        Scanner reponseMenu = new Scanner(System.in);
        String choix = reponseMenu.next();

        while (i == 0) {

            switch (choix.charAt(0)) {
                case '1':
                    // Mettre ici le code pour détails des chambre
                    break;
                case '2':
                    // Mettre ici l'affichage des chambres et de leur états
                    break;
                case '3':
                    // Mettre ici le code pour la commande de repas
                    break;
                case '4':
                    // Mettre ici le code pour réservation d'une chambre
                    break;
                case '5':
                    // Mettre ici le code pour l'enregistrement de facture
                    break;
                default:
                    System.out.println("Veuillez entrer un choix valide [1, 2, 3, 4, 5]");
                    break;
            }
        }
    }
>>>>>>> 5c520e0b37195cfc740a0d0dafd0c4f0a9e8ae92
}