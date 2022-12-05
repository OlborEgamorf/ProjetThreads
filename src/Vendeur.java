import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//package src;

public class Vendeur extends Personne {

    private Queue<Personne> file = new LinkedList<Personne>();
    private ArrayList<Personne> arrive = new ArrayList<Personne>();

    public Vendeur(int id, Coordonnees position, int vent, Coordonnees objposition) {
        super(id, position, vent, 1200000);
        this.objPosition = objposition;
        this.etat = Etat.PATH;
        this.vitesse /= 3;
        this.objectif = Objectif.VENDRE;
    }

    public void addArrive(Personne personne) {
        arrive.add(personne);
    }

    public void removeArrive(Personne personne) {
        arrive.remove(personne);
    }

    public boolean containArrive(Personne personne) {
        return arrive.contains(personne);
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

    public Personne getHead(){
        return file.peek();
    }

    public void coming(Personne personne) {
        arrive.remove(personne);
        file.add(personne);
        personne.etat = Etat.ENFILE;

        for (Personne acheteurs : arrive) {
            acheteurs.objPosition = new Coordonnees(position.getX(),position.getY()-sizeFile()-1);
            acheteurs.vecteurCourant = null;
            acheteurs.stackMove.clear();
            acheteurs.etat = Etat.PATH;
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
        int sleeper = 10;

        while (!Thread.interrupted()) {
            sleeper = 10;
            //System.out.println(etat + " " + vecteurCourant + " " + position[0] + " " + position [1]);
            if (etat == Etat.MOUVEMENT && oath) {
                if (arrive.size() != 0 || file.size() != 0) {
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
                
                if (file.size() == 0 && arrive.size() == 0) {
                    etat = Etat.MOUVEMENT;
                } else if (file.size() != 0) {
                    if (file.peek().etat == Etat.ENFILE) {
                        file.peek().etat = Etat.ACHETER;
                    } else if (!file.peek().volonteAcheter) {
                        file.remove();
                        int i = 0;
                        for (Personne acheteurs : file) {
                            acheteurs.objPosition = new Coordonnees(position.getX(), position.getY()-i-1);
                            acheteurs.objectif = Objectif.FILE;
                            acheteurs.etat = Etat.PATH;
                            i++;
                        }
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



