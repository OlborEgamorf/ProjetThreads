//package src;

import java.util.*;

public class Plage {

    private int longueur;
    private int largeur;
    private int vent;
    private int mer;
    private int[] zones= setZones();
    private Personne[] threads;
    private ArrayList<Integer> sauveteurs = new ArrayList<Integer>();
    private Meteo meteo;
    private Rectangle poste;
    private Vendeur vendeur = null;
    private ArrayList<Vague> vagues = new ArrayList<Vague>();
    private double[] attributsVagues = new double[3];; //[vitesse, hauteur, force]
    private double coeffVagues = 10;
    private ArrayList<Rectangle> placements = new ArrayList<Rectangle>();

    Plage(int longueur, int largeur, int vent, int mer, int nbMax, Meteo meteo) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.vent = vent;
        this.mer = mer;
        this.threads = new Personne[nbMax];

        //this.poste = new Rectangle(new double[]{longueur/2-20,largeur/2+10}, new double[]{longueur/2+20,largeur/2+10}, new double[]{longueur/2+20,largeur/2-10}, new double[]{longueur/2-20,largeur/2-10}, 2,-1);

        //System.out.println(poste.getA()[0]+" "+poste.getA()[1]+" | "+poste.getB()[0]+" "+poste.getB()[1]+" | "+poste.getC()[0]+" "+poste.getC()[1]+" | "+poste.getD()[0]+" "+poste.getD()[1]+" | ");


        //placements.add(poste);

        this.poste = new Rectangle(3,2,5,0, 2,-1);

        placements.add(new Rectangle(5,8,8,5, mer, nbMax));
        placements.add(new Rectangle(15,20,20,15, mer, nbMax));
        placements.add(new Rectangle(40,55,50,40, mer, nbMax));

        setZones();
        this.meteo = meteo;
        setAttributsVagues();
        setVagues();

        int apparition = 5000; // coefficient de vitesse d'apparition, en ms
        for (int i = 0; i < threads.length; i++) {
            if (i == 90909090) {
                threads[i] = new Sauveteur(i,poste.getCentre(),vent,poste);
                sauveteurs.add(i);
            } else if (i == 666) {
                threads[i] = new Vendeur(i, new Coordonnees(longueur-1, 1), vent, new Coordonnees(longueur-1, largeur));
            } else {
                Coordonnees posTest = new Coordonnees(0, largeur * Math.random());
                threads[i] = new Personne(i,posTest,vent,apparition*i);
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
        for (int i = 0; i<nbVagues; i++){
            vagues.add(new Vague(attributsVagues[1], attributsVagues[0], attributsVagues[2], 3,(longueur+(i*(mer/coeffVagues))*2)+2, longueur, longueur+mer));
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

            Rectangle emplacement = new Rectangle(x, y, x+1.7, y-2, z, personne.getIdPersonne()); // dimensions serviette de plage

            boolean flag = true;
            for (Rectangle emp : placements) {
                if (emp.getZone() == z) {
                    flag = !emplacement.isIn(emp);
                }
            }

            if (flag) {
                placements.add(emplacement);
                Collections.sort(placements);
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

    public int closerSave(Coordonnees coords) {
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
                    if (coords.getX()-threads[i].getPosition().getX()+coords.getY()-threads[i].getPosition().getY() < min && (threads[i].getEtat() != Etat.SAUVETAGE && threads[i].getObjectif() != Objectif.SAUVETAGE)) {
                        flag = i;
                        min = coords.getX()-threads[i].getPosition().getX()+coords.getY()-threads[i].getPosition().getX();
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

    public ArrayList<Vector> checkCroisement(ArrayList<Vector> liste) {
        return liste;
    }

    public void turn() {
        for (int i = 0; i<vagues.size(); i++) {
            for (int j = 0; j<threads.length; j++) {
                if (threads[j].vecteurCourant != null && !threads[j].getAttributsvague() && ((int) threads[j].getPosition().getX() == vagues.get(i).getPositionY() || (int) (threads[j].getPosition().getX() + 1) == vagues.get(i).getPositionY() || (int) (threads[j].getPosition().getX() - 1) == vagues.get(i).getPositionY())) {
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
            Coordonnees position = personne.getPosition();
            Coordonnees objPosition = personne.getObjPosition();
            Objectif objectif = personne.getObjectif();

            if (personne.getAlive()) {

                if (etat == Etat.PATH && personne.getVecteurCourant() == null) {
                    try {
                        //bjPosition = new Coordonnees(60,60);
                        //personne.setObjPosition(new double[]{60,60});
                        double vitesse = personne.isIntoWater()? personne.getVitesseNage(): personne.getVitesse();
                        //System.out.println(vitesse);

                        if (objectif == Objectif.PATROUILLE) {
                            objPosition = new Coordonnees(zones[1] + Math.random() * (zones[2]-zones[1]),Math.random() * largeur);
                            //System.out.println(objPosition[0]+" "+objPosition[1]);
                        } else if (objectif == Objectif.BAIGNADE) {
                            objPosition = new Coordonnees(longueur, position.getY());
                        } else if (objectif == Objectif.NAGE) {
                            objPosition = new Coordonnees(longueur+10 + Math.random()*(mer-10),position.getY());
                        }

                        if (objectif == Objectif.SAUVETAGE) {
                            Vector vecteur = Vector.choixVector(position, objPosition, 0.055555555, 0);
                            personne.addVector(vecteur);
                            
                        } else {
                            Vector vecteur = Vector.choixVector(position, objPosition, vitesse, 0);
                            ArrayList<Coordonnees> liste = new ArrayList<>();
                            int sensX = vecteur.getSensX();
                            int sensY = vecteur.getSensY();
                            if (sensX == -1) {
                                Collections.reverse(placements);
                            }
                            for (Rectangle compare : placements) {
                                //System.out.println(compare.getIdRect()+" "+personne.getIdPersonne());

                                if (sensY == 1) {
                                    if (objPosition.getY() < compare.getD().getY() && objPosition.getY() < compare.getA().getY()) {
                                        //System.out.println("BREAK");
                                        break;
                                    }
                                } else {
                                    if (objPosition.getY() > compare.getD().getY() && objPosition.getY() > compare.getA().getY()) {
                                        //System.out.println("BREAK");
                                        break;
                                    }
                                }

                                if (compare.getIdRect() != personne.getIdPersonne()) {
                                    Coordonnees coords = vecteur.croisementRectangle(compare);
                                    if (!Vector.isCoordsNull(coords)) {
                                        //System.out.println(coords);
                                        liste.add(coords);
                                        if (sensX == 1) {
                                            liste.add(new Coordonnees(coords.getX(),compare.getB().getY()+0.5));
                                            vecteur = Vector.choixVector(new Coordonnees(coords.getX(),compare.getB().getY()+0.5), objPosition, vitesse, 0);
                                        } else {
                                            liste.add(new Coordonnees(coords.getX(),compare.getA().getY()-0.5));
                                            vecteur = Vector.choixVector(new Coordonnees(coords.getX(),compare.getA().getY()-0.5), objPosition, vitesse, 0);
                                        }
                                    }
                                }
                            }

                            if (liste.isEmpty()) {
                                personne.addVector(vecteur);
                            } else {
                                //System.out.println(personne.getIdPersonne()+" P:"+position[0]+" "+position[1]+" O:"+objPosition[0]+" "+objPosition[1]);
                                ListIterator<Coordonnees> itLi = liste.listIterator();
                                while (itLi.hasNext()) {
                                    Coordonnees coordsPrevious;
                                    if (itLi.hasPrevious()) {
                                        coordsPrevious = itLi.previous();
                                        itLi.next();
                                    } else {
                                        coordsPrevious = position;
                                    }
                                    Coordonnees coordsNext = itLi.next();
                                    
                                    //System.out.println(personne.getIdPersonne()+" P:"+coordsPrevious[0]+" "+coordsPrevious[1]+" N:"+coordsNext[0]+" "+coordsNext[1]);
                                    personne.addVector(Vector.choixVector(coordsPrevious, coordsNext, personne.getVitesse(), 0));
                                    if (!itLi.hasNext()) {
                                        //System.out.println(personne.getIdPersonne()+" P:"+coordsNext[0]+" "+coordsNext[1]+" F:"+objPosition[0]+" "+objPosition[1]);
                                        personne.addVector(Vector.choixVector(coordsNext, objPosition, personne.getVitesse(), 0));
                                    }
                                }
                            }

                            if (sensX == -1) {
                                Collections.sort(placements);
                            }

                            /*for (Personne compare : threads) {
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
                            }*/
                        }                
        
                    } catch (ConcurrentModificationException exc) {}
                    
                } else if (etat == Etat.ARRIVEE && personne.getPositionPlage() == null) {
                    placementPlage(personne);
                } else if (etat == Etat.ACHETER && vendeur != null) {
                    personne.setObjPosition(new Coordonnees(vendeur.position.getX()-1, vendeur.position.getY()));
                } else if (etat == Etat.ATTENTE) {

                } else if (etat == Etat.PARTI) {
                    personne.setAlive(false);
                    personne.interrupt();

                } else if (etat == Etat.AUSECOURS) {
                    int sauveteur = closerSave(position);
                    if (sauveteur == -1) {
                        // S'il n'y a pas de sauveteur disponible, RIP !
                    } else {
                        Sauveteur saver = ((Sauveteur) threads[sauveteur]);
                        saver.sauvetage(position,personne.getIdPersonne());
                    }

                } else if (etat == Etat.MOUVEMENT) {
                    if (position.getX() >= longueur && !personne.isIntoWater()) {
                        //attributsBaignade(personne, true);
                        for (Vector vect : personne.getStackMove()) {
                            vect.setVitesse(personne.getVitesseNage());
                        }
                        personne.setIntoWater(true);
                    } else if (position.getX() <= longueur && personne.isIntoWater()) {
                        //attributsBaignade(personne, false);
                        for (Vector vect : personne.getStackMove()) {
                            vect.setVitesse(personne.getVitesse());
                        }
                        personne.setIntoWater(false);
                    }
                    
                } else if (etat == Etat.SAUVETAGE) {

                } else if (etat == Etat.DEPART) {
                    placements.remove(personne.getPositionPlage());
                }

                personne.setOath(true);
            }
        }
    }
}
