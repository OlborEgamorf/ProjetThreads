import java.time.LocalDateTime;
import java.util.ArrayList;

public class Plage {

    private int longueur;
    private int largeur;
    private int profondeur;
    private Case[][] matrice;
    private double temperature;
    private int vent;
    private int mer;
    private int[] zones;
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

        setZones();
        this.mer = mer;
        this.threads = new Personne[nbMax];

        for (int i = 0; i < threads.length; i++) {
            int[] posTest = {0,(int)(Math.floor(Math.random() * largeur))};
            threads[i] = new Personne(i,posTest,vent);
            threads[i].placement(longueur, largeur, mer);
            threads[i].start();
        }


    }
    public  int[] getZones(){
        return zones;
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
    
    public int getMer() {
        return mer;
    }

    public Personne[] getThreads() {
        return threads;
    }

    public boolean isFree(int x, int y) {
        // Si la case est vide
        return matrice[x][y].getType() == Type.VIDE;
    }
    public void setZones(){
        this.zones = new int[3];
        this.zones[0]= this.longueur/3;
        this.zones[1]= 2*(this.longueur/3);
        this.zones[2]= this.longueur;
    }
    public boolean unpack(int x, int y) {
        //desormais la case n'est plus vide.
        // on met la case a 2 + celles aux alentours

        if (matrice[x][y-1].type == Type.VIDE && matrice[x][y-1].type == Type.VIDE && matrice[x][y-1].type == Type.VIDE && matrice[x][y-1].type == Type.VIDE && matrice[x][y-1].type == Type.VIDE) {
            return false;
        }

        for (int i=0; i<2; i++) {
            for (int j=0;j<3;j++) {
                matrice[x+i][y+j].type = Type.AFFAIRES;
            }
        }

        return true;
    }
        // La personne pose ses affaires, les cases vaudront 2

    public boolean check4x4(int x, int y){
        for (int i=(x-2); i<(x+2); i++){
            for (int j=(y-2); j<(y+2); j++){
                if (!isFree(i, j))
                    return false;
            }
        }
        return true;
    }

    /* public void pack(int[][] coords) {
        // La personne s'en va et remballe ses affaires
        for (int i = 0;i< coords.length;i++) {
            matrice[coords[i][0]][coords[i][1]] = 0;
        }
    } */

    public static int[] coordToArray(int x, int y) {
        int[] liste = {x, y};
        return liste;
    }

    public void modifVision(Personne personne, int x, int y, int oldX, int oldY) {
        Case emplacement;
        int[][] vision = new int[3][3];

        for (int i=-1;i<2;i++) {
            for (int j=-1;j<2;j++) {

                if (x+i < 0 || y+j < 0 || x+i == longueur || y+j == largeur) {

                } else {
                    emplacement = matrice[x+i][y+j];
                    if (emplacement.type != Type.VIDE) {
                        vision[i+1][j+1] = 1; 
                    }
                    if (emplacement.type == Type.PERSONNE) {
                        int[] coords = emplacement.getCoords();
                        threads[emplacement.id].setVisionCase(coords[0]-x+1,coords[1]-y+1,1);
                        if (coords[0]-oldX+1 >= 0 && coords[0]-oldX+1 <= 2 && coords[1]-oldY+1 >= 0 && coords[1]-oldY+1 <= 2) {
                            threads[emplacement.id].setVisionCase(coords[0]-oldX+1,coords[1]-oldY+1,0);
                        }
                    }
                }
            }
        }

        personne.setVision(vision);
    }

    public void Placement(Personne personne){
        int[] zones= getZones();
        for (int i= zones.length-1; i> -1; i--){
            for (int j=zones[i]; j>zones[i-1]; j--){
                for (int k=0; k<getLargeur(); k++){
                    if (isFree(k,j) && (isFree(k,j-1) && isFree(k,j+1) && isFree(k-1,j) && isFree(k+1,j) && isFree(k+1,j+1)&& isFree(k+1,j-1)&& isFree(k-1,j+1)&& isFree(k-1,j-1))
                        && check4x4(k,j)){
                            unpack(k,j);
                            personne.setPositionPlage(k,j);
                        }
                    }
                }
            }
        }


    public void turn () {
                
        int[] actPos;
        int[] oldPos;
        Etat etat;
        Personne personne;
        
        for (int i = 0; i < threads.length; i++) {

            personne = threads[i];
            etat = personne.getEtat();
            if (etat == Etat.MOUVEMENT) {
            
                actPos = personne.getPosition();
                oldPos = personne.getOldPosition();

                if (actPos != oldPos) {

                    if (matrice[actPos[0]][actPos[1]].type != Type.VIDE) {
                        // Si la personne s'est déplacé sur sa case avant
                        System.out.println(matrice[actPos[0]][actPos[1]].type+" "+actPos[0]+" "+actPos[1]);
                        personne.setPosition(oldPos);
                    } else {
                        // Si la personne peut aller sur la case

                        matrice[actPos[0]][actPos[1]].setCase(i, Type.PERSONNE);
                        matrice[oldPos[0]][oldPos[1]].setCase(-1, Type.VIDE);

                        modifVision(personne, actPos[0], actPos[1], oldPos[0], oldPos[1]);
                        personne.immobilisation();
                    }
                }
            } else if (etat == Etat.PLACEMENT) {
                actPos = personne.getPosition();
                Placement(personne);

                /*if (!unpack(actPos[0], actPos[1])) {
                    personne.placement(longueur, largeur, mer);
                };*/
            }

            personne.setOath(true);
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