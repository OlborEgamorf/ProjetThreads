//package src;

import java.util.*;

public class Plage {

    private int longueur;
    private int largeur;
    private int profondeur;
    private double temperature;
    private int vent;
    private int mer;
    private int[] zones= setZones();
    private Personne[] threads;
    private ArrayList<Integer> sauveteurs = new ArrayList<Integer>();
    private Meteo meteo;
    private Coeff coeff;
    private Rectangle poste;
    private Vendeur vendeur = null;
    private ArrayList<Vague> vagues = new ArrayList<Vague>();
    private double[] attributsVagues = new double[3];; //[vitesse, hauteur, force]
    private double coeffVagues = 10;

    Plage(int longueur, int largeur, int profondeur, double temperature, int vent, int mer, int nbMax, Meteo meteo, Coeff coeff) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.profondeur = profondeur;
        this.temperature = temperature;
        this.vent = vent;
        this.mer = mer;
        this.threads = new Personne[nbMax];
        this.coeff = coeff;

        this.poste = new Rectangle(new double[]{longueur / 2 - 3, largeur / 2 + 5}, new double[]{longueur / 2 + 3, largeur / 2 + 5}, new double[]{longueur / 2 + 3, largeur / 2 - 5}, new double[]{longueur / 2 - 3, largeur / 2 - 5}, 2);

        setZones();
        this.meteo = meteo;
        setAttributsVagues();
        setVagues();

        int apparition = 5000; // coefficient de vitesse d'apparition, en ms
        for (int i = 0; i < threads.length; i++) {
            if (i == 0) {
                threads[i] = new Sauveteur(i, poste.getCentre(), vent, poste, coeff);
                sauveteurs.add(i);
            } else if (i == threads.length/2) {
                threads[i] = new Vendeur(i, new double[] {longueur-1, 1}, vent, new double[] {longueur-1, largeur}, coeff);
            } else {
                double[] posTest = {0, Math.random() * largeur};
                threads[i] = new Personne(i, posTest, vent, apparition * i, coeff);
            }
        }
        changeVitesseInitiale();

    }
    public void startAll() {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    public  int[] getZones(){
        return zones;
    }
    
    public Rectangle getPoste() {
        return poste;
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
    public ArrayList<Vague> getVagues(){
        return vagues;
    }
    public void setAttributsVagues(){
        if (meteo == Meteo.Soleil){
            this.attributsVagues[0] = 4;
            this.attributsVagues[1] = 0.1;
            this.attributsVagues[2] = 5;
        }
        else if (meteo == Meteo.Nuageux){
            this.attributsVagues[0] = 6;
            this.attributsVagues[1] = 0.3;
            this.attributsVagues[2] = 1.5;
            this.coeffVagues = 15;
        }
        else{
            this.attributsVagues[0] = 8;
            this.attributsVagues[1] = 0.8;
            this.attributsVagues[2] = 2;
            this.coeffVagues = 20;
        }
        if (vent > 25 && vent<60){
            this.attributsVagues[0] *= 1.5;
            this.attributsVagues[1] *= 1.5;
            this.attributsVagues[2] *= 1.5;
            this.coeffVagues /= 1.5;
        }
        else if (vent > 60){
            this.attributsVagues[0] *= 2;
            this.attributsVagues[1] *= 1.5;
            this.attributsVagues[2] *= 2;
            this.coeffVagues /= 2;
        }
    }
    public void setVagues(){
        double a = (mer/coeffVagues);
        int nbVagues = (int) ((mer/a)/2);
        System.out.println(coeffVagues);
        for (int i = 0; i<nbVagues; i++){
            vagues.add(new Vague(attributsVagues[1], attributsVagues[0], attributsVagues[2], 3,(longueur+(i*(mer/coeffVagues))*2)+2, longueur, longueur+mer, coeff));
        }
        for (int j = 0; j<vagues.size(); j++)
            vagues.get(j).start();
    }
    public void changeVitesseInitiale(){
        if (meteo == Meteo.Pluie){
            for (Personne thread : threads) {
                thread.changeAttribut(1, 0.80);
                thread.changeAttribut(2, 1.20);
            }
        }
        if (vent >25 && vent<60){
            for (Personne thread : threads) {
                thread.changeAttribut(2, 1.15);
            }
        }
        else if (vent >= 60){
            for (Personne thread : threads) {
                thread.changeAttribut(2, 1.30);
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
            double x = (Math.random() * ((getZones()[z]-3) - (getZones()[z-1]+3)) + (getZones()[z-1]+3)-5);
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

    /*public void attributsBaignade(Personne i, boolean entreOuSort){ //entre == true, sort == false
        if (entreOuSort){
            if (multVagues <= 3){
                i.changeAttribut(1, 1.1);
                i.changeAttribut(2, 1.1);
            }
            else if (multVagues <= 6){
                i.changeAttribut(1, 1.5);
                i.changeAttribut(2, 1.5);
            }
            else {
                i.changeAttribut(1, 2);
                i.changeAttribut(2, 2);
            }
        }
        else {
            if (multVagues <= 3){
                i.changeAttribut(1, 1/1.1);
                i.changeAttribut(2, 1/1.1);
            }
            else if (multVagues <= 6){
                i.changeAttribut(1, 1/1.5);
                i.changeAttribut(2, 1/1.5);
            }
            else {
                i.changeAttribut(1, 0.5);
                i.changeAttribut(2, 0.5);
            }
        }
        i.setAttributsBaignade(entreOuSort);
    }*/

    public void turn() {
        for (int i = 0; i<vagues.size(); i++) {
            for (int j = 0; j<threads.length; j++) {
                if (threads[j].vecteurCourant != null && !threads[j].getAttributsvague() && ((int) threads[j].position[0] == vagues.get(i).getPositionY() || (int) (threads[j].position[0] + 1) == vagues.get(i).getPositionY() || (int) (threads[j].position[0] - 1) == vagues.get(i).getPositionY())) {
                    threads[j].changeAttribut(1, 0.5);
                    threads[j].changeAttribut(2, 1.5);
                    threads[j].setAttributsVague(true);
                } else if (threads[j].vecteurCourant != null && threads[j].getAttributsvague()) {
                    threads[j].changeAttribut(1, 1 / 0.5);
                    threads[j].changeAttribut(2, 1 / 1.5);
                    threads[j].setAttributsVague(false);
                }
            }
        }
        
        for (int i = 0; i < threads.length; i++) {

            Personne personne = threads[i];
            Etat etat = personne.getEtat();
            double[] position = personne.getPosition();
            double[] objPosition = personne.getObjPosition();
            Objectif objectif = personne.getObjectif();




            if (personne.getAlive()) {

                if (etat == Etat.PATH && personne.getVecteurCourant() == null) {
                    try {
                        double vitesse = objectif==Objectif.NAGE?personne.getVitesseNage():personne.getVitesse();
                        //System.out.println(vitesse);

                        if (objectif == Objectif.PATROUILLE) {
                            objPosition = new double[]{Math.random() * longueur,Math.random() * largeur};
                            //System.out.println(objPosition[0]+" "+objPosition[1]);
                        } else if (objectif == Objectif.BAIGNADE) {
                            objPosition = new double[]{longueur,position[1]};
                        } else if (objectif == Objectif.NAGE) {
                            objPosition = new double[]{longueur+10 + Math.random()*(mer-10),position[1]};
                        }

                        if (objectif == Objectif.SAUVETAGE) {
                            Vector vecteur = Vector.choixVector(position, objPosition, 0.055555555, coeff, 0);
                            personne.addVector(vecteur);
                        } else {
                            Vector vecteur = Vector.choixVector(position, objPosition, vitesse, coeff, 0);
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
                                            }
                                        }
                                    }
                                }

                                if (compare.isPlaced()) {}

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
                                    personne.addVector(Vector.choixVector(coordsPrevious, coordsNext, personne.getVitesse(), coeff, 2000));
                                    if (!itLi.hasNext()) {
                                        //System.out.println(personne.getIdPersonne()+" P:"+coordsNext[0]+" "+coordsNext[1]+" F:"+objPosition[0]+" "+objPosition[1]);
                                        personne.addVector(Vector.choixVector(coordsNext, objPosition, personne.getVitesse(), coeff, 0));
                                    }
                                }
                            }
                        }                
        
                    } catch (ConcurrentModificationException exc) {}
                    
                } else if (etat == Etat.PLACEMENT) {
                    personne.setPlaced(true);
                } else if (etat == Etat.DEPART) {
                    personne.setPlaced(false);
                    personne.setPositionPlage(null);
                } else if (etat == Etat.ARRIVEE && personne.getPositionPlage() == null) {
                    placementPlage(personne);
                } else if (etat == Etat.ACHETER && vendeur != null) {
                    personne.setObjPosition(new double[]{vendeur.position[0]-1, vendeur.position[1]});
                } else if (etat == Etat.ATTENTE) {

                } else if (etat == Etat.PARTI) {
                    personne.setAlive(false);
                    personne.interrupt();
                } else if (etat == Etat.NOYADE) {
                    int sauveteur = closerSave(position[0],position[1]);
                    if (sauveteur == -1) {
                        // S'il n'y a pas de sauveteur disponible, RIP !
                    } else {
                        Sauveteur saver = ((Sauveteur) threads[sauveteur]);
                        saver.sauvetage(position);
                    }
                } else if (etat == Etat.MOUVEMENT) {
                    if (objectif == Objectif.SAUVETAGE && position[0] >= longueur && personne.getVecteurCourant().getVitesse() != 0.0213) {
                        personne.getVecteurCourant().setVitesse(0.0213);
                    } else if (objectif == Objectif.REPOS && position[0] <= longueur && personne.getVecteurCourant().getVitesse() != personne.getVitesse()) {
                        Iterator<Vector> it = personne.getStackMove().iterator();
                        while (it.hasNext()) { 
                            it.next().setVitesse(personne.getVitesse());
                        }
                    }
                }
                personne.setOath(true);
            }
        }
    }
}
