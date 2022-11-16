/*public class Sauveteur extends Personne {

    public Sauveteur(int id, double[] position, int vent, Rectangle poste) {
        super(id, position, vent, 1);
        positionPlage = poste;
    }

    public void setAge() {
        this.age = (int)Math.floor(18 + Math.random() * 8);
    }

    public void sauvetage(double[] position) {
        etat = Etat.MOUVEMENT;
        objectif = Objectif.SAUVETAGE;
        objPosition = position;
    }
    
    public void run() {

        alive = true;
        int sleeper = 10;
        int iterStatique = 0;

        while (!Thread.interrupted()) {
            sleeper = 20;

            if (etat == Etat.MOUVEMENT && oath) {

                if (position[0] == objPosition[0] && position[1] == objPosition[1]) {
                    if (objectif == Objectif.PATROUILLE) {
                        etat = Etat.ATTENTE;
                    } else if (objectif == Objectif.SAUVETAGE) {
                        etat = Etat.SAUVETAGE;
                    } else if (objectif == Objectif.REPOS) {
                        etat = Etat.ATTENTE;
                    }

                } else {
                    if (objectif == Objectif.SAUVETAGE) {
                        sleeper = (int)vitesse*2;
                    } else {
                        sleeper = (int)vitesse/2;
                    }
                    vecteur.glissement();
                }
                
            } else if (etat == Etat.REPOS) {
                sleeper = 100;
                iterStatique += 1;
                if (iterStatique == 50) {
                    etat = Etat.MOUVEMENT;
                    if (Plage.comparePositions(position, positionPlage)) {
                        objectif = Objectif.PATROUILLE;
                        objPosition = ;
                    } else {
                        objectif = Objectif.REPOS;
                        objPosition = positionPlage;
                    }
                    
                }
            }

            oath = false;
            try {
                Thread.sleep(sleeper);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    
    }

}
*/