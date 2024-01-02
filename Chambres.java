public enum Chambres {

    Chambre_1(12, 1, 50),
    Chambre_2(13, 1, 53),
    Chambre_3(25, 2, 71),
    Chambre_4(32, 2, 83),
    Chambre_5(46, 2, 102),
    Chambre_6(96, 3, 186),
    Chambre_7(24, 1, 69);

    private int Surface;
    private int Nombre_Lits;
    private int Prix;

    public int getSurface() {
        return Surface;
    }

    public int getNombre_Lits() {
        return Nombre_Lits;
    }

    public int getPrix() {
        return Prix;
    }
    
    private Chambres (int Surface, int Nombre_Lits, int Prix) {
        this.Surface = Surface;
        this.Nombre_Lits = Nombre_Lits;
        this.Prix = Prix;
    }

}

