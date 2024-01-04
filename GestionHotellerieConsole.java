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
        //vérification de l'existence du fichier etatChambres.ser
        if (new File("etatChambres.ser").exists())//si le fichier de sauvegarde existe
        {//code de déserialisation --> on récupère les informations de la sauvegarde pour les remettre dans la listeStatuts
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("etatChambres.ser"))){
                listeStatuts = (List<StatutChambres>)ois.readObject(); // ecriture du contenu du fichier dans la liste
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{//si le fichier n'existe pas
            // Initialisation standard des chambres en "non réservé"
            for (Chambres chambre : chambres)//pour toutes les chambres de l'enum
            {
                StatutChambres statut = new StatutChambres(chambre);//on créer un objet StatutChambres pour chaque chambre de l'enum
                statut.setReservation(false);//on set chaque objet à false
                listeStatuts.add(statut);//puis on ajoute l'objet statut à listeStatuts
            }
            //une fois que la listeStatut est initialisé on serialise une premiere fois la listeStatut
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("etatChambres.ser")))
            {
                oos.writeObject(listeStatuts); // ecriture de l'etat de listeStatut dans le fichier etatChambres.ser
            } catch (IOException e) {
                e.printStackTrace();
            }
            /* NOTABENE:On a pas besoin de fermer les flux d'entrée et de sortie (ObjectInputStream / ObjectOutputStream) 
             *          quand on l'écrit sous la forme try-with-resources (comme ci-dessus) car le flux va se fermer tout seul. si on 
             *          initialisait l'objet oos ou ois avant le try, il faudrait rajouter d'autres lignes avec un
             *          "finally" et en appelant une metthode pour cloturer le flux de donnée.
             */
        }
        Scanner scan = new Scanner(System.in);
        while (continuProgram) 
        {

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

            String getchoixMenu = scan.next();

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
                    for (StatutChambres statut : listeStatuts) {
                        System.out.println("La chambre "+ statut.getTypeChambre() +" est reservee ? --> "+statut.estReservee());
                    }
                    break;
                case '3':

                    System.out.println("----------------------------------------");
                    System.out.println("         Système de réservation         ");
                    System.out.println("----------------------------------------");
                    System.out.println("");
                    System.out.println("1. Effectuer une réservation");
                    System.out.println("2. Revenir au menu principal");
                    System.out.println("Contenu de la liste");

                    Chambres chambreAModifier = Chambres.Chambre_1;//on definit la chambre pour laquelle on veut modifier le statut de reservation

                    for (StatutChambres statut : listeStatuts) {//pour tout les objets StatutChambres dans listeStatuts qu'on appelera "statut"
                        if (statut.getTypeChambre() == chambreAModifier) {//si le typeChambre de statut est egal à la chambre à modifier
                            statut.setReservation(true);//alors on set la valeur de réservation a true
                        }
                        System.out.println(statut.estReservee());//ici on affiche juste le statut de reservation
                    }
                    //cette boucle for sert surtout au cas où les statut de chambre dans listeStatuts ne seraient pas dans le bon ordre
                    //mais meme si il y a peu de chance qu'ils soient désordonnés, il est préférable de faire cette vérification

                    break;
                case '4':
                    String plats_commandes = Menu.Menu_Repas(scan);
                    System.out.println(plats_commandes);
                    break;
                case '5':
                    System.out.println("----------------------------------------");
                    System.out.println("        Enregistrement de Facture       ");
                    System.out.println("----------------------------------------");

                    break;
                case '6':
                    System.out.println("Aurevoir !!");
                    continuProgram = false;
                    //avant de quitter le programme, on enregistre l'état de reservation des chambres avec la serialisation :
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("etatChambres.ser")))
                    {
                        oos.writeObject(listeStatuts); // ecriture de l'etat dans le fichier
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //System.exit(-1);
                    break;
                default:
                    System.out.println("Erreur, veuillez choisir un des menus proposées");
                    break;
                }
            }
        }
        scan.close();
    }


    public static void main(String[] args)
    {
        new GestionHotellerieConsole();
    }
}