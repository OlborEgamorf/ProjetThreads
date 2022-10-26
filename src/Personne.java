public class Personne extends Thread {
    private int age;
    private double vitesse;
    private boolean estSauveteur;
    private boolean peutSauver;
    private double probaNoyade;
     
    private int[] positionPlage;
    
    private int[] position;
    private int[] oldPosition;
    private int[] objPosition;

    private Etat etat;
    private Objectif objectif;
    private int[][] vision = {{0,0,0},{0,0,0},{0,0,0}};
    private int id;

    private boolean oath = true;
    private boolean alive = false;
    private boolean place = false;

    private final int timing;
    private int nbFoisEau = 0;
    
    Personne(int id, int[] position, int vent, int timing){
        this.position = position; //position spawn
        this.oldPosition = position;
        this.etat = Etat.ARRIVEE;
        this.id = id;
        this.timing = timing;
        
        setAge();
        setVitesse();
        setEstSauveteur();
        setPeutSauver();
        // setProbaNoyade(vent);
    }

    public double getAge(){
        return age;
    }

    public int[] getPosition(){
        return position;
    }
    public int[] getObjPosition(){
        return objPosition;
    }

    public int[] getOldPosition () {
        return oldPosition;
    }

    public Etat getEtat() {
        return etat;
    }

    public boolean getEstSauveteur(){
        return estSauveteur;
    }

    public int[][] getVision(){
        return vision;
    }

    public int[] getPositionPlage(){
        return positionPlage;
    }

    public int getIdPersonne(){
        return id;
    }

    public int getNbFoisEau() {
        return nbFoisEau;
    }



    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean isAlive) {
        alive = isAlive;
    }

    public void setPosition(int[] nextPosition){
        oldPosition = position;
        position = nextPosition;
    }

    public void setObjPosition(int[] nextPosition){
        objPosition = nextPosition;
    }

    public void immobilisation() {
        oldPosition = position;
    }

    public void setVision(int[][] vision) {
        this.vision = vision;
    }

    public void setVisionCase(int x, int y, int val) {
        vision[x][y] = val;
    }

    public void setAge(){
        this.age= (int)(Math.random() * 100);
    }



    public void setPeutSauver(){
        if (age < 15 || age > 60) {
            this.peutSauver = false;
        }
        else{
            double probaSauv = Math.random();
            if (probaSauv < 0.05)
                this.peutSauver = true;
            else
                this.peutSauver = false;
        }
    }

    public void setPositionPlage(int[] positionPlage){
        this.positionPlage= positionPlage;
    }

    public void setEstSauveteur(){
        if (age > 18 && age < 60) {
            double probaSauveteur = Math.random();
            if (probaSauveteur < 0.005)
                this.estSauveteur = true;
            else
                this.estSauveteur = false;
        }
        else
            this.estSauveteur = false;
    }

    public void setOath(boolean oath) {
        this.oath = oath;
    }

    

    public boolean isPlace() {
        return place;
    }

    public void setVitesse(){
        if (age >= 15 && age < 60)
            this.vitesse= Math.floor(Math.random() * (1.43 - 1.31 + 1) + 1.31); //vitesse moyenne de marche en m/s;
        else if (age>=60 && age<80) {
            this.vitesse = Math.floor(Math.random() * (1.34 - 1.13 + 1) + 1.13);
        }
        else
            this.vitesse= Math.floor(Math.random() * (0.97 - 0.94 + 1) + 0.94);
    }

    public void setProbaNoyade(int vent){
        if (age <= 2){
            this.probaNoyade += 0.0004;
            if (this.position[2] > (-0.2)){
                this.probaNoyade *= 1000;
            }
        }
        else if (this.position[2] > (-1)){
            if (age > 50){
                this.probaNoyade += 0.0002;
                for (int i = 50; i <= age; i++){
                    this.probaNoyade += 0.00001;
                }
            }
            if (vent > 50)
                this.probaNoyade *= 1.2;
            else if (vent > 65) {
                this.probaNoyade *= 1.5;
            }
        }
    }

    /*public void seNoie(){
        if (this.position == Plage.mer) {
            double noyade = Math.random();
            if (noyade < (0.005 * this.probaNoyade))
                this.etat = Etat.NOYADE;
                while(estSauve() == false){
                    Thread.sleep();

                }
        }
    }

     public void vaSauver(){
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

        while (!Thread.interrupted()) {

            if (etat == Etat.MOUVEMENT) {

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
                    int x = position[0];
                    int y = position[1];


                    int ecartX = objPosition[0]-position[0];
                    int ecartY = objPosition[1]-position[1];

                    if (ecartX != 0) {
                        ecartX = ecartX/Math.abs(ecartX);
                    }
                    
                    if (ecartY != 0) {
                        ecartY = ecartY/Math.abs(ecartY);
                    }                    
                    
                    if (vision[1+ecartX][1+ecartY] == 0) {
                        int[] newPos = {x+ecartX,y+ecartY};
                        setPosition(newPos);
                    } else if (vision[1][1+ecartY] == 0) {
                        int[] newPos = {x,y+ecartY};
                        setPosition(newPos);
                    } else if (vision[1+ecartX][1] == 0) {
                        int[] newPos = {x+ecartX,y};
                        setPosition(newPos);
                    } else if (vision[1+ecartX][0] == 0 && y > 0) {
                        int[] newPos = {x+ecartX,y-1};
                        setPosition(newPos);
                    } else if (vision[0][1+ecartY] == 0 && x > 0) {
                        int[] newPos = {x-1,y+ecartY};
                        setPosition(newPos);
                    } else {
                        System.out.println("DOMMAGE");
                        int[] newPos = position;
                        setPosition(newPos);
                    }
                    //System.out.println(position[0]+" "+position[1]);

                    oath = false;
                    while (!oath) {
                        try {
                            ///Thread.sleep(100 * coefficient);
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                
            } else if (etat == Etat.BAIGNADE) {
                try {
                    ///Thread.sleep(60000 * coefficient);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                etat = Etat.MOUVEMENT;
                objectif = Objectif.REPOS;
                objPosition = positionPlage;

            } else if (etat == Etat.REPOS) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                etat = Etat.ATTENTE;
            } else if (etat == Etat.PLACEMENT) {

            } else if (etat == Etat.NOYADE) {

            } else if (etat == Etat.ATTENTE) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    
    }
    
    public void placementDebut(){
        etat= Etat.MOUVEMENT;
        objectif= Objectif.PLACEMENT;
    }
    public void placementFini(){
        place = true;
        etat= Etat.ATTENTE;
        objectif= Objectif.REPOS;
    }

    public void goBaignade(int mer, int longeuur, int[] zones){
        int y = objPosition[1];
        objPosition = new int[] {longeuur+ (int) (Math.random()*mer),y};
        etat = Etat.MOUVEMENT;
        objectif = Objectif.BAIGNADE;
        nbFoisEau ++;
        System.out.println(objPosition[0]+" "+objPosition[1]+" "+etat);
    }



}



