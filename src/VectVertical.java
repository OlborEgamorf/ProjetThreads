public class VectVertical extends Vector {

    public VectVertical(double x, double y, double objX, double objY, double vitesse, int timing) {
        super(x, y, objX, objY, vitesse, timing);
    }

    public VectVertical(Vector vect) {
        super(vect);
    }

    public VectVertical copy() {
        return new VectVertical(this);
    } 

    public void glissement() {
        y += sensY*vitesse;
        if ((sensY == 1 && y > objY) || (sensY == -1 && y < objY)) {
            y = objY;
        }
    }
    
}
