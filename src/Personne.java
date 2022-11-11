//package src;

public class Personne extends Thread {
    protected int age;
    protected int vitesse;
     
    protected int[] positionPlage;
    
    protected int[] position;
    protected int[] oldPosition;
    protected int[] objPosition;

    protected Etat etat;
    protected Objectif objectif;
    protected int[][] vision = {{0,0,0},{0,0,0},{0,0,0}};
    protected int id;

    protected boolean oath = false;
    protected boolean alive = false;

    protected final int timing;

    protected int nbFoisEau = 0;
    protected double probaNoyade = 0;
    
    Personne(int id, int[] position, int vent, int timing) {
        this.position = position; //position spawn
        this.oldPosition = position;
        this.etat = Etat.ARRIVEE;
        this.id = id;
        this.timing = timing;
        
        setAge();
        setVitesse();
        // setProbaNoyade(vent);
    }

    public double getAge() {
        return age;
    }

    public int[] getPosition() {
        return position;
    }
    public int[] getObjPosition() {
        return objPosition;
    }

    public int[] getOldPosition () {
        return oldPosition;
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

    public int[][] getVision() {
        return vision;
    }

    public int[] getPositionPlage() {
        return positionPlage;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean isAlive) {
        alive = isAlive;
    }

    public void setPosition(int[] nextPosition) {
        // Change la position de la Personne et sauvegarde l'ancienne
        oldPosition = position;
        position = nextPosition;
    }

    public void forcePosition(int[] position) {
        // Force le changement de position, on ne veut pas changer l'ancienne position puisque c'est la même, il y a juste eu un déroutement
        this.position = position;
    }

    public void setObjPosition(int[] nextPosition) {
        objPosition = nextPosition;
    }

    public void immobilisation() {
        // La position a été validée, et stabilisée
        oldPosition = position;
    }

    public void setVision(int[][] vision) {
        this.vision = vision;
    }

    public void setVisionCase(int x, int y, int val) {
        vision[x][y] = val;
    }

    public void setAge() {
        this.age= (int)(Math.random() * 100);
    }

    public void setPositionPlage(int[] positionPlage) {
        this.positionPlage= positionPlage;
    }

    public void setOath(boolean oath) {
        this.oath = oath;
    }

    public void setVitesse() {
        if (age >= 15 && age < 60) 
            this.vitesse = (int)Math.floor(1000/(Math.random() * (1.43 - 1.31 + 1) + 1.31)); //vitesse moyenne de marche en m/s;
        else if (age>=60 && age<80) {
            this.vitesse = (int)Math.floor(1000/(Math.random() * (1.34 - 1.13 + 1) + 1.13));
        }
        else
            this.vitesse = (int)Math.floor(1000/(Math.random() * (0.97 - 0.94 + 1) + 0.94));
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

    public int[] mouvement(int x, int y) {

        int ecartX = objPosition[0]-x;
        int ecartY = objPosition[1]-y;

        if (ecartX != 0) {
            ecartX = ecartX/Math.abs(ecartX);
        }
        
        if (ecartY != 0) {
            ecartY = ecartY/Math.abs(ecartY);
        }                    
        
        int[] newPos;

        if (vision[1+ecartX][1+ecartY] == 0) {
            newPos = new int[]{x+ecartX,y+ecartY};
        } else if (vision[1][1+ecartY] == 0) {
            newPos = new int[]{x,y+ecartY};
        } else if (vision[1+ecartX][1] == 0) {
            newPos = new int[]{x+ecartX,y};
        } else if (vision[1+ecartX][0] == 0 && y > 0) {
            newPos = new int[]{x+ecartX,y-1};
        } else if (vision[0][1+ecartY] == 0 && x > 0) {
            newPos = new int[]{x-1,y+ecartY};
        } else {
            System.out.println("DOMMAGE");
            newPos = position;
        }

        return newPos;
    }

    public void run() {
        try {
            Thread.sleep(timing);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        alive = true;
        int sleeper = 10;
        boolean finBaignade = false;

        while (!Thread.interrupted()) {
            sleeper = 20;

            if (etat == Etat.MOUVEMENT && oath) {

                if (position[0] == objPosition[0] && position[1] == objPosition[1]) {
                    //System.out.println("OUI JE SUI LA");
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
                    setPosition(mouvement(position[0], position[1]));
                    sleeper = vitesse/2;
                }
                
            } else if (etat == Etat.BAIGNADE) {
                //sleeper = 5000;
                // VA CHANGER DU COUP 
                finBaignade = true;

            } else if (etat == Etat.REPOS) {
                sleeper = 5000;

            } else if (etat == Etat.PLACEMENT && oath) {
                etat = Etat.ATTENTE;
                objectif = Objectif.REPOS;

            } else if (etat == Etat.ARRIVEE && oath) {
                etat = Etat.MOUVEMENT;
                objectif = Objectif.PLACEMENT;

            } else if (etat == Etat.NOYADE) {

            } else if (etat == Etat.ATTENTE) {
                if (getNbFoisEau() == 0 || Math.floor(Math.random()*(getNbFoisEau()+1)) == 1) {
                    etat = Etat.MOUVEMENT;
                    objectif = Objectif.BAIGNADE;
                    objPosition[0] = 14999; // Taille limite de la plage
                } else {
                    etat = Etat.DEPART;
                }

            } else if (etat == Etat.DEPART && oath) {
                objPosition[0] = 0;
                etat = Etat.MOUVEMENT;
                objectif = Objectif.PARTIR;
            }
            oath = false;
            try {
                Thread.sleep(sleeper);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (etat == Etat.BAIGNADE && finBaignade) {
                etat = Etat.MOUVEMENT;
                objectif = Objectif.REPOS;
                objPosition = positionPlage;
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