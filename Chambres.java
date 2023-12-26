public enum Chambres {
    Chambre_1(12, 1),
    Chambre_2(13, 1),
    Chambre_3(25, 2),
    Chambre_4(32, 2),
    Chambre_5(46, 2),
    Chambre_6(96, 3),
    Chambre_7(24, 1);

    private int Surface;
    private int Nombre_Lits;

    public int getSurface() {
        return Surface;
    }

    public int getNombre_Lits() {
        return Nombre_Lits;
    }

    private Chambres (int surface, int Nombre_Lits) {
        this.Surface = Surface;
        this.Nombre_Lits = Nombre_Lits;
    }

}

