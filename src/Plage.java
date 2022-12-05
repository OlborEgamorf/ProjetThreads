//package src;

import java.util.*;

public class Plage {

    private final int longueur;
    private final int largeur;
    private final int vent;
    private final int mer;
    private int[] zones = setZones();
    private final int nbPersonnes;

    private ArrayList<Personne> threads = new ArrayList<Personne>();
    private ArrayList<Rectangle> placements = new ArrayList<Rectangle>();

    private ArrayList<Sauveteur> sauveteurs = new ArrayList<Sauveteur>();
    private Rectangle poste;

    private Meteo meteo;
    private Vendeur vendeur;

    private ArrayList<Vague> vagues = new ArrayList<Vague>();
    private double[] attributsVagues = new double[3]; //[vitesse, hauteur, force]
    private double coeffVagues = 10;
    

    Plage(int longueur, int largeur, int vent, int mer, int nbMax, Meteo meteo) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.vent = vent;
        this.mer = mer;
        this.nbPersonnes = nbMax+1+nbMax/1;

        this.poste = new Rectangle(3,2,5,0, 2,-1);
        placements.add(poste);

        setZones();
        this.meteo = meteo;
        setAttributsVagues();
        setVagues();

        int apparition = 5000; // coefficient de vitesse d'apparition, en ms
        for (int i = 0; i < nbMax; i++) {
            Coordonnees posTest = new Coordonnees(0, largeur*Math.random());
            threads.add(new Personne(i,posTest,vent,apparition*i));
        }

        if (nbMax >= 1) {
            for (int i = 0; i < nbMax/1; i++) {
                Sauveteur sauv = new Sauveteur(i+nbMax,poste.getCentre(),vent,poste);
                threads.add(sauv);
                sauveteurs.add(sauv);
            }
        }

        if (nbMax > 0) {
            vendeur = new Vendeur(nbMax+nbMax/500, new Coordonnees(longueur-1, 1), vent, new Coordonnees(longueur-1, largeur));
            threads.add(vendeur);
        }

        changeVitesseInitiale();

    }
    public void startAll() {
        for (Personne personne : threads) {
            personne.start();
        }
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

    public ArrayList<Personne> getThreads() {
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

    public int[] setZones(){
        zones = new int[3];
        zones[0] = this.longueur/3;
        zones[1] = 2*(this.longueur/3);
        zones[2] = this.longueur-5;
        return zones;
    }

    public void placementPlage(Personne personne){
        int z = zones.length-1;
        int t = 0;
        while (z >= 0) {
            double x = (Math.random() * ((zones[z]-3) - (zones[z-1]+3)) + (zones[z-1]+3)-5);
            double y = 3 + (Math.floor(Math.random() * (((getLargeur()-3) - (getLargeur() - (getLargeur()-3))) + (getLargeur() - (getLargeur()-3)))));

            Rectangle emplacement = new Rectangle(x, y, x+1.2, y-2, z, personne.getIdPersonne()); // dimensions serviette de plage
            Rectangle emplacementLarge = new Rectangle(x-0.5, y+0.5, x+1.7, y-2.5, z, personne.getIdPersonne()); // dimensions serviette de plage

            boolean flag = true;
            for (Rectangle emp : placements) {
                if (emp.getZone() == z) {
                    flag = !emplacementLarge.isIn(emp);
                }
                if (emp.getCentre().getY() > emplacement.getCentre().getY()+2) {
                    break;
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

    public Sauveteur closerSave(Coordonnees coords) {
        switch (sauveteurs.size()) {
            case 0: {
                return null;
            }
            case 1: {
                return sauveteurs.get(0);
            }
            default: {
                Sauveteur flag = null;
                double min = 15000;
                for (int s=0;s<sauveteurs.size();s++) {
                    Sauveteur sauv = sauveteurs.get(s);
                    if (coords.getX()-sauv.getPosition().getX()+coords.getY()-sauv.getPosition().getY() < min && (sauv.getEtat() != Etat.SAUVETAGE && sauv.getObjectif() != Objectif.SAUVETAGE)) {
                        flag = sauv;
                        min = coords.getX()-sauv.getPosition().getX()+coords.getY()-sauv.getPosition().getX();
                    }
                }
                return flag;
            }
        }
    }

    public void turn() {
        for (int i = 0; i<vagues.size(); i++) {
            for (int j = 0; j<nbPersonnes; j++) {
                if (threads.get(j).vecteurCourant != null && !threads.get(j).getAttributsvague() && ((int) threads.get(j).getPosition().getX() == vagues.get(i).getPositionY() || (int) (threads.get(j).getPosition().getX() + 1) == vagues.get(i).getPositionY() || (int) (threads.get(j).getPosition().getX() - 1) == vagues.get(i).getPositionY())) {
                    threads.get(j).changeAttribut(1, 0.5);
                    threads.get(j).changeAttribut(2, 1.5);
                    threads.get(j).setAttributsVague(true);
                } else if (threads.get(j).vecteurCourant != null && threads.get(j).getAttributsvague()) {
                    threads.get(j).changeAttribut(1, 1 / 0.5);
                    threads.get(j).changeAttribut(2, 1 / 1.5);
                    threads.get(j).setAttributsVague(false);
                }
            }
        }

       // Collections.sort(threads);
        
        for (int i = 0; i < nbPersonnes; i++) {

            Personne personne = threads.get(i);
            Etat etat = personne.getEtat();
            Coordonnees position = personne.getPosition();
            Coordonnees objPosition = personne.getObjPosition();
            Objectif objectif = personne.getObjectif();

            if (personne.getAlive()) {

                if (etat == Etat.PATH && personne.getVecteurCourant() == null) {
                    try {
                        //objPosition = new Coordonnees(60,60);
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
                        } else if (objectif == Objectif.ACHETER) {
                            if (!vendeur.containArrive(personne) && !vendeur.containFile(personne)) {
                                vendeur.addArrive(personne);
                            }
                            if (objPosition == null) {
                                objPosition = new Coordonnees(vendeur.getPosition().getX(), vendeur.getPosition().getY()-vendeur.sizeFile()-1);
                            }
                        } else if (objectif == Objectif.VENDRE) {
                            for (Personne acheteur : threads) {
                                if (Math.floor(Math.random() * 50) == 1) {
                                    acheteur.setVolonteAcheter(true);
                                }
                            }
                        }

                        personne.setObjPosition(objPosition);

                        if (objectif == Objectif.SAUVETAGE) {
                            Vector vecteur = Vector.choixVector(position, objPosition, 0.0055555555, 0);
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
                                if (sensY == 1) {
                                    if (objPosition.getY() < compare.getD().getY() && objPosition.getY() < compare.getA().getY()) {
                                        break;
                                    }
                                } else {
                                    if (objPosition.getY() > compare.getD().getY() && objPosition.getY() > compare.getA().getY()) {
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
        
                                    personne.addVector(Vector.choixVector(coordsPrevious, coordsNext, personne.getVitesse(), 0));
                                    if (!itLi.hasNext()) {
                                        personne.addVector(Vector.choixVector(coordsNext, objPosition, personne.getVitesse(), 0));
                                    }
                                }
                            }

                            if (sensX == -1) {
                                Collections.sort(placements);
                            }
                        }                
        
                    } catch (ConcurrentModificationException exc) {}
                    
                } else if (etat == Etat.ARRIVEE && personne.getPositionPlage() == null) {
                    placementPlage(personne);
                } else if (etat == Etat.ATTENTE) {

                } else if (etat == Etat.PARTI) {
                    personne.setAlive(false);
                    personne.interrupt();
                    if (personne == vendeur) {
                        threads.remove(vendeur);
                        vendeur = new Vendeur(i, new Coordonnees(longueur-1, 1), vent, new Coordonnees(longueur-1, largeur));
                        threads.add(vendeur);
                    }

                } else if (etat == Etat.AUSECOURS) {
                    Sauveteur sauveteur = closerSave(position);
                    if (sauveteur == null) {
                        // S'il n'y a pas de sauveteur disponible, RIP !
                    } else {
                        sauveteur.sauvetage(position,personne.getIdPersonne());
                    }

                } else if (etat == Etat.MOUVEMENT) {
                    if (position.getX() > longueur && !personne.isIntoWater()) {
                        for (Vector vect : personne.getStackMove()) {
                            vect.setVitesse(personne.getVitesseNage());
                        }
                        personne.setIntoWater(true);
                    } else if (position.getX() < longueur && personne.isIntoWater()) {
                        for (Vector vect : personne.getStackMove()) {
                            vect.setVitesse(personne.getVitesse());
                        }
                        personne.setIntoWater(false);
                    }
                    
                } else if (etat == Etat.SAUVETAGE) {

                } else if (etat == Etat.DEPART) {
                    placements.remove(personne.getPositionPlage());
                } else if (etat == Etat.ARRIVACHAT) {
                    vendeur.coming(personne);
                }

                personne.setOath(true);
            }
        }
    }
}
