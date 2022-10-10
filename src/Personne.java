import java.util.stream.IntStream.IntMapMultiConsumer;

public class Personne extends Thread {
    private int age;
    private double vitesse;
    private boolean estSauveteur;
    private boolean peutSauver;
    private double probaNoyade;
    private int[]positionPlage;
    
    private int[] position;
    private int[] oldPosition;
    private Etat etat;
    private Objectif objectif;
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

    public boolean getEstSauveteur(){
        return estSauveteur;
    }

    public int[] getPositionPlage(){
        return positionPlage;
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
            boolean trouve = true;
        }
        else{
            boolean trouve= false;
            while (position == plage && trouve == false){
                //faire un truc pour verifier la disponibilité de l'espace sur la plage le plus proche de la mer
                double[][] positionPlage = positionVerifiee; //prend la valeur de la position qui est en train d'etre vérifiée
                trouve = true;
        }}
        this.positionPlage = position;
        Plage.unpack(positionVerifiee);
    }
    
    
    /*public void deplacement(positionArrivee){
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
    }*/

    public void baignade(){
        if (this.estSauveteur = false){
            if (this.position == plage){
                double baignade = Math.random();
                if (baignade < 0.05){
                    this.etat= Etat.MOUVEMENT;
                    deplacement(); //mettre en paramètre un emplacement libre dans la mer
                }
            }
            if (this.position == Plage.mer){
                double baignade = Math.random();
                if (baignade < 0.05) {
                    this.etat = Etat.MOUVEMENT;
                    deplacement(positionPlage);
                    this.etat = Etat.REPOS;
                }
            }
        }
    }

    public void seNoie(){
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
    }

    public void quittePlage(){
        double partir = Math.random();
        if (partir <= 0.005)
            deplacement(positionPlage);
            Plage.pack();
            deplacement();      //endroit à définir où on part de la plage
            interruption();
    }

    public void run() {
        // Chaque personne a un Etat : ce qu'il fait, et un Objectif : ce qu'il voudrait faire
        // Exemple : si une personne se déplace, son Etat est Etat.MOUVEMENT, et là où il va est dans son objectif : Objectif.BAIGNADE par exemple
        // run() doit faire en sorte que la personne fasse ses actions.
        // Si la personne se déplace, elle doit bouger jusqu'à ce qu'elle atteingne sa destination, case par case, en faisant attention à ce qui l'entoure.
        // La personne va bouger de case en case dans une boucle tous les x millisecondes, avec un Thread.sleep(x) 
        // Si la personne s'arrête quelque part, attendre y temps avant qu'il se remette à bouger
        // A toi de déterminer comment tout se passe, en jouer avec Etat et Objectif et les différents attributs, tu peux en créer aussi si besoin
        // Rappel des actions : se déplacer, se baigner, se reposer, se placer, partir (redoncances ?)
        // Pour le placement, ce sera complété plus tard donc tu peux laisser vide
        // La personne doit vivre ! LET THERE BE LIGHT

        Etat etat;
        Objectif objectif;
        double proba;
        proba = math.random();

        for (int i = 0; i < 100; i++) {
            etat = Etat.MOUVEMENT;
            objectif = Objectif.PLACEMENT;
            Thread.sleep(5000);
            while (proba < 0.9) {
                etat = Etat.Repos;
                Thread.sleep(2000);
            }
            etat = Etat.BAIGNADE;
            objectif = Objectif.BAIGNADE;
            baignade();
            if (seNoie() == true) {
                etat = Etat.NOYADE;
                vaSauver();
            } else {
                etat = Etat.MOUVEMENT;
            }

            objectif = Objectif.PARTIR;
            quittePlage();
        }
    }

    public boolean caselibre(int x, int y, Plage p){
        return p[x][y] == 0;
    }

    public void seplacer(int x, int y, Plage pl, int[][] place){
        Etat etat1;



    }

    public void deplacer(Personne personne, Plage pla){
        Etat etat;
        Objectif object;
        int x = Math.floor(Math.random()) * this.longueur;
        int y = Math.floor(Math.random()) * this.largeur;

        //Tant que la position n'est pas celle d'arrivee.
        while (this.position!=pla[x][y]) {
            // Si la case est vide
            if (caselibre(x, y, pla)) {
                etat = Etat.MOUVEMENT;                   //La persone est en mouvement
                object = Objectif.PLACEMENT;             // avec l'objectif de se placer
                // Si la personne est arrivé
                if (this.position == pla[x][y]) {
                    etat = Etat.PLACEMENT;               // Elle se met alors a se placer
                    unpack(x, y);                        // La Plage considere donc que la case est occupe et vaut 2
                    this.position = pla[x][y];         // La boucle prend fin
                }
            }
            // Si la case est occupe alors on redemande de nouvelle coordonées pour relancer la boucle
            else{
                x = Math.floor(Math.random()) * this.longueur;
                y = Math.floor(Math.random()) * this.largeur;
            }
        }



    }
                
                
            
                
     
    

}



