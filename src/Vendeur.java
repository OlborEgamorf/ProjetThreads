//package src;


import java.sql.SQLOutput;

public class Vendeur extends Personne {

    private Personne personne = null;

    public Vendeur(int id, double[] position, int vent, double[] objposition) {
        super(id, position, vent, 1);
        this.objPosition = objposition;
        this.etat = Etat.PATH;
    }

    public void run() {
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        alive = true;
        int sleeper = 10;

        while (!Thread.interrupted()) {
            sleeper = 20;
            //System.out.println(etat + " " + vecteurCourant + " " + position[0] + " " + position [1]);
            if (etat == Etat.MOUVEMENT && oath) {
                if (personne != null) {
                    //System.out.println("Je vend");
                    objectif = Objectif.COMMERCE;
                    vecteurCourant = null;
                    stackMove.clear();
                } else {
                    if (position[0] == objPosition[0] && position[1] == objPosition[1]) {
                        if (stackMove.size() == 0) {
                            if (objectif == Objectif.COMMERCE) {
                                etat = Etat.COMMERCE;
                            } else if (objectif == Objectif.VENDRE) {
                                etat = Etat.PATH;
                            } else if (objectif == Objectif.PARTIR) {
                                etat = Etat.PARTI;
                            }
                            vecteurCourant = null;
                        } else {
                            vecteurCourant = stackMove.get(0);
                            objPosition = vecteurCourant.getCoordsObj();
                            sleeper = vecteurCourant.getTiming();
                        }
                    } else {
                        vecteurCourant.glissement();
                        position = vecteurCourant.getCoords();
                    }
                }
            } else if (etat == Etat.COMMERCE) {
                sleeper = 15000;
                objectif = Objectif.VENDRE;
                etat = Etat.PATH;
            } else if (position[0] == objPosition[0] && position[1] == objPosition[1]) {
                objectif = Objectif.PARTIR;
            } else if (etat == Etat.PATH && vecteurCourant != null) {
                //System.out.println("Je bouge");
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



