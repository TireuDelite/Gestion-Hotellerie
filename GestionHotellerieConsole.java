import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GestionHotellerieConsole {

    public static boolean continuProgram = true;
    static Chambres[] chambres = Chambres.values();
    static List<StatutChambres> listeStatuts = new ArrayList<>(); //liste d'objets StatutChambres contenant les reservations

    @SuppressWarnings("unchecked")//pour qu'il enleve le warning lors de la deserialisation car on ne check pas le type de ce qu'on deserialise
    public GestionHotellerieConsole() {
        
        listeStatuts.clear(); //on clear la liste pour etre sûr qu'elle est vide de base
        //verification de l'existence du fichier etatChambres.ser
        if (new File("etatChambres.ser").exists())//si le fichier de sauvegarde existe
        {//code de deserialisation --> on recupère les informations de la sauvegarde pour les remettre dans la listeStatuts
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("etatChambres.ser"))){
                listeStatuts = (List<StatutChambres>)ois.readObject(); // ecriture du contenu du fichier dans la liste
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{//si le fichier n'existe pas
            // Initialisation standard des chambres en "non reserve"
            for (Chambres chambre : chambres)//pour toutes les chambres de l'enum
            {
                StatutChambres statut = new StatutChambres(chambre);//on creer un objet StatutChambres pour chaque chambre de l'enum
                statut.setReservation(false);//on set chaque objet à false
                listeStatuts.add(statut);//puis on ajoute l'objet statut à listeStatuts
            }
            //une fois que la listeStatut est initialise on serialise une premiere fois la listeStatut
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("etatChambres.ser")))
            {
                oos.writeObject(listeStatuts); // ecriture de l'etat de listeStatut dans le fichier etatChambres.ser
            } catch (IOException e) {
                e.printStackTrace();
            }
            /* NOTABENE:On a pas besoin de fermer les flux d'entree et de sortie (ObjectInputStream / ObjectOutputStream) 
             *          quand on l'ecrit sous la forme try-with-resources (comme ci-dessus) car le flux va se fermer tout seul. si on 
             *          initialisait l'objet oos ou ois avant le try, il faudrait rajouter d'autres lignes avec un
             *          "finally" et en appelant une metthode pour cloturer le flux de donnee.
             */
        }
        Scanner scan = new Scanner(System.in);
        while (continuProgram) 
        {
            // Affichage menu principal
            System.out.println("----------------------------------------");
            System.out.println("Bienvenue dans votre logiciel de gestion");
            System.out.println("----------------------------------------");
            System.out.println("Veuillez faire votre choix :");
            System.out.println("1. Afficher le detail des chambres");
            System.out.println("2. Afficher la disponibilite des chambres");
            System.out.println("3. Gestion des reservations");
            System.out.println("4. Commander un repas");
            System.out.println("5. Enregistrer une facture");
            System.out.println("6. Quitter");

            String getchoixMenu = scan.next();

            if (getchoixMenu.length() >= 0) { // L'utilisateur doit rentrer au moins 1 caractères
                switch (getchoixMenu.charAt(0)) { // Ici on prends uniquement le premier caractère que l'utilisateur entre
                case '1': // 
                    Clear.clear();
                    System.out.println("----------------------------------------");
                    System.out.println("            Detail des chambres         ");
                    System.out.println("----------------------------------------");
                    for (Chambres chambre : chambres) { // Boucle For each qui récupère Surface, nombre_lits et Prix 

                        int surfaceChambres = chambre.getSurface();
                        int nbr_lits_Chambres = chambre.getNombre_Lits();
                        int prix_chambres = chambre.getPrix();

                        System.out.println("La "+ chambre.name() +" à une surface de "+ surfaceChambres +"m² puis elle a "+ nbr_lits_Chambres +" lits et coute "+ prix_chambres +" \u20AC par nuit");
                    }
                    break;
                    
                case '2':
                    Clear.clear();
                    System.out.println("----------------------------------------");
                    System.out.println("        Disponibilite des chambres      ");
                    System.out.println("----------------------------------------");
                    for (StatutChambres statut : listeStatuts) { // Boucle For each qui récupère la lsites des chambre ainsi que leurs status (Est reservée)
                        System.out.println("La chambre "+ statut.getTypeChambre() +" est reservee ? --> "+statut.estReservee());
                    }
                    break;

                case '3':
                    // Appelle vers la fonction menuReservationChambres
                    Reservation.menuReservationChambres(scan, listeStatuts);
                    Clear.clear();
                    break;
                case '4':
                    // Appelle vers la fonction Menu_Repas
                    Clear.clear();
                    Menu.Menu_Repas(scan, listeStatuts);
                    break;
                case '5':
                    // Appelle vers la fonction Menu Facturation
                    Facturation.Menu_Facturation(scan, listeStatuts);
                    Clear.clear();
                    break;
                case '6':
                    System.out.println("Aurevoir !!");
                    continuProgram = false;
                    //avant de quitter le programme, on enregistre l'etat de reservation des chambres avec la serialisation :
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("etatChambres.ser")))
                    {
                        oos.writeObject(listeStatuts); // ecriture de l'etat dans le fichier
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //System.exit(-1);
                    break;

                default: // si l'utilisateur renseigne un nombre non compris entre 1 et 6 alors affichage de l'erreur
                    System.out.println("Erreur, veuillez choisir un des menus proposees");
                    break;
                }
            }
        }
        scan.close(); // Fermeture du Scanner afin de liberer la mémoire
    }


    public static void main(String[] args)
    {
        new GestionHotellerieConsole();
    }
}