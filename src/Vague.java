//package src;

import java.util.ArrayList;

public class Vague extends Thread{
    private double hauteur;
    private double vitesse;
    private double force;
    private double positionY;
    private double positionMer;
    private Vector vecteurCourant;
    private ArrayList<Vector> stackMove = new ArrayList<Vector>();
    private int longueur;
    private int plagePlusMer;

    public Vague(double hauteur, double vitesse, double force, int longueur, double positionY, double positionMer, int plagePlusMer){
        this.hauteur = hauteur;
        this.vitesse = vitesse;
        this.force = force;
        this.longueur = longueur;
        this.positionY = positionY;
        this.positionMer = positionMer;
        this.plagePlusMer = plagePlusMer;
        this.vecteurCourant = Vector.choixVector(new double[]{positionY, 0}, new double[]{positionMer, 0},vitesse/250,10);
        stackMove.add(vecteurCourant);
    }

    public double getHauteur(){
        return this.hauteur;
    }

    public double getVitesse(){
        return this.vitesse;
    }

    public double getForce(){
        return this.hauteur;
    }

    public double getPositionY() {
        return this.positionY;
    }

    public double getPositionMer() {
        return this.positionMer;
    }

    public int getLongueur(){
        return this.longueur;
    }

    public void run(){
        while (!Thread.interrupted()){
            if ((int) (this.positionY) == this.vecteurCourant.objX || (int) (this.positionY) == this.vecteurCourant.objX-1){
                this.positionY = plagePlusMer;
                this.vecteurCourant = Vector.choixVector(new double[]{positionY, 0}, new double[]{positionMer, 0},vitesse/250,10);
                stackMove.add(vecteurCourant);
                stackMove.remove(0);
            }
            else{
                vecteurCourant.glissement();
                this.positionY = vecteurCourant.getCoords()[0];
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
