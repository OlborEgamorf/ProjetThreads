//package src;

import java.util.ArrayList;

public class Personne extends Thread {
    protected int age;
    protected double vitesse;
    protected double vitesseNage;
     
    protected Rectangle positionPlage;
    
    protected double[] position;
    protected double[] objPosition;

    protected Etat etat;
    protected Objectif objectif;
    protected int id;

    protected boolean oath = false;
    protected boolean alive = false;
    protected boolean placed = false;

    protected final int timing;

    protected int nbFoisEau = 0;
    protected double probaNoyade = 0;
    protected ArrayList<Vector> stackMove = new ArrayList<Vector>();
    protected Vector vecteurCourant;

    protected Coeff coeff;
    private boolean attributsBaignade = false;
    
    Personne(int id, double[] position, int vent, int timing, Coeff coeff) {
        this.position = position; //position spawn
        this.etat = Etat.ARRIVEE;
        this.id = id;
        this.timing = timing;
        this.coeff = coeff;

        this.age = 15 + (int)(Math.random() * 75);
        this.vitesseNage = (0.83 + Math.random()*0.28)/100;

        setVitesse();

        //System.out.println(vitesse+" "+vitesseNage+" "+age);
        //setProbaNoyade(vent);
    }
    

    public Vector getVecteurCourant() {
        return vecteurCourant;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void addVector(Vector vecteur) {
        stackMove.add(vecteur);
        if (vecteurCourant == null) {
            vecteurCourant = vecteur;
            objPosition = vecteur.getCoordsObj();
        }
    }

    public ArrayList<Vector> getStackMove() {
        return stackMove;
    }

    public double[] getPosition() {
        return position;
    }
    public double[] getObjPosition() {
        return objPosition;
    }

    public int getIdPersonne() {
        return id;
    }

    public Etat getEtat() {
        return etat;
    }

    public Objectif getObjectif() {
        return objectif;
    }

    public Rectangle getPositionPlage() {
        return positionPlage;
    }

    public boolean getAlive() {
        return alive;
    }

    

    public double getVitesseNage() {
        return vitesseNage;
    }


    public boolean getAttributsBaignade(){
        return attributsBaignade;
    }

    public void setAttributsBaignade(boolean booleen){
        attributsBaignade = booleen;
    }

    public void setAlive(boolean isAlive) {
        alive = isAlive;
    }

    public void setPosition(double[] nextPosition) {
        position = nextPosition;
    }

    public void setObjPosition(double[] nextPosition) {
        objPosition = nextPosition;
    }

    public void setPositionPlage(Rectangle positionPlage) {
        this.positionPlage = positionPlage;
    }

    public void setOath(boolean oath) {
        this.oath = oath;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public void setVitesse() {
        if (age < 30) {
            vitesse = 1.34 + Math.random()*0.02;
        } else if (age < 40) {
            vitesse = 1.34 + Math.random()*0.09;
        } else if (age < 50) {
            vitesse = 1.38 + Math.random()*0.05;
        } else if (age < 60) {
            vitesse = 1.31 + Math.random()*0.12;
        } else if (age < 70) {
            vitesse = 1.24 + Math.random()*0.1;
        } else if (age < 80) {
            vitesse = 1.13 + Math.random()*0.13;
        } else {
            vitesse = 0.94 + Math.random()*0.03;
        }

        vitesse /= 100;
    }

    public boolean setProbaNoyade(int vent, int mer, int largeur) {
        float proba;
        if (position[1] > largeur+mer*0.5) {
            // les adultes n'ont plus pieds
            if (age < 15) {
                proba = 10;
            } else {
                proba = 5;
            }
        } else if (position[1] > largeur+mer*0.25) {
            // les enfants n'ont plus pieds
            if (age > 15) {
                proba = 0;
            } else {
                proba = 5;
            }
        } else {
            proba = 0;
        }

        if (proba == 0) {
            return false;
        } else if (Math.floor(Math.random()) * 1000 <= proba) {
            return true;
        } else {
            return false;
        }
    }

    public void changeAttribut(int x, double y) {
        if (x == 1) {
            for (Vector vector : stackMove)
                vector.vitesse *= y;
        }
        if (x == 2) {
            probaNoyade *= y;
        }
    }

    public void run() {
        try {
            Thread.sleep(timing/coeff.getCoeff());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        alive = true;
        int tempsEau = 0;

        while (!Thread.interrupted()) {
            int sleeper = 10;

            if (etat == Etat.MOUVEMENT && oath) {

                if (position[0] == objPosition[0] && position[1] == objPosition[1]) {
                    stackMove.remove(0);
                    if (stackMove.size() == 0) {
                        if (objectif == Objectif.PLACEMENT) {
                            etat = Etat.PLACEMENT;
                        } else if (objectif == Objectif.BAIGNADE) {
                            objectif = Objectif.NAGE;
                            etat = Etat.PATH;
                        } else if (objectif == Objectif.REPOS) {
                            etat = Etat.REPOS;
                        } else if (objectif == Objectif.PARTIR) {
                            etat = Etat.PARTI;
                        } else if (objectif == Objectif.NAGE) {
                            etat = Etat.BAIGNADE;
                        }
                        vecteurCourant = null;
                    } else {
                        vecteurCourant = stackMove.get(0);
                        objPosition = vecteurCourant.getCoordsObj();
                        sleeper = vecteurCourant.getTiming()/coeff.getCoeff();
                    } 

                } else {
                    vecteurCourant.glissement();
                    setPosition(vecteurCourant.getCoords());
                }

                if (objectif == Objectif.NAGE) {
                    tempsEau += 10*coeff.getCoeff();
                }
                
            } else if (etat == Etat.BAIGNADE) {
                tempsEau += 10*coeff.getCoeff();
                if (tempsEau > 600000) {
                    objectif = Objectif.REPOS;
                    objPosition = positionPlage.getCentre();
                    etat = Etat.PATH;
                    nbFoisEau++;
                }               

            } else if (etat == Etat.REPOS) {
                etat = Etat.ATTENTE;
                sleeper = 600000/coeff.getCoeff();

            } else if (etat == Etat.PLACEMENT && oath) {
                objectif = Objectif.REPOS;
                etat = Etat.ATTENTE;

            } else if (etat == Etat.ARRIVEE && oath) {
                objectif = Objectif.PLACEMENT;
                etat = Etat.PATH;

            } else if (etat == Etat.NOYADE) {

            } else if (etat == Etat.ATTENTE) {
                if (nbFoisEau == 0 || Math.floor(Math.random()*(nbFoisEau+1)) == 1) {
                    objectif = Objectif.BAIGNADE;
                    etat = Etat.PATH;
                } else {
                    etat = Etat.DEPART;
                }

            } else if (etat == Etat.DEPART && oath) {
                objectif = Objectif.PARTIR;
                objPosition[0] = 0;
                etat = Etat.PATH;
            } else if (etat == Etat.PATH && vecteurCourant != null) {
                etat = Etat.MOUVEMENT;
            }

            oath = false;
            try {
                Thread.sleep(sleeper);
            } catch (InterruptedException e) {
                System.out.println(id+" : interrompu");
            }

        }
    }
}



// paramètres physique
// cliquer pour se noyer
// modifier les cases
// path finding