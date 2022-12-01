import java.util.LinkedList;
import java.util.Queue;

//package src;

public class Vendeur extends Personne {

    private Queue<Personne> file = new LinkedList<Personne>();

    public Vendeur(int id, Coordonnees position, int vent, Coordonnees objposition) {
        super(id, position, vent, 1800000);
        this.objPosition = objposition;
        this.etat = Etat.PATH;
        this.vitesse /= 3;
        this.objectif = Objectif.VENDRE;
    }

    public void addFile(Personne personne) {
        file.add(personne);
    }

    public void removeFile() {
        file.remove();
    }

    public int sizeFile() {
        return file.size();
    }

    public boolean containFile(Personne personne) {
        return file.contains(personne);
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
        int sleeper = 10;

        while (!Thread.interrupted()) {
            sleeper = 10;
            //System.out.println(etat + " " + vecteurCourant + " " + position[0] + " " + position [1]);
            if (etat == Etat.MOUVEMENT && oath) {
                if (file.size() != 0) {
                    //System.out.println("Je vend");
                    etat = Etat.COMMERCE;
                } else {
                    if (position.equals(objPosition)) {
                        if (stackMove.size() == 0) {
                            etat = Etat.PARTI;
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
                file.peek().etat = Etat.ACHETER;
                if (file.size() == 0) {
                    etat = Etat.MOUVEMENT;
                } else if (!file.peek().volonteAcheter) {
                    file.remove();
                    for (Personne acheteurs : file) {
                        acheteurs.etat = Etat.PATH;
                    }
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



