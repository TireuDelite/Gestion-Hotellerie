public class StatutChambres {
    private Chambres typeChambre; // Type de chambre basé sur l'énumération Chambres
    private boolean estReservee;  // Indicateur de réservation

    // Constructeur
    public StatutChambres(Chambres typeChambre) {
        this.typeChambre = typeChambre;
        this.estReservee = false; // Initialement non réservée
    }

    // Getter pour typeChambre
    public Chambres getTypeChambre() {
        return typeChambre;
    }

    // Getter pour estReservee
    public boolean estReservee() {
        return estReservee;
    }

    // Setter pour estReservee
    public void setReservation(boolean estReservee) {
        this.estReservee = estReservee;
    }
}