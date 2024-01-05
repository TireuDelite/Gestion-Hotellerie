import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatutChambres implements Serializable {
    private Chambres typeChambre;       // Type de chambre basé sur l'énumération Chambres
    private boolean estReservee;        // Indicateur de réservation
    private String relatedClient;       // Nom du client relié à la réservation
    private List<Repas> repasCommandes;  // repas commandés
    private int nbrNuits;               // Nombre de nuits de la réservation

    // Constructeur
    public StatutChambres(Chambres typeChambre) {
        this.typeChambre = typeChambre;
        estReservee = false; // Initialement non réservée
        relatedClient = null;
        repasCommandes = new ArrayList<>();
        nbrNuits = 0;
    }

    // Getter pour typeChambre
    public Chambres getTypeChambre() {
        return typeChambre;
    }

    // Getter pour estReservee
    public boolean estReservee() {
        return estReservee;
    }

    // Getter pour relatedClient
    public String getRelatedClient() {
        if(estReservee == true)
            return relatedClient;
        else
        {
            return "NOT RESERVED";
        }
    }

    // Getter pour repasCommandes
    public List<Repas> getRepasCommandes() {
        return repasCommandes;
    }

    // Getter pour nbrNuits
    public int getNbrNuits() {
        return nbrNuits;
    }


    // Setter pour estReservee
    public void setReservation(boolean estReservee) {
        this.estReservee = estReservee;
    }

    // Setter pour relatedClient
    public void setRelatedClient(String relatedClient) {
        this.relatedClient = relatedClient;
    }

    // Setter pour repasCommandes
    public void addRepasCommandes(Repas repas) {
        repasCommandes.add(repas);
    }

    // Setter pour nbrNuits
    public void setNbrNuits(int nbrNuits) {
        this.nbrNuits = nbrNuits;
    }

    public void resetStatut() {
        estReservee = false; // Remet la réservation à non réservée
        relatedClient = null; // Supprime le client lié
        repasCommandes.clear(); // Efface la liste des repas commandés
        nbrNuits = 0; // Remet le nombre de nuits à 0
    }
}