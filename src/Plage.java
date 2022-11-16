//package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Plage {

    private int longueur;
    private int largeur;
    private int profondeur;
    private double temperature;
    private int vent;
    private int mer;
    private int[] zones= setZones();
    private Personne[] threads;
    private ArrayList<Integer> sauveteurs;
    private Meteo meteo;

    Plage(int longueur, int largeur, int profondeur, double temperature, int vent, int mer, int nbMax) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.profondeur = profondeur;
        this.temperature = temperature;
        this.vent = (int) (Math.random() * 100);

        this.mer = mer;
        this.threads = new Personne[nbMax];

        setZones();
        setMeteo();

        int coeff = 500; // coefficient de vitesse d'apparition, en ms
        for (int i = 0; i < threads.length; i++) {
            if (Math.random() > 1000) {
                //threads[i] = new Sauveteur(i,new int[]{0,(largeur/2)+2},vent);
                sauveteurs.add(i);
            } else {
                double[] posTest = {0,Math.random() * largeur};
                threads[i] = new Personne(i,posTest,vent,coeff*i);
            }
            
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
    
    public int getMer() {
        return mer;
    }

    public Personne[] getThreads() {
        return threads;
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

    public Meteo getMeteo(){
        return meteo;
    }

    /*
    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
    */

    public int[] setZones(){
        zones = new int[3];
        zones[0] = this.longueur/3;
        zones[1] = 2*(this.longueur/3);
        zones[2] = this.longueur;
        return zones;
    }

    public void placementPlage(Personne personne){
        int z = getZones().length-1;
        int t = 0;
        while (z >= 0) {
            double x = (Math.random() * ((getZones()[z]-3) - (getZones()[z-1]+3)) + (getZones()[z-1]+3));
            double y = (Math.floor(Math.random() * (((getLargeur()-3) - (getLargeur() - (getLargeur()-3))) + (getLargeur() - (getLargeur()-3)))));
            
            while (y<3){
                y = (Math.floor(Math.random() * (((getLargeur()-3) - (getLargeur() - (getLargeur()-3))) + (getLargeur() - (getLargeur()-3)))));
            }

            Rectangle emplacement = new Rectangle(new double[]{x,y}, new double[]{x,y+1}, new double[]{x+1.7,y+1}, new double[]{x+1.7,y}, z); // dimensions serviette de plage

            boolean flag = true;
            for (int c=0; c<threads.length && flag; c++) {
                if (threads[c].isAlive() && threads[c].getPositionPlage() != null && c != personne.getIdPersonne()) {
                    Rectangle emp = threads[c].getPositionPlage();
                    if (emp.getZone() == z) {
                        flag = !emplacement.isIn(emp);
                    }
                }
            }

            if (flag) {
                personne.setPositionPlage(emplacement);
                personne.setObjPosition(emplacement.getCentre());
                z = -69;
            }
            else{
                t++;
                if (t == 15){
                    t=0;
                    z--;
                }
            }
        }
    }

    public int closerSave(double x, double y) {
        switch (sauveteurs.size()) {
            case 0: {
                return -1;
            }
            case 1: {
                return sauveteurs.get(0);
            }
            default: {
                int flag = -1;
                double min = 15000;
                for (int i=0;i<sauveteurs.size();i++) {
                    if (x-threads[i].getPosition()[0]+y-threads[i].getPosition()[1] < min && (threads[i].getEtat() != Etat.SAUVETAGE && threads[i].getObjectif() != Objectif.SAUVETAGE)) {
                        flag = i;
                        min = x-threads[i].getPosition()[0]+y-threads[i].getPosition()[1];
                    }
                }
                return flag;
            }
        }
    }

    public void turn() {
                
        double[] position;
        double[] objPosition;
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
            objPosition = personne.getObjPosition();

            if (personne.getAlive()) {
                if (etat == Etat.PATH) {
                    Vector vecteur = Vector.choixVector(position, objPosition, personne.getVitesse(), 0);

                    ArrayList<Coordonnees> liste = new ArrayList<>();
                    for (Personne compare : threads) {
                        if (compare.isAlive() && !compare.getStackMove().isEmpty()) {
                            Iterator<Vector> it = compare.getStackMove().iterator();
                            while (it.hasNext()) {
                                Vector vectorComp = it.next();
                                double[] coords = vecteur.isCroisement(vectorComp);
                                if (!Vector.isCoordsNull(coords)) {
                                    if (Vector.isCollision(vecteur.copy(),vectorComp.copy())) {
                                        liste.add(new Coordonnees(coords[0], coords[1]));
                                    };
                                }
                            }
                        }
                        if (compare.isPlaced()) {
                            
                        }
                    }
                    
                    if (liste.isEmpty()) {
                        personne.addVector(vecteur);
                    } else {
                        Collections.sort(liste);
                        if (vecteur.getSensX() == -1) {
                            Collections.reverse(liste);
                        }

                        //System.out.println(personne.getIdPersonne()+" P:"+position[0]+" "+position[1]+" O:"+objPosition[0]+" "+objPosition[1]);
                        ListIterator<Coordonnees> itLi = liste.listIterator();
                        while (itLi.hasNext()) {
                            double[] coordsPrevious;
                            if (itLi.hasPrevious()) {
                                coordsPrevious = itLi.previous().getCoords();
                                itLi.next();
                            } else {
                                coordsPrevious = position;
                            }
                            double[] coordsNext = itLi.next().getCoords();
                            
                            //System.out.println(personne.getIdPersonne()+" P:"+coordsPrevious[0]+" "+coordsPrevious[1]+" N:"+coordsNext[0]+" "+coordsNext[1]);
                            personne.addVector(Vector.choixVector(coordsPrevious, coordsNext, personne.getVitesse(), 1000));
                            if (!itLi.hasNext()) {
                                //System.out.println(personne.getIdPersonne()+" P:"+coordsNext[0]+" "+coordsNext[1]+" F:"+objPosition[0]+" "+objPosition[1]);
                                personne.addVector(Vector.choixVector(coordsNext, objPosition, personne.getVitesse(), 0));
                            }
                        }
                        
                    }
                
                } else if (etat == Etat.PLACEMENT) {
                    personne.setPlaced(true);
                } else if (etat == Etat.DEPART) {
                    personne.setPlaced(false);
                    personne.setPositionPlage(null);
                } else if (etat == Etat.ARRIVEE && personne.getPositionPlage() == null) {
                    placementPlage(personne);
                } else if (etat == Etat.ATTENTE) {

                } else if (etat == Etat.PARTI) {
                    personne.setAlive(false);
                    personne.interrupt();
                } else if (etat == Etat.NOYADE) {
                    int sauveteur = closerSave(position[0],position[1]);
                    if (sauveteur == -1) {
                        // S'il n'y a pas de sauveteur disponible, RIP !
                    } else {
                        /*Sauveteur saver = ((Sauveteur) threads[sauveteur]);
                        saver.sauvetage(position);
                        int[] simulPos = saver.getPosition();
                        do {
                            simulPos = saver.mouvement(simulPos[0], simulPos[1]);
                            matrice[simulPos[0]][simulPos[1]].setAlerte(true);
                        } while (!comparePositions(simulPos, position));*/
                    }
                } else if (etat == Etat.MOUVEMENT && personne.getObjectif() == Objectif.BAIGNADE) {
                    if (position[0] >= longueur) {
                        personne.setObjPosition(new double[]{position[0],position[1]});
                    }
                }
                personne.setOath(true);
            }
        }
    }

    public static boolean comparePositions(int[] position1, int[] position2) {
        return position1[0] == position2[0] || position1[1] == position2[1];
    }
}
