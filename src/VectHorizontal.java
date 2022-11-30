//package src;

public class VectHorizontal extends Vector {

    public VectHorizontal(double x, double y, double objX, double objY, double vitesse, Coeff coeff, int timing) {
        super(x, y, objX, objY, vitesse, coeff, timing);
    }

    public VectHorizontal(Vector vect) {
        super(vect);
    }

    public VectHorizontal copy() {
        return new VectHorizontal(this);
    } 

    public void glissement() {
        x += sensX*vitesse*coeff.getCoeff();
        if ((sensX == 1 && x > objX) || (sensX == -1 && x < objX)) {
            x = objX;
        }
    }
}