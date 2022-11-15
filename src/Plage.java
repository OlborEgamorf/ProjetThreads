package src;

import java.util.ArrayList;

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
    private Meteo meteo;

    Plage(int longueur, int largeur, int profondeur, double temperature, int vent, int mer, int nbMax) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.profondeur = profondeur;
        this.temperature = temperature;
        this.vent = (int) (Math.random() * 100);
        this.matrice = new Case[(int)longueur+mer][(int)largeur];
        for (int i=0;i<longueur+mer;i++) {
            for (int j=0;j<largeur;j++) {
                matrice[i][j] = new Case(i,j);
            }
        }
        this.mer = mer;
        setZones();
        this.threads = new Personne[nbMax];
        setMeteo();

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

    public Case[][] getMatrice() {return matrice;}
    
    public int getMer() {
        return mer;
    }

    public Personne[] getThreads() {
        return threads;
    }
    public Meteo getMeteo(){
        return meteo;
    }

    public void setMeteo(){
        int a= Meteo.values().length;
        int rang = (int) (Math.random() * a)+1;
        for (int i=0; i<Meteo.values().length; i++){
            if (Meteo.values()[i].getNombre() == rang){
                meteo = Meteo.values()[i];
            }
        }
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

    public void unpack(int x, int y, int id) {
        //desormais la case n'est plus vide.
        // on met la case a 2 + celles aux alentours

        for (int i=0; i<2; i++) {
            for (int j=0;j<3;j++) {
                matrice[x+i][y+j].setCase(id, Type.AFFAIRES);;
                //System.out.println("oui2Unpackboucles");
            }
        }
    }

    public boolean check6x6(int x, int y){
        for (int i=(x-3); i<(x+3); i++){
            for (int j=(y-3); j<(y+3); j++){
                if (!isFree(i, j))
                    return false;
            }
        }
        return true;
    }

    public void pack(int x, int y) {
        // La personne s'en va et remballe ses affaires
        for (int i=0; i<2; i++) {
            for (int j=0; j<3; j++) {
                matrice[x+i][y+j].setCase(0, Type.VIDE);
            }
        }
    }

    public void modifVision(Personne personne, int x, int y, int oldX, int oldY) {
        Case emplacement;
        int[][] vision = new int[3][3];

        for (int i=-1;i<2;i++) {
            for (int j=-1;j<2;j++) {

                if (x+i < 0 || y+j < 0 || x+i == longueur+mer || y+j == largeur) {

                } else {
                    emplacement = matrice[x+i][y+j];
                    if (emplacement.getType() != Type.VIDE && emplacement.getType() != Type.TEMPORAIRE) {
                        if (emplacement.getId() != personne.getIdPersonne()) {
                            vision[i+1][j+1] = 1; 
                        }
                    }
                    if (emplacement.getType() == Type.PERSONNE) {
                        int[] coords = emplacement.getCoords();
                        threads[emplacement.getId()].setVisionCase(Math.abs(x-coords[0]+1),Math.abs(y-coords[1]+1),1);
                        if (coords[0]-oldX+1 >= 0 && coords[0]-oldX+1 <= 2 && coords[1]-oldY+1 >= 0 && coords[1]-oldY+1 <= 2) {
                            threads[emplacement.getId()].setVisionCase(Math.abs(oldX-coords[0]+1),Math.abs(oldY-coords[1]+1),0);
                        }
                    }
                }
            }
        }
        personne.setVision(vision);
    }

    public void placementPlage(Personne personne){
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
                        matrice[x+l][y+m].setCase(personne.getIdPersonne(), Type.TEMPORAIRE);
                    }
                    return;
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

    public String fetchMatrice(){
        String s = "";
        for (int i=0;i<longueur+mer;i++) {
            for (int j=0;j<largeur;j++) {
                s+=matrice[i][j];
            }
            s+="\n";
        }
        return s;
    }

    public void turn() {
                
        int[] position;
        int[] oldPos;
        Etat etat;
        Personne personne;

        if (meteo == Meteo.Soleil){
            double a= Math.random();
            if (a<=0.01){
                meteo = Meteo.Nuageux;
            }
        }
        else if (meteo == Meteo.Nuageux){
            double a= Math.random();
            if (a<=0.01){
                meteo = Meteo.Pluie;
            } else if (a>=0.99) {
                meteo = Meteo.Soleil;
            }
        }
        else if (meteo == Meteo.Pluie){
            double a= Math.random();
            if (a<=0.01){
                meteo = Meteo.Nuageux;
            }
        }

        if (vent <= 25){
            double a= Math.random();
            if (a<0.1)
                vent = (int) (Math.random() * (40));
        } else if (vent<60) {
            double a= Math.random();
            if (a<0.1)
                vent = (int) (Math.random() * ((60-15)+15));
        } else {
            double a= Math.random();
            if (a<0.1)
                vent = (int) (Math.random() * ((100-45)+45));
        }
        
        for (int i = 0; i < threads.length; i++) {

            personne = threads[i];
            etat = personne.getEtat();
            position = personne.getPosition();

            if (personne.getAlive()) {

                if (meteo == Meteo.Pluie){
                    personne.changeAttribut(1,0.95);
                    personne.changeAttribut(2, 1.15);
                }

                if (vent >25 && vent<60){
                    personne.changeAttribut(2, 1.15);
                }
                else
                    personne.changeAttribut(2, 1.30);

                if (etat == Etat.MOUVEMENT || etat == Etat.BAIGNADE) {
        
                    oldPos = personne.getOldPosition();
    
                    if (position[0] != oldPos[0] || position[1] != oldPos[1]) {
    
                        if (matrice[position[0]][position[1]].getType() != Type.VIDE && matrice[position[0]][position[1]].getType() != Type.TEMPORAIRE && matrice[position[0]][position[1]].getId() != personne.getId()) {
                            // Si la personne s'est déplacé sur sa case avant
                            //System.out.println(matrice[actPos[0]][actPos[1]].type+" "+actPos[0]+" "+actPos[1]+" -- "+i);
                            personne.setPosition(oldPos);
                            modifVision(personne, oldPos[0], oldPos[1], oldPos[0], oldPos[1]);
                            System.out.println("DENIED");
                        } else {
                            // Si la personne peut aller sur la case
    
                            matrice[position[0]][position[1]].setCase(i, Type.PERSONNE);
                            matrice[oldPos[0]][oldPos[1]].setCase(-1, Type.VIDE);
    
                            modifVision(personne, position[0], position[1], oldPos[0], oldPos[1]);
                            personne.immobilisation();
                            //System.out.println((personne.getObjectif() == Objectif.BAIGNADE)+" "+position[0]+" "+longueur);
                            if (personne.getObjectif() == Objectif.BAIGNADE && position[0] == longueur) {
                                personne.setObjPosition(new int[]{longueur,position[1]});
                            }
                        }
                    } /*else {
                        System.out.println("IMMOBILE");
                    }*/
    
                } else if (etat == Etat.PLACEMENT) {
                    unpack(personne.getPositionPlage()[0], personne.getPositionPlage()[1], personne.getIdPersonne());
                } else if (etat == Etat.DEPART) {
                    pack(personne.getPositionPlage()[0], personne.getPositionPlage()[1]);
                } else if (etat == Etat.ARRIVEE) {
                    modifVision(personne,position[0],position[1],longueur+500,largeur+500);
                    placementPlage(personne);
                } else if (etat == Etat.ATTENTE) {

                } else if (etat == Etat.PARTI) {
                    personne.setAlive(false);
                    personne.interrupt();
                }

                personne.setOath(true);
            }
        }
    }
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