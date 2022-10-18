import java.util.ArrayList;
import java.util.Arrays;

public class Plage {

    private int longueur;
    private int largeur;
    private int profondeur;
    private Case[][] matrice;
    private double temperature;
    private int vent;
    private int mer;
    private int[] zones= setZones();
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
        setZones();
        this.threads = new Personne[nbMax];

        int coeff = 500; // coefficient de vitesse d'apparition, en ms
        for (int i = 0; i < threads.length; i++) {
            int[] posTest = {0,(int)(Math.floor(Math.random() * largeur))};
            threads[i] = new Personne(i,posTest,vent,coeff*i);
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

    /*
    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
    */


    public boolean isFree(int x, int y) {
        // Si la case est vide
        return matrice[x][y].getType() == Type.VIDE;
    }
    public int[] setZones(){
        zones = new int[3];
        zones[0]= this.longueur/3;
        zones[1]= 2*(this.longueur/3);
        zones[2]= this.longueur;
        return zones;
    }
    public void unpack(int x, int y) {
        //desormais la case n'est plus vide.
        // on met la case a 2 + celles aux alentours

        /*if (matrice[x][y-1].type == Type.VIDE && matrice[x][y-1].type == Type.VIDE && matrice[x][y-1].type == Type.VIDE && matrice[x][y-1].type == Type.VIDE && matrice[x][y-1].type == Type.VIDE) {
            return false;
        }*/

        for (int i=0; i<2; i++) {
            for (int j=0;j<3;j++) {
                matrice[x+i][y+j].type = Type.AFFAIRES;
                //System.out.println("oui2Unpackboucles");
            }
        }

        /*return true;*/
    }
        // La personne pose ses affaires, les cases vaudront 2

    public boolean check6x6(int x, int y){
        for (int i=(x-3); i<(x+3); i++){
            for (int j=(y-3); j<(y+3); j++){
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
                    if (emplacement.type != Type.VIDE && emplacement.type != Type.TEMPORAIRE) {
                        vision[i+1][j+1] = 1; 
                    }
                    if (emplacement.type == Type.PERSONNE) {
                        int[] coords = emplacement.getCoords();
                        threads[emplacement.id].setVisionCase(Math.abs(x-coords[0]+1),Math.abs(y-coords[1]+1),1);
                        if (coords[0]-oldX+1 >= 0 && coords[0]-oldX+1 <= 2 && coords[1]-oldY+1 >= 0 && coords[1]-oldY+1 <= 2) {
                            threads[emplacement.id].setVisionCase(Math.abs(oldX-coords[0]+1),Math.abs(oldY-coords[1]+1),0);
                        }
                    }
                }
            }
        }

        personne.setVision(vision);
    }

    public void placementPlage(Personne personne){
        /*for (int i= getZones().length-1; i>0; i--){*/
        int i= getZones().length-1;
        int t=0;
        while (i>0){
            int x = (int)(Math.random() * ((getZones()[i]-3)-(getZones()[i-1]+3))+(getZones()[i-1]+3));
            int y = (int)(Math.floor(Math.random() * (((getLargeur()-3)-(getLargeur()-(getLargeur()-3)))+(getLargeur()-(getLargeur()-3)))));
            while (y<3){
                y = (int)(Math.floor(Math.random() * (((getLargeur()-3)-(getLargeur()-(getLargeur()-3)))+(getLargeur()-(getLargeur()-3)))));
            }
            if (isFree(x,y) && (isFree(x,y-1) && isFree(x,y+1) && isFree(x-1,y) && isFree(x+1,y) && isFree(x+1,y+1)&& isFree(x+1,y-1)&& isFree(x-1,y+1)&& isFree(x-1,y-1))
                    && check6x6(x,y)){
                int [] pos= {x,y};
                personne.setPositionPlage(pos);
                int [] pos2= {x+3,y+3};
                personne.setObjPosition(pos2);
                for (int l=0; l<2; l++) {
                    for (int m=0;m<3;m++) {
                        //System.out.println("1: x: "+personne.getPositionPlage()[0]+" y: "+personne.getPositionPlage()[1]+ " x: "+personne.getObjPosition()[0]+ " y: "+personne.getObjPosition()[1]);
                        matrice[x+l][y+m].type = Type.TEMPORAIRE;
                    }
                    return ;
                }
            }
            else{
                t++;
                if (t== 15 && i>1){
                    t=0;
                    i--;
                }
            }

        }
    }

    public void turn() {
                
        int[] position;
        int[] oldPos;
        Etat etat;
        Personne personne;
        
        for (int i = 0; i < threads.length; i++) {

            personne = threads[i];
            etat = personne.getEtat();
            position = personne.getPosition();

            if (etat == Etat.MOUVEMENT) {
        
                oldPos = personne.getOldPosition();

                if (position != oldPos) {

                    if ((matrice[position[0]][position[1]].type != Type.VIDE) && (matrice[position[0]][position[1]].type != Type.TEMPORAIRE)) {
                        // Si la personne s'est déplacé sur sa case avant
                        //System.out.println(matrice[actPos[0]][actPos[1]].type+" "+actPos[0]+" "+actPos[1]+" -- "+i);
                        personne.setPosition(oldPos);
                    } else {
                        // Si la personne peut aller sur la case

                        matrice[position[0]][position[1]].setCase(i, Type.PERSONNE);
                        matrice[oldPos[0]][oldPos[1]].setCase(-1, Type.VIDE);

                        modifVision(personne, position[0], position[1], oldPos[0], oldPos[1]);
                        personne.immobilisation();
                    }
                }

            } else if (etat == Etat.PLACEMENT) {
                unpack(personne.getPositionPlage()[0], personne.getPositionPlage()[1]);
                personne.placementFini();
            } else if (etat == Etat.ARRIVEE) {
                modifVision(personne,position[0],position[1],longueur+500,largeur+500);
                placementPlage(personne);
                personne.placementDebut();
            }

            personne.setOath(true);
        }

         /*for (Personne pers : threads) {
            System.out.println(pers.getPosition()[0]+" "+pers.getPosition()[1]);
            int[][] vis = pers.getVision();
            for (int i=0;i<3;i++) {
                System.out.println(vis[i][0]+" "+vis[i][1]+" "+vis[i][2]);
            }
        }
        System.out.println("");
        System.out.println("------\n\n");*/
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