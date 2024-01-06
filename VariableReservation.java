import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VariableReservation implements Serializable{
    protected Chambres typeChambre;       // Type de chambre base sur l'enumeration Chambres
    protected boolean estReservee;        // Indicateur de reservation
    protected String relatedClient;       // Nom du client relie à la reservation
    protected List<Repas> repasCommandes; // repas commandes
    protected int nbrNuits;               // Nombre de nuits de la reservation
    protected int prixReservation;        // prix total de la réservation


    //Constructeur
    public VariableReservation(Chambres typeChambre) {
        this.typeChambre = typeChambre;
        estReservee = false;
        relatedClient = null;
        repasCommandes = new ArrayList<>();
        nbrNuits = 0;
        prixReservation = 0;
    }
}