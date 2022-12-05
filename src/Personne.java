//package src;

import java.util.ArrayList;

public class Personne extends Thread implements Comparable<Personne> {
    protected int age;
    
    protected double vitesse;
    protected double vitesseNage;

    protected Rectangle positionPlage;
    
    protected Coordonnees position;
    protected Coordonnees objPosition;

    protected Etat etat;
    protected Objectif objectif;
    protected int id;

    protected boolean oath = false;
    protected boolean alive = false;
    protected boolean placed = false;
    protected boolean intoWater = false;

    protected boolean volonteAcheter = false;

    protected final int timing;

    protected int nbFoisEau = 0;

    protected double stamina = 0;
    protected double staminaMax;

    protected ArrayList<Vector> stackMove = new ArrayList<Vector>();
    protected Vector vecteurCourant;

    private boolean attributsBaignade = false;
    private boolean attributsVague = false;
    
    Personne(int id, Coordonnees position, int vent, int timing) {
        this.position = position; //position spawn
        this.etat = Etat.ARRIVEE;
        this.id = id;
        this.timing = timing;

        this.age = 15 + (int)(Math.random() * 75);
        this.vitesseNage = (0.83 + Math.random()*0.28)/100;

        setVitesse();
        setStamina();

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

    public Coordonnees getPosition() {
        return position;
    }
    public Coordonnees getObjPosition() {
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
    public boolean getAttributsvague(){
        return attributsVague;
    }

    public void setAttributsBaignade(boolean booleen){
        attributsBaignade = booleen;
    }
    public void setAttributsVague(boolean booleen){
        attributsVague = booleen;
    }

    public void setAlive(boolean isAlive) {
        alive = isAlive;
    }

    public void setObjPosition(Coordonnees nextPosition) {
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

    public boolean isIntoWater() {
        return intoWater;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public void setIntoWater(boolean intoWater) {
        this.intoWater = intoWater;
    }

    private void setVitesse() {
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

    private void setStamina() {
        if (age < 20) {
            stamina = 200 + Math.random()*200; // 200 - 400
        } else if (age < 35) {
            stamina = 300 + Math.random()*200; // 300 - 500
        } else if (age < 50) {
            stamina = 250 + Math.random()*150; // 250 - 400
        } else if (age < 65) {
            stamina = 200 + Math.random()*100; // 200 - 300
        } else if (age < 80) {
            stamina = 150 + Math.random()*100; // 150 - 250
        } else {
            stamina = 100 + Math.random()*100; // 100 - 200
        }

        staminaMax = stamina;
    }

    public void setVolonteAcheter(boolean volonte) {
        this.volonteAcheter = volonte;
    }

    public void changeAttribut(int x, double y) {
        if (x == 1) {
            for (Vector vector : stackMove)
                vector.vitesse *= y;
        }
        if (x == 2) {
            //probaNoyade *= y;
        }
    }

    public void run() {
        int deadge = 0;
        while (deadge < timing) {
            try {
                deadge+=1000*Coeff.getCoeff();
                Thread.sleep(1000/Coeff.getCoeff());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        

        alive = true;
        int tempsEau = 0;
        int iterStatique = 0;

        while (!Thread.interrupted()) {
            int sleeper = 10;
            //System.out.println(stamina);
            //System.out.println(etat);

            if (etat == Etat.MOUVEMENT && oath && objPosition != null) {

                if (position.equals(objPosition)) {
                    stackMove.remove(0);
                    if (stackMove.size() == 0) {
                        if (objectif == Objectif.PLACEMENT) {
                            etat = Etat.PLACEMENT;
                        } else if (objectif == Objectif.BAIGNADE) {
                            objectif = Objectif.NAGE;
                            etat = Etat.PATH;
                        } else if (objectif == Objectif.REPOS) {
                            if (volonteAcheter) {
                                objectif = Objectif.ACHETER;
                                etat = Etat.PATH;
                            } else {
                                etat = Etat.REPOS;
                            }
                        } else if (objectif == Objectif.PARTIR) {
                            etat = Etat.PARTI;
                        } else if (objectif == Objectif.ACHETER) {
                            etat = Etat.ARRIVACHAT;
                        } else if (objectif == Objectif.NAGE) {
                            etat = Etat.BAIGNADE;
                        } else if (objectif == Objectif.FILE) {
                            etat = Etat.ENFILE;
                        }
                        vecteurCourant = null;
                        objPosition = null;
                    } else {
                        vecteurCourant = stackMove.get(0);
                        objPosition = vecteurCourant.getCoordsObj();
                        sleeper = vecteurCourant.getTiming()/Coeff.getCoeff();
                    }

                } else {
                    try {
                        vecteurCourant.glissement();
                        position = vecteurCourant.getCoords();
                        stamina -= 0.002*Coeff.getCoeff();
                    } catch (NullPointerException point){

                    }
                    
                }

                if (intoWater) {
                    tempsEau += 10*Coeff.getCoeff();
                    stamina -= 0.004*Coeff.getCoeff();
                    if (stamina < 15) {
                        etat = Etat.AUSECOURS;
                    }
                }

            } else if (etat == Etat.BAIGNADE) {
                tempsEau += 10*Coeff.getCoeff();
                stamina -= 0.0015*Coeff.getCoeff();
                if (tempsEau > 300000 || stamina < 25) {
                    objectif = Objectif.REPOS;
                    objPosition = positionPlage.getCentre();
                    etat = Etat.PATH;
                    nbFoisEau++;
                    tempsEau = 0;
                }

            } else if (etat == Etat.REPOS) {
                iterStatique += 10*Coeff.getCoeff();
                if (stamina < staminaMax) {
                    stamina += 0.1;
                }
                if (iterStatique > 600000 && stamina > staminaMax/2) {
                    etat = Etat.ATTENTE;
                    iterStatique = 0;
                }
                if (volonteAcheter) {
                    objectif = Objectif.ACHETER;
                    etat = Etat.PATH;
                    iterStatique = 0;
                }

            } else if (etat == Etat.ACHETER){
                iterStatique += 10*Coeff.getCoeff();
                if (iterStatique > 40000) {
                    objPosition = positionPlage.getCentre();
                    objectif = Objectif.REPOS;
                    etat = Etat.PATH;
                    volonteAcheter = false;
                }

            } else if (etat == Etat.PLACEMENT) {
                placed = true;
                objectif = Objectif.REPOS;
                etat = Etat.ATTENTE;

            } else if (etat == Etat.ARRIVEE && oath) {
                objectif = Objectif.PLACEMENT;
                etat = Etat.PATH;

            } else if (etat == Etat.NOYADE) {
                stamina -= 0.001*Coeff.getCoeff();

            } else if (etat == Etat.ATTENTE) {
                if (nbFoisEau == 0 || Math.floor(Math.random()*(nbFoisEau+1)) == 1) {
                    objectif = Objectif.BAIGNADE;
                    etat = Etat.PATH;
                } else {
                    etat = Etat.DEPART;
                }

            } else if (etat == Etat.DEPART) {
                placed = false;
                positionPlage = null;
                objectif = Objectif.PARTIR;
                objPosition = new Coordonnees(0, objPosition.getY());
                etat = Etat.PATH;
                
            } else if (etat == Etat.PATH && vecteurCourant != null) {
                etat = Etat.MOUVEMENT;
            } else if (etat == Etat.AUSECOURS && oath) {
                etat = Etat.NOYADE;
            }

            oath = false;
            try {
                Thread.sleep(sleeper);
            } catch (InterruptedException e) {
                System.out.println(id+" : interrompu");
            }

        }
    }

    public int compareTo(Personne other) {
        if (other.position.getY() > position.getY()) {
            return -1;
        } else if (other.position.getY() < position.getY()) {
            return 1;
        } else {
            return 0;
        }
    }
}



// paramètres physique
// cliquer pour se noyer
// modifier les cases
// path finding