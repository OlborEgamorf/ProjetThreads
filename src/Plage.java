package src;
public class Plage {

    private int longueur;
    private int largeur;
    private int profondeur;
    private int[][] matrice;
    private double temperature;
    private int vent;
    private int mer;

    Plage(int longueur, int largeur, int profondeur, double temperature, int vent, int mer) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.profondeur = profondeur;
        this.temperature = temperature;
        this.vent = vent;
        this.matrice = new int[(int)largeur][(int)largeur];
        this.mer = mer;

    }
    public int getLongueur() {
        return longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getProfondeur() {
        return profondeur;
    }

    public int[][] getMatrice() { return matrice; }
    
    public int mer() {
        return mer;
    }

    public boolean isFree(int x, int y) {
        // Si la case est vide
        if (matrice[x][y] == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void move(int[] from, int[] to) {
        // La personne bouge de from vers to
        matrice[from[0]][from[1]] = 0;
        matrice[to[0]][to[1]] = 1;
    }

    public void unpack(int x, int y) {
        // La personne pose ses affaires, les cases vaudront 2
    }

    public void pack(int[][] coords) {
        // La personne s'en va et remballe ses affaires
        for (int i = 0;i< coords.length;i++) {
            matrice[coords[i][0]][coords[i][1]] = 0;
        }
    }

    public static int[] coordToArray(int x, int y) {
        int[] liste = {x, y};
        return liste;
    }


}





