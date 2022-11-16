import java.util.ArrayList;

//package src;

public class Personne extends Thread {
    protected int age;
    protected double vitesse;
     
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
    
    Personne(int id, double[] position, int vent, int timing) {
        this.position = position; //position spawn
        this.etat = Etat.ARRIVEE;
        this.id = id;
        this.timing = timing;
        
        setAge();
        setVitesse();
        // setProbaNoyade(vent);
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


    public double getAge() {
        return age;
    }

    public double[] getPosition() {
        return position;
    }
    public double[] getObjPosition() {
        return objPosition;
    }

    public int getNbFoisEau() {
        return nbFoisEau;
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

    public void setAlive(boolean isAlive) {
        alive = isAlive;
    }

    public void setPosition(double[] nextPosition) {
        position = nextPosition;
    }

    public void setPosition(double x, double y) {
        position = new double[]{x,y};
    }

    public void forcePosition(double[] position) {
        // Force le changement de position, on ne veut pas changer l'ancienne position puisque c'est la même, il y a juste eu un déroutement
        this.position = position;
    }

    public void setObjPosition(double[] nextPosition) {
        objPosition = nextPosition;
    }

    public void setAge() {
        this.age = (int)(Math.random() * 100);
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
        if (age >= 15 && age < 60) 
            this.vitesse = (Math.random() * (1.43 - 1.31 + 1) + 1.31)/10; //vitesse moyenne de marche en m/s;
        else if (age>=60 && age<80) {
            this.vitesse = (Math.random() * (1.34 - 1.13 + 1) + 1.13)/10;
        }
        else
            this.vitesse = (Math.random() * (0.97 - 0.94 + 1) + 0.94)/10;
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

/*      public void vaSauver(){
        if ((((this.peutSauver == true) && ((Math.abs(this.position - Personne.position)) < 10)) || ((this.estSauveteur == true) && ((Math.abs(Personne.position) - this.position < 30))) && (Personne.seNoie() == true))){
                this.deplacement(Personne.position);
                this.etat = Etat.SAUVETAGE;
                this.vitesse = this.vitesse / 2;
                deplacement(); //point du bord de la plage le plus proche à mettre en paramètre
                this.vitesse= this.vitesse * 2;
                Personne.etat = Etat.BRONZAGE;
                if (this.estSauveteur == True)
                    this.etat = Etat.ATTENTE;
                else
                    this.etat = Etat.REPOS;
        }
    }

    public boolean estSauve(){
        if (this.position == plage)
            this.etat = Etat.ATTENTE;
            return true;
        return false;
    } */

    public void run() {
        try {
            Thread.sleep(timing);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        alive = true;
        int sleeper = 20;
        boolean finBaignade = false;

        while (!Thread.interrupted()) {
            sleeper = 20;

            if (etat == Etat.MOUVEMENT) {

                if (position[0] == objPosition[0] && position[1] == objPosition[1]) {
                    stackMove.remove(0);
                    if (stackMove.size() == 0) {
                        vecteurCourant = null;
                        if (objectif == Objectif.PLACEMENT) {
                            etat = Etat.PLACEMENT;
                        } else if (objectif == Objectif.BAIGNADE) {
                            etat = Etat.BAIGNADE;
                        } else if (objectif == Objectif.REPOS) {
                            etat = Etat.REPOS;
                        } else if (objectif == Objectif.PARTIR) {
                            etat = Etat.PARTI;
                        }
                    } else {
                        vecteurCourant = stackMove.get(0);
                        objPosition = vecteurCourant.getCoordsObj();
                        sleeper = vecteurCourant.getTiming();
                    } 

                } else {
                    vecteurCourant.glissement();
                    //System.out.println(vecteurCourant.getCoords()[0]+" "+vecteurCourant.getCoords()[1]);
                    setPosition(vecteurCourant.getCoords());
                }
                
            } else if (etat == Etat.BAIGNADE) {
                //System.out.println("Je vais me baigner");
                sleeper = vitesse*3;
                mouvement();
                int x2 = position[0]+(int)(Math.floor(Math.random()*1000));
                int y2 = position[1];
                objPosition = new int[] {x2, y2};
                int x3 = position[0];
                int x4 = (int)(Math.floor(Math.random()*25));
                objPosition = new int[] {x3, x4};
                //new int[] {x2 + (int)(Math.floor(Math.random()*5)), y2 + (int)(Math.floor(Math.random()*5)) };
                if (position[0] == objPosition[0] && position[1] == objPosition[1]) {
                    sleeper = vitesse/3;
                    finBaignade = true;
                }

                //sleeper = 5000;
                // VA CHANGER DU COUP 
                //finBaignade = true;

            } else if (etat == Etat.REPOS) {
                sleeper = 5000;

            } else if (etat == Etat.PLACEMENT && oath) {
                etat = Etat.ATTENTE;
                objectif = Objectif.REPOS;

            } else if (etat == Etat.ARRIVEE && oath) {
                etat = Etat.PATH;
                objectif = Objectif.PLACEMENT;

            } else if (etat == Etat.NOYADE) {

            } else if (etat == Etat.ATTENTE) {
                if (getNbFoisEau() == 0 || Math.floor(Math.random()*(getNbFoisEau()+1)) == 1) {
                    etat = Etat.PATH;
                    objectif = Objectif.BAIGNADE;
                    objPosition[0] = 14999; // Taille limite de la plage
                } else {
                    etat = Etat.DEPART;
                }

            } else if (etat == Etat.DEPART && oath) {
                objPosition[0] = 0;
                etat = Etat.PATH;
                objectif = Objectif.PARTIR;
            } else if (etat == Etat.PATH && vecteurCourant != null) {
                etat = Etat.MOUVEMENT;
            }
            oath = false;
            try {
                Thread.sleep(sleeper);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (etat == Etat.BAIGNADE && finBaignade) {
                etat = Etat.PATH;
                objectif = Objectif.REPOS;
                objPosition = positionPlage.getCentre();
                finBaignade = false;
            }

        }
    
    }

    public void changeAttribut(int x, double y){
        if (x==1){
            vitesse *= y;
        }
        if (x==2){
            probaNoyade *= y;
        }
    }
}



// paramètres physique
// cliquer pour se noyer
// modifier les cases
// path finding