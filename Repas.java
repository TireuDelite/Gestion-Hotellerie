public enum Repas {

    Numero_1("Steak Frites", 9),
    Numero_2("Lasagnes", 10),
    Numero_3("Salade Cesar", 8),
    Numero_4("Hamburger", 9),
    Numero_5("Spaghetti Bolognaise", 6);

    private String Plat;
    private int Prix;

    public String getPlat() {
        return Plat;
    }

    public int getPrixPlat() {
        return Prix;
    }

    private Repas (String Plat, int Prix) {
        this.Plat = Plat;
        this.Prix = Prix;
    }

    public String toString() {
        return "Plat: "+ this.Plat + ", Prix: " + this.Prix + "\u20AC";
    }
}
