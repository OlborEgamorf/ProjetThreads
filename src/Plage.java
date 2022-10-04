import java.util.ArrayList;

public class Plage {

    private int longueur;
    private int largeur;
    private int profondeur;
    private Case[][] matrice;
    private double temperature;
    private int vent;
    private int mer;
    private Personne[] threads;
    private ArrayList<Integer> intoWater;

    Plage(int longueur, int largeur, int profondeur, double temperature, int vent, int mer, int nbMax) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.profondeur = profondeur;
        this.temperature = temperature;
        this.vent = vent;
        this.matrice = new Case[(int)longueur][(int)largeur];
        for (int i=0;i<longueur;i++) {
            for (int j=0;j<largeur;j++) {
                matrice[i][j] = new Case(i,j);
            }
        } 
        this.mer = mer;
        this.threads = new Personne[nbMax];

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

    public Case[][] getMatrice() { return matrice; }
    
    public int mer() {
        return mer;
    }

    public boolean isFree(int x, int y) {
        // Si la case est vide
        return matrice[x][y].getType() == Type.VIDE;
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

    public void modifVision(Personne personne, int x, int y, int oldX, int oldY) {
        Case emplacement;
        int[][] vision = new int[3][3];

        for (int i=x-1;i<x+2;i++) {
            for (int j=y-1;j<j+2;j++) {

                emplacement = matrice[i][j];
                if (emplacement.type != Type.VIDE) {
                    vision[(int)(i/(x-1))][(int)(j/(y-1))] = 1; 
                }
                if (emplacement.type == Type.PERSONNE) {
                    threads[emplacement.id].setVisionCase(i-1-x,j-1-y,1);
                    threads[emplacement.id].setVisionCase(i-1-oldX,j-1-oldY,0);
                }
            }
        }

        personne.setVision(vision);
    }

    public void start () throws InterruptedException {
        int[] posTest = {0,1};
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Personne(i,posTest,vent);
            threads[i].start();
        }
        
        int[] actPos;
        int[] oldPos;
        Etat etat;
        Personne personne;
        
        while (true) {
            for (int i = 0; i < threads.length; i++) {
                personne = threads[i];
                etat = personne.getEtat();
                if (etat == Etat.MOUVEMENT) {
                    actPos = personne.getPosition();
                    oldPos = personne.getOldPosition();

                    if (actPos != oldPos) {
                        if (matrice[actPos[0]][actPos[1]].type != Type.VIDE) {
                            // Si la personne s'est déplacé sur sa case avant
                            personne.setPosition(oldPos);
                        } else {
                            // Si la personne peut aller sur la case
                            matrice[actPos[0]][actPos[1]].setCase(i, Type.PERSONNE);
                            matrice[oldPos[0]][oldPos[1]].setCase(-1, Type.VIDE);
                            modifVision(personne, actPos[0], actPos[1], oldPos[0], oldPos[1]);
                        }
                    }
                }
            }
            Thread.sleep(100);
        }
    }
    
}


// 0 0 0 0 0 0 0 0 0 0
// 0 0 0 0 0 0 0 0 0 0
// 0 0 0 0 1 0 0 0 0 0
// 0 0 0 0 0 0 0 0 0 0
// 0 0 0 0 0 0 0 0 0 0


// x = 200 | i = 201


    // x,y = 24,26 | i,j = 25,25 | expect : 0,2
    // 0 0 1 0
    // 0 1 0 0
    // 0 0 0 0