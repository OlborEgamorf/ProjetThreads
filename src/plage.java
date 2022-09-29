package environnement;

public class plage {

    private double longueur;
    private double largeur;
    private double profondeur;

    plage(double longueur, double largeur, double profondeur) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.profondeur = profondeur;

    }
    public double getLongueur() {
        return longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public double getProfondeur() {
        return profondeur;
    }

    public static int[] coordToArray(int x, int y) {
        int[] liste = {x, y};
        return liste;
    }


}





