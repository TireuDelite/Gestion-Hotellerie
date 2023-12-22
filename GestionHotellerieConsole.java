import java.util.Scanner;

public class GestionHotellerieConsole {

    public static void main(String[] args) {
        
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
}