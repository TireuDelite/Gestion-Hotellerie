import java.util.List;
import java.util.Scanner;

public class Reservation {

    private static boolean dansBoucle1, dansBoucle2;

    private static void printHeaderSysResa(List<StatutChambres> listeStatuts) {
        Clear.clear();
        System.out.println("----------------------------------------");
        System.out.println("         Système de réservation         ");
        System.out.println("----------------------------------------");
        System.out.println("Détails des chambres et des réservations en cours :");
        for(StatutChambres statut : listeStatuts)
        {
            if (statut.estReservee() == false) {
                System.out.println("La "+ statut.getTypeChambre() +" n'est pas reservee.");                    
            }else{
                System.out.println("La "+ statut.getTypeChambre() +" est reservee pour "+ statut.getNbrNuits() +" nuit(s) par le client "+ statut.getRelatedClient());
            }
        }
    }

    public static Chambres menuReservationChambres(Scanner scan, List<StatutChambres> listeStatuts)
    {
        dansBoucle1 = true;
        while (dansBoucle1) {
            printHeaderSysResa(listeStatuts);
            System.out.println("----------------------------------------");
            System.out.println("       Que souhaitez vous faire ?       ");
            System.out.println("----------------------------------------");
            System.out.println("1. Effectuer une réservation");
            System.out.println("2. Annuler une réservation");
            System.out.println("3. Modifier une réservation");
            System.out.println("5. Revenir au menu principal");
            
            String getChoixMenu = scan.next();

            switch (getChoixMenu.charAt(0)) {
                case '1':
                int numChambre = 0;
                dansBoucle2 = true;
                while (dansBoucle2) 
                {
                    printHeaderSysResa(listeStatuts);
                    System.out.println("----------------------------------------");
                    System.out.println("        Reservation d'une chambre       ");
                    System.out.println("----------------------------------------");
                    if(numChambre == 0)
                    {
                        System.out.println("Veuillez entrer le numero de la chambre que vous souhaitez réserver (entre 1 et 7)...");
                        numChambre = scan.nextInt();
                    }
                    System.out.println("2. Annuler une réservation");
                    System.out.println("3. Modifier une réservation");
                    System.out.println("5. Revenir au menu principal");
                }    
                break;
            
                default:
                    break;
            }
            
        }
        return;
    }
}
