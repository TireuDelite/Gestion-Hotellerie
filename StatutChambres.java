import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatutChambres implements Serializable {
    private Chambres typeChambre;       // Type de chambre base sur l'enumeration Chambres
    private boolean estReservee;        // Indicateur de reservation
    private String relatedClient;       // Nom du client relie à la reservation
    private List<Repas> repasCommandes; // repas commandes
    private int nbrNuits;               // Nombre de nuits de la reservation
    private int prixReservation;        // prix total de la réservation

    // Constructeur
    public StatutChambres(Chambres typeChambre) {
        this.typeChambre = typeChambre;
        estReservee = false; // Initialement non reservee
        relatedClient = null;
        repasCommandes = new ArrayList<>();
        nbrNuits = 0;
        prixReservation = 0;
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

    // Getter pour prixReservation
    public int getPrixReservation() {
        prixReservation=0;
        prixReservation = nbrNuits * typeChambre.getPrix();
        for (Repas repas : repasCommandes)
        {
            prixReservation = prixReservation + repas.getPrixPlat();
        }
        return prixReservation;
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
        estReservee = false; // Remet la reservation à non reservee
        relatedClient = null; // Supprime le client lie
        repasCommandes.clear(); // Efface la liste des repas commandes
        nbrNuits = 0; // Remet le nombre de nuits à 0
    }

    public static void copyStatut(StatutChambres oldChambre, StatutChambres newChambre) {
        newChambre.estReservee = oldChambre.estReservee;
        newChambre.relatedClient = oldChambre.relatedClient;
        newChambre.repasCommandes = oldChambre.repasCommandes;
        newChambre.nbrNuits = oldChambre.nbrNuits;
        oldChambre.resetStatut();
    }
}