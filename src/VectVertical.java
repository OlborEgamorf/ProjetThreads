public class VectVertical extends Vector {

    public VectVertical(double x, double y, double objX, double objY, double vitesse, double coeff) {
        super(x, y, objX, objY, vitesse, coeff);
        if (objY > y) {
            sens = 1;
        } else {
            sens = -1;
        }
    }

    public VectVertical(Vector vect) {
        super(vect);
    }

    public VectVertical copy() {
        return new VectVertical(this);
    } 

    public void glissement() {
        y += sens*vitesse;
        if ((sens == 1 && y > objY) || (sens == -1 && y < objY)) {
            y = objY;
        }
    }
    
}
