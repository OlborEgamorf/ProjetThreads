//package src;

public class Sauveteur extends Personne {

    private int idSave = -1;

    public Sauveteur(int id, double[] position, int vent, Rectangle poste) {
        super(id, position, vent, 1);
        positionPlage = poste;
        etat = Etat.REPOS;
        vitesseNage = 0.2133;
    }

    public void setAge() {
        this.age = (int)Math.floor(18 + Math.random() * 8);
    }

    public int getIdSave() {
        return idSave;
    }

    public void sauvetage(double[] position, int idSave) {
        etat = Etat.PATH;
        objectif = Objectif.SAUVETAGE;
        objPosition = position;
        this.idSave = idSave;
    }
    
    public void run() {

        alive = true;
        int iterStatique = 0;
        int tempsPatrouille = 0;

        while (!Thread.interrupted()) {
            int sleeper = 10;
            if (etat == Etat.MOUVEMENT) {
                //System.out.println(position[0]+" "+objPosition[0]+" "+position[1]+" "+objPosition[1]);
                if (position[0] == objPosition[0] && position[1] == objPosition[1]) {
                    stackMove.remove(0);
                    if (stackMove.size() == 0) {
                        vecteurCourant = null;
                        if (objectif == Objectif.PATROUILLE) {
                            etat = Etat.ATTENTE;
                        } else if (objectif == Objectif.SAUVETAGE) {
                            etat = Etat.SAUVETAGE;
                        } else if (objectif == Objectif.REPOS) {
                            etat = Etat.REPOS;
                        }
                    } else {
                        vecteurCourant = stackMove.get(0);
                        objPosition = vecteurCourant.getCoordsObj();
                        sleeper = vecteurCourant.getTiming();
                    } 

                } else {
                    vecteurCourant.glissement();
                    position = vecteurCourant.getCoords();
                }
                
            } else if (etat == Etat.ATTENTE) {
                iterStatique += 10*Coeff.getCoeff();
                if (iterStatique >= 60000) {
                    tempsPatrouille += 1;
                    if (tempsPatrouille == 10) {
                        objectif = Objectif.REPOS;
                        objPosition = positionPlage.getCentre();
                        tempsPatrouille = 0;
                    } else {
                        objectif = Objectif.PATROUILLE;
                    }
                    etat = Etat.PATH;
                    iterStatique = 0;
                }

            } else if (etat == Etat.REPOS) {
                iterStatique += 10*Coeff.getCoeff();
                if (iterStatique >= 1200000) {
                    objectif = Objectif.PATROUILLE;
                    etat = Etat.PATH;
                    iterStatique = 0;
                }

            } else if (etat == Etat.PATH && vecteurCourant != null) {
                etat = Etat.MOUVEMENT;
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
