import java.util.List;
import java.util.Scanner;

public class Reservation {

    private static int chambresReservables, choix, choix2;
    private static boolean dansBoucle1, dansBoucle2, dansBoucle3;

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
                chambresReservables++;                    
            }else{
                System.out.println("La "+ statut.getTypeChambre() +" est reservee pour "+ statut.getNbrNuits() +" nuit(s) par le client "+ statut.getRelatedClient());
            }
        }
    }

    private static void annulerResa(Scanner scan, List<StatutChambres> listeStatuts, int numChambreAnnulResa)
    {
        int nbrNuitsPassees = 0;
        System.out.println("Combien de nuit(s) le client a deja passe ?");
        while (nbrNuitsPassees<0 || nbrNuitsPassees>365) {
            nbrNuitsPassees = scan.nextInt();
            if (nbrNuitsPassees>0 && nbrNuitsPassees<=365) {
                System.out.println("Faut-il facturer le client pour ces "+nbrNuitsPassees+" nuit(s) ?");
                System.out.println("1. Oui, facturer le client pour la/les nuit(s) passee(s) et le(s) repas commande(s) s'il y en a");
                System.out.println("2. Non, ne pas facturer ni la/les nuit(s) passee(s) et le(s) repas commande(s) s'il y en a au client");
                choix2=0;
                while (choix2!=1 && choix2!=2) 
                {
                    choix2=scan.nextInt();
                    if (choix2==1) {
                        //faire facturation
                        listeStatuts.get(numChambreAnnulResa).resetStatut();

                    }else if(choix2==2) {
                        listeStatuts.get(numChambreAnnulResa).resetStatut();
                    }else {
                        System.out.println("Vous n'avez pas fait de choix correct. Veuillez recommencer...");
                    }
                }
            }
        }
    }

    public static void menuReservationChambres(Scanner scan, List<StatutChambres> listeStatuts)
    {
        chambresReservables=0;
        dansBoucle1 = true;
        printHeaderSysResa(listeStatuts);
        while (dansBoucle1) 
        {
            System.out.println("----------------------------------------");
            System.out.println("       Que souhaitez vous faire ?       ");
            System.out.println("----------------------------------------");
            if (chambresReservables>0)
                System.out.println("1. Effectuer une réservation");
            if (chambresReservables<7)
            {
                System.out.println("2. Annuler une réservation");
                System.out.println("3. Modifier une réservation");
            }
            System.out.println("4. Revenir au menu principal");
            
            String getChoixMenu = scan.next();

            switch (getChoixMenu.charAt(0)) {
                case '1':
                    if (chambresReservables==0)
                        break;
                    int numChambre = 0;
                    int nbrNuits = 0;
                    String prenomClient="";
                    String nomClient="";
                    dansBoucle2 = true;
                    while (dansBoucle2) 
                    {
                        printHeaderSysResa(listeStatuts);
                        System.out.println("----------------------------------------");
                        System.out.println("        Reservation d'une chambre       ");
                        System.out.println("----------------------------------------");
                        if(numChambre == 0)
                        {
                            while (numChambre>7 || numChambre<1) 
                            {
                                System.out.println("Veuillez entrer le numero de la chambre que vous souhaitez réserver (entre 1 et 7)...");
                                numChambre = scan.nextInt();
                                if (numChambre>7 || numChambre<1)                          
                                    System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                                if (listeStatuts.get(numChambre).estReservee()) {
                                    System.out.println("Vous avez choisi une chambre actuellement revervee par un autre client. Veuillez en choisir une libre...");
                                    numChambre=0;
                                }
                            }
                        }
                        if(nbrNuits == 0)
                        {
                            while (nbrNuits>365 || numChambre<1) 
                            {
                                System.out.println("Veuillez entrer le nombre de nuit(s) que vous souhaitez passer chez nous (entre 1 et 365)...");
                                nbrNuits = scan.nextInt();
                                if (nbrNuits>365 || numChambre<1)                          
                                    System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                            }
                        }
                        dansBoucle3=true;
                        while (dansBoucle3) 
                        {
                            System.out.println("Veuillez entrer le prenom du client...");
                            prenomClient = scan.nextLine();
                            System.out.println("Veuillez entrer le nom du client...");
                            nomClient = scan.nextLine();
                            System.out.println("Vous avez entrez "+ prenomClient +" "+ nomClient +". Est-ce que c'est bon pour vous ?");
                            System.out.println("1. Oui, continuer...");
                            System.out.println("2. Non, modifier le nom et prenom...");
                            while(choix != 1 && choix != 2)
                            {
                                choix = scan.nextInt();
                                if (choix==1) {
                                    dansBoucle3=false;
                                }
                                if (choix != 1 && choix != 2) {
                                    System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                                }
                            }
                        }
                        System.out.println("Vous avez reserve la "+listeStatuts.get(numChambre).getTypeChambre()+" pour "+nbrNuits+" nuit(s) pour le client"+prenomClient +" "+ nomClient);
                        System.out.println("Est-ce que ceci vous convient ?");
                        System.out.println("1. Oui, valider la reservation et retourner au menu principal");
                        System.out.println("2. Non, recommencer la reservation en renseignant d'autres informations");
                        System.out.println("3. Non, annuler la reservation en cours de traitement et retourner au menu principal");
                        choix=0;
                        while(choix<1 || choix>3)
                        {
                            choix=scan.nextInt();
                            switch (choix) {
                                case 1:
                                    listeStatuts.get(numChambre).setReservation(true);
                                    listeStatuts.get(numChambre).setNbrNuits(nbrNuits);
                                    listeStatuts.get(numChambre).setRelatedClient(prenomClient +" "+ nomClient);
                                    dansBoucle2=false;
                                    dansBoucle1=false;
                                    break;
                                case 2:
                                    numChambre = 0;
                                    nbrNuits = 0;
                                    break;
                                case 3:
                                    dansBoucle2=false;
                                    dansBoucle1=false;
                                default:
                                    System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                                    break;
                            }
                        }
                    }
                    break;
                case '2':
                    if(chambresReservables==7)//si l'utilisateur appuie sur 2 meme alors que ca ne lui a pas ete proposé
                        break;//on sort du switch et on recommence la boucle
                    dansBoucle2=true;
                    while (dansBoucle2) {
                        printHeaderSysResa(listeStatuts);
                        System.out.println("----------------------------------------");
                        System.out.println("        Annulation de reservation       ");
                        System.out.println("----------------------------------------");
                        System.out.println("Quelle reservation souhaitez vous annuler ?");
                        System.out.println("Entrez un chiffre entre 1 et 7...");
                        choix=0; choix2=0;

                        while(choix<1 || choix>7)
                        {
                            choix = scan.nextInt();
                            if((choix>=1 || choix<=7) && listeStatuts.get(choix).estReservee())
                            {
                                System.out.println("Vous avez choisi la "+listeStatuts.get(choix).getTypeChambre()+", est-ce correct ?");
                                System.out.println("1. Oui, annuler la reservation de cette chambre");
                                System.out.println("2. Non, selectionner une autre chambre");
                                System.out.println("3. Ne finalement pas annuler de reservation et retourner au menu principal");

                                dansBoucle3=true;
                                while (dansBoucle3) {
                                    choix2 = scan.nextInt();

                                    switch (choix2) {
                                        case 1:
                                            annulerResa(scan, listeStatuts, choix);
                                            break;
                                        case 2:
                                            dansBoucle3=false;
                                            choix=0;
                                            break;
                                        case 3:
                                            dansBoucle3=false;
                                            dansBoucle2=false;
                                            dansBoucle1=false;
                                            break;
                                        default:
                                            System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                                            break;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case '3':
                    break;
                case '4':
                    dansBoucle1=false;
                    break;
                default:
                    System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                    break;
            }
            
        }
        return;
    }
}
