import java.util.List;
import java.util.Scanner;

public class Reservation {

    //declaration des variables
    private static int chambresReservables, choix, choix2;
    private static boolean dansBoucle1, dansBoucle2, dansBoucle3;

    //methode d'affichage du header menu systeme de reservation
    private static void printHeaderSysResa(List<StatutChambres> listeStatuts) {
        chambresReservables=0;
        Clear.clear();
        System.out.println("----------------------------------------");
        System.out.println("         Système de reservation         ");
        System.out.println("----------------------------------------");
        System.out.println("Details des chambres et des reservations en cours :");
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

    //methode de modification de reservation
    private static void modifierResa(Scanner scan, List<StatutChambres> listeStatuts, int numChoixChambre)
    {
        int newNumChambre = 0;
        int newNbrNuits = 0;
        String newPrenomClient="";
        String newNomClient="";
        dansBoucle1=true;
        while (dansBoucle1) {
            printHeaderSysResa(listeStatuts);
            System.out.println("----------------------------------------");
            System.out.println("       Modification de reservation      ");
            System.out.println("----------------------------------------");
            System.out.println("Reservation selectionnee :");//affichage des information de la chambre selectionnee a modifier
            System.out.println("Chambre reservee : "+listeStatuts.get(numChoixChambre-1).getTypeChambre());
            System.out.println("Client associe : "+listeStatuts.get(numChoixChambre-1).getRelatedClient());
            System.out.println("Nombre de nuit(s) : "+listeStatuts.get(numChoixChambre-1).getNbrNuits()+" nuit(s)");
            System.out.println("\nQue souhaitez vous faire ?");
            if (chambresReservables>0)//si il y a au moins une chambre libre
                System.out.println("1. Modifier la chambre de cette reservation");//on print le choix de modification de chambre
            System.out.println("2. Modifier le nom/prenom du client associe a la reservation");
            System.out.println("3. Modifier le nombre de nuit(s) de la reservation");
            System.out.println("4. Selectionner une autre reservation");
            System.out.println("5. Annuler la modification et revenir au menu principal");

            choix=0;
            while (choix<1 || choix>5) {
                choix=scan.nextInt();
                if (choix>=1 && choix<=5) {
                    switch (choix) {
                        case 1:
                            if (chambresReservables==0) //si l'user a entré 1 meme si ca ne lui était pas proposé 
                                break;//on sort de la case
                            newNumChambre=0;
                            while (newNumChambre>7 || newNumChambre<1) 
                            {
                                printHeaderSysResa(listeStatuts);//appel du print du header
                                System.out.println("----------------------------------------");
                                System.out.println("       Modification de reservation      ");
                                System.out.println("----------------------------------------");
                                System.out.println("Chambre actuelle : "+listeStatuts.get(numChoixChambre-1).getTypeChambre());
                                System.out.println("Veuillez entrer le numero de la chambre que vous souhaitez reserver a la place de la "+listeStatuts.get(numChoixChambre-1).getTypeChambre());
                                newNumChambre = scan.nextInt();
                                scan.nextLine();//pour consommer le retour a la ligne
                                if (newNumChambre>7 || newNumChambre<1)                          
                                    System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                                else if(newNumChambre<=7 && newNumChambre>=1){
                                    if (listeStatuts.get(newNumChambre-1).estReservee()) {
                                        System.out.println("Vous avez choisi une chambre actuellement revervee par un autre client. Veuillez en choisir une libre...");
                                        newNumChambre=0;
                                    }else{
                                        dansBoucle2=true;
                                        while (dansBoucle2) {
                                            printHeaderSysResa(listeStatuts);
                                            System.out.println("----------------------------------------");
                                            System.out.println("       Modification de reservation      ");
                                            System.out.println("----------------------------------------");
                                            System.out.println("Vous avez choisi de transferer la reservation vers la "+listeStatuts.get(newNumChambre-1).getTypeChambre()+".");
                                            System.out.println("Chambre de "+listeStatuts.get(newNumChambre-1).getTypeChambre().getSurface()+"m2, "+listeStatuts.get(newNumChambre-1).getTypeChambre().getNombre_Lits()+" lit(s) et coutant "+listeStatuts.get(newNumChambre-1).getTypeChambre().getPrix()+" euros par nuit.");
                                            System.out.println("Est-ce que c'est bon pour vous ?");
                                            System.out.println("1. Oui, confirmer la modification vers la chambre selectionnee");
                                            System.out.println("2. Non, choisir une autre chambre vers laquelle transferer la reservation");
                                            System.out.println("3. Ne finalement pas transferer de la reservation et revenir au menu principal");
                                            choix2=scan.nextInt();
                                            switch (choix2) {
                                                case 1:
                                                    StatutChambres.copyStatut(listeStatuts.get(numChoixChambre-1), listeStatuts.get(newNumChambre-1));
                                                    dansBoucle2=false;
                                                    dansBoucle1=false;
                                                    break;
                                                case 2:
                                                    dansBoucle2=false;
                                                    break;
                                                case 3:
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
                        case 2:
                            dansBoucle2=true;
                            while (dansBoucle2) {
                                printHeaderSysResa(listeStatuts);
                                System.out.println("----------------------------------------");
                                System.out.println("     Modification informations client   ");
                                System.out.println("----------------------------------------");
                                System.out.println("Prenom nom actuel de la reservation de la "+listeStatuts.get(numChoixChambre-1).getTypeChambre()+" : "+listeStatuts.get(numChoixChambre-1).getRelatedClient());
                                System.out.println("Veuillez entrer le nouveau prenom a associer a la reservation...");
                                newPrenomClient = scan.nextLine();
                                System.out.println("Veuillez entrer le nouveau nom a associer a la reservation...");
                                newNomClient = scan.nextLine();
                                System.out.println("Vous avez entrez "+ newPrenomClient +" "+ newNomClient +". Est-ce que c'est bon pour vous ?");
                                System.out.println("1. Oui, continuer...");
                                System.out.println("2. Non, modifier le nom et prenom qui remplacera "+listeStatuts.get(numChoixChambre-1).getRelatedClient()+"...");
                                choix=0;
                                while(choix != 1 && choix != 2)
                                {
                                    choix = scan.nextInt();
                                    if (choix==1) {
                                        dansBoucle2=false;
                                    }
                                    if (choix != 1 && choix != 2) {
                                        System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                                    }
                                }
                            }
                            String newRelatedClient = newPrenomClient+" "+newNomClient;//on colle les deux parties dans une nouvelle variable String
                            listeStatuts.get(numChoixChambre-1).setRelatedClient(newRelatedClient);//et on l'applique au relatedClient de la chambre
                            dansBoucle1=false;
                            break;
                        case 3:
                            dansBoucle2=true;
                            while (dansBoucle2) {
                                printHeaderSysResa(listeStatuts);
                                System.out.println("----------------------------------------");
                                System.out.println(" Modification nombre nuit(s) reservation");
                                System.out.println("----------------------------------------");
                                System.out.println("Veuillez entrer le nombre de nui(s) que vous voulez associer a cette reservation au lieu des "+listeStatuts.get(numChoixChambre-1).getNbrNuits()+" actuelle(s)...");
                                newNbrNuits=0;
                                if(newNbrNuits == 0)
                                {
                                    while (newNbrNuits>365 || newNbrNuits<1) 
                                    {
                                        System.out.println("Veuillez entrer le nombre de nuit(s) que vous souhaitez passer chez nous (entre 1 et 365)...");
                                        newNbrNuits = scan.nextInt();
                                        scan.nextLine();
                                        if (newNbrNuits>365 || newNbrNuits<1)                          
                                            System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                                    }
                                }
                                System.out.println("Vous avez choisi d'associer "+newNbrNuits+" nuit(s) a la reservation de la "+listeStatuts.get(numChoixChambre-1).getTypeChambre());
                                System.out.println("Est-ce que c'est bon pour vous ?");
                                System.out.println("1. Oui, continuer...");
                                System.out.println("2. Non, remodifier le nombre de nuit(s)...");
                                choix=0;
                                while(choix != 1 && choix != 2)
                                {
                                    choix = scan.nextInt();
                                    if (choix==1) {
                                        dansBoucle2=false;
                                    }
                                    if (choix != 1 && choix != 2) {
                                        System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                                    }
                                }
                            }
                            listeStatuts.get(numChoixChambre-1).setNbrNuits(newNbrNuits);
                            dansBoucle1=false;
                            break;
                        case 4:
                            dansBoucle2=true;
                            while (dansBoucle2) {

                                printHeaderSysResa(listeStatuts);
                                System.out.println("----------------------------------------");
                                System.out.println("       Modification de reservation      ");
                                System.out.println("----------------------------------------");
                                System.out.println("Quelle reservation souhaitez vous modifier ?");
                                System.out.println("Entrez un chiffre entre 1 et 7...");
                                choix=0;
                                while(choix<1 || choix>7)
                                {
                                    choix = scan.nextInt();
                                    scan.nextLine();
                                    if((choix>=1 && choix<=7))
                                    {
                                        if (listeStatuts.get(choix-1).estReservee()) 
                                        {
                                            numChoixChambre = choix;
                                            dansBoucle2=false;
                                        }else{
                                            System.out.println("La chambre selectionnee n'est pas reservee, veuillez en choisir une autre...");
                                            choix=0;
                                        }
                                    }else{
                                        System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                                        choix=0;
                                    }
                                }
                            }
                            break;
                        case 5:
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

    public static void menuReservationChambres(Scanner scan, List<StatutChambres> listeStatuts)
    {
        dansBoucle1 = true;
        printHeaderSysResa(listeStatuts);
        while (dansBoucle1) 
        {
            System.out.println("----------------------------------------");
            System.out.println("       Que souhaitez vous faire ?       ");
            System.out.println("----------------------------------------");
            if (chambresReservables>0)
                System.out.println("1. Effectuer une reservation");
            if (chambresReservables<7)
            {
                System.out.println("2. Annuler une reservation");
                System.out.println("3. Modifier une reservation");
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
                                System.out.println("Veuillez entrer le numero de la chambre que vous souhaitez reserver (entre 1 et 7)...");
                                numChambre = scan.nextInt();
                                scan.nextLine();//pour consommer le retour a la ligne
                                if (numChambre>7 || numChambre<1)                          
                                    System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                                else if(numChambre<=7 && numChambre>=1){
                                    if (listeStatuts.get(numChambre-1).estReservee()) {
                                        System.out.println("Vous avez choisi une chambre actuellement revervee par un autre client. Veuillez en choisir une libre...");
                                        numChambre=0;
                                    }
                                }
                            }
                        }
                        nbrNuits=0;
                        if(nbrNuits == 0)
                        {
                            while (nbrNuits>365 || nbrNuits<1) 
                            {
                                System.out.println("Veuillez entrer le nombre de nuit(s) que vous souhaitez passer chez nous (entre 1 et 365)...");
                                nbrNuits = scan.nextInt();
                                scan.nextLine();
                                if (nbrNuits>365 || nbrNuits<1)                          
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
                            choix=0;
                            while(choix != 1 && choix != 2)
                            {
                                choix = scan.nextInt();
                                scan.nextLine();
                                if (choix==1) {
                                    dansBoucle3=false;
                                }
                                if (choix != 1 && choix != 2) {
                                    System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                                }
                            }
                        }
                        System.out.println("Vous avez reserve la "+listeStatuts.get(numChambre-1).getTypeChambre()+" pour "+nbrNuits+" nuit(s) pour le client "+prenomClient +" "+ nomClient);
                        System.out.println("Est-ce que ceci vous convient ?");
                        System.out.println("1. Oui, valider la reservation et retourner au menu principal");
                        System.out.println("2. Non, recommencer la reservation en renseignant d'autres informations");
                        System.out.println("3. Non, annuler la reservation en cours de traitement et retourner au menu principal");
                        choix=0;
                        while(choix<1 || choix>3)
                        {
                            choix=scan.nextInt();
                            scan.nextLine();
                            switch (choix) {
                                case 1:
                                    listeStatuts.get(numChambre-1).setReservation(true);
                                    listeStatuts.get(numChambre-1).setNbrNuits(nbrNuits);
                                    listeStatuts.get(numChambre-1).setRelatedClient(prenomClient +" "+ nomClient);
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
                    if(chambresReservables==7)//si l'utilisateur appuie sur 2 meme alors que ca ne lui a pas ete propose
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
                            if((choix>=1 || choix<=7))
                            {
                                if (listeStatuts.get(choix-1).estReservee()) 
                                {
                                    System.out.println("Vous avez choisi la "+listeStatuts.get(choix-1).getTypeChambre()+", est-ce correct ?");
                                    System.out.println("1. Oui, annuler la reservation de cette chambre");
                                    System.out.println("2. Non, selectionner une autre chambre");
                                    System.out.println("3. Ne finalement pas annuler de reservation et retourner au menu principal");

                                    dansBoucle3=true;
                                    while (dansBoucle3) {
                                        choix2 = scan.nextInt();

                                        switch (choix2) {
                                            case 1:
                                                listeStatuts.get(choix-1).resetStatut();
                                                dansBoucle3=false;
                                                dansBoucle2=false;
                                                dansBoucle1=false;
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
                                }else{
                                    System.out.println("Vous avez selectionne une chambre libre --> aucune reservation a annuler. Veuillez recommencer...");
                                    choix=0;
                                }
                            }else{
                                System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                            }
                        }
                    }
                    break;
                case '3':
                    if(chambresReservables==7)//si l'utilisateur appuie sur 2 meme alors que ca ne lui a pas ete propose
                        break;//on sort du switch et on recommence la boucle
                    dansBoucle2=true;
                    while (dansBoucle2) {

                        printHeaderSysResa(listeStatuts);
                        System.out.println("----------------------------------------");
                        System.out.println("       Modification de reservation      ");
                        System.out.println("----------------------------------------");
                        System.out.println("Quelle reservation souhaitez vous modifier ?");
                        System.out.println("Entrez un chiffre entre 1 et 7...");
                        choix=0;
                        while(choix<1 || choix>7)
                        {
                            choix = scan.nextInt();
                            if((choix>=1 && choix<=7))
                            {
                                if (listeStatuts.get(choix-1).estReservee()) 
                                {
                                    System.out.println("Vous avez choisi la "+listeStatuts.get(choix-1).getTypeChambre()+", est-ce correct ?");
                                    System.out.println("1. Oui, modifier la reservation de cette chambre");
                                    System.out.println("2. Non, selectionner une autre chambre");
                                    System.out.println("3. Ne finalement pas modifier de reservation et retourner au menu principal");

                                    dansBoucle3=true;
                                    while (dansBoucle3) {
                                        choix2 = scan.nextInt();

                                        switch (choix2) {
                                            case 1:
                                                modifierResa(scan, listeStatuts, choix);
                                                dansBoucle3=false;
                                                dansBoucle2=false;
                                                dansBoucle1=false;
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
                                }else{
                                    System.out.println("Vous avez selectionne une chambre libre --> aucune reservation a modifier. Veuillez recommencer...");
                                    choix=0;
                                }
                            }else{
                                System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                            }
                        }
                    }
                    break;
                case '4':
                    dansBoucle1=false;
                    break;
                default:
                    System.out.println("Vous n'avez pas fait de choix correct, veuillez recommencer...");
                    break;
            }
        }
    }
}
