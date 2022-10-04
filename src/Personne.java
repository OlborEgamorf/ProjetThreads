import java.util.stream.IntStream.IntMapMultiConsumer;

public class Personne extends Thread {
    private int age;
    private double vitesse;
    private boolean estSauveteur;
    private boolean peutSauver;
    private double probaNoyade;
    
    private int[] position;
    private int[] oldPosition;
    private Etat etat;
    private int[][] vision;
    private int id;
    
    Personne(int id, int[] position, int vent){
        this.position = position; //position spawn
        this.etat = Etat.ATTENTE;
        this.id = id;
        setAge();
        setVitesse();
        setEstSauveteur();
        setPeutSauver();
        setProbaNoyade(vent);
        setPositionPlage();
    }

    public double getAge(){
        return age;
    }

    public int[] getPosition(){
        return position;
    }

    public int[] getOldPosition () {
        return oldPosition;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setPosition(int[] nextPosition){
        oldPosition = position;
        position = nextPosition;
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

    public void setEstSauveteur(){
        if (age > 18 && age < 60) {
            double probaSauveteur = Math.random();
            if (probaSauveteur < 0.0005)
                this.estSauveteur = true;
            else
                this.estSauveteur = false;
        }
        else
            this.estSauveteur = false;
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

    public void setPositionPlage(){
        if (this.estSauveteur == false) {
            boolean trouve = false;
        }
        else
            while (position == plage && trouve == false){
                //faire un truc pour verifier la disponibilité de l'espace sur la plage le plus proche de la mer
                double[][] position = positionVerifiee; //prend la valeur de la position qui est en train d'etre vérifiée
                trouve = true;
        }
        this.positionPlage = position;
        Plage.unpack(positionVerifiee);
    }
    
    
    public void deplacement(positionArrivee){
        double diffX = this.postion[0] - positionArrivee[0];
        double diffY = this.position[1] - positionArrivée[1];
        while (this.position != positionArrivée){
            if(diffX > 0)
                this.position[0] += this.vitesse;
            else
                this.position[0] -= this.vitesse;
            diffX -= this.vitesse;
            if (diffY > 0)
        }
    }

    public void baignade(){
        if (this.estSauveteur = false){
            if (this.position == plage){
                double baignade = Math.random();
                if (baignade < 0.05){
                    this.activité = Activite.deplacement;
                    deplacement(); //mettre en paramètre un emplacement libre dans la mer
                }
            }
            if (this.position == mer){
                double baignade = Math.random;
                if (baignade < 0.05) {
                    this.activité = Activite.deplacement;
                    déplacement(positionPlage);
                    this.activite = Activite.bronzage;
                }
            }
        }
    }

    public void seNoie(){
        if (this.position == mer) {
            double noyade = Math.random();
            if (noyade < (0.005 * this.probaNoyade))
                this.activite = Activite.seNoie;
                while(estSauve() == false){
                    Thread.sleep();

                }
        }
    }

    public void vaSauver(){
        if ((((this.peutSauver == true) && ((Math.abs(this.position - Personne.position)) < 10)) || ((this.estSauveteur == true) && ((Math.abs(Personne.position) - this.position < 30))) && (Personne.seNoie() == true)){
                this.deplacement(Personne.position);
                this.activite = Activite.sauvetage;
                this.vitesse = this.vitesse / 2;
                this.deplacement(); //point du bord de la plage le plus proche à mettre en paramètre
                this.vitesse * 2;
                Personne.activite = bronzage;
                if (this.estSauveteur == True)
                    this.activite = Activite.attente;
                else
                    this.activite = Activite.bronzage;
        }
    }

    public boolean estSauve(){
        if (this.position == plage)
            this.activite = Activite.attente;
            return true;
        return false;
    }

    public void quittePlage(){
        double partir = Math.random();
        if (partir <= 0.005)
            deplacement(positionPlage);
            Plage.pack()
            deplacement();      //endroit à définir où on part de la plage
            interruption();
    }

    public void run(){
        while (!Thread.currentThread().isInterrupted()){
            this.baignade();
            this.seNoie();
            this.vaSauver();
            this.quittePlage();
        }
    }

    public void interruption(){
        Thread.currentThread().interrupt();
    }
}



