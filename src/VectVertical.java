public class VectVertical extends Vector {

    public VectVertical(double x, double y, double objX, double objY, double vitesse, Coeff coeff, int timing) {
        super(x, y, objX, objY, vitesse, coeff, timing);
    }

    public VectVertical(Vector vect) {
        super(vect);
    }

    public VectVertical copy() {
        return new VectVertical(this);
    } 

    public void glissement() {
        y += sensY*vitesse*coeff.getCoeff();
        if ((sensY == 1 && y > objY) || (sensY == -1 && y < objY)) {
            y = objY;
        }
    }
    
}
