public class VectHorizontal extends Vector {

    public VectHorizontal(double x, double y, double objX, double objY, double vitesse, double coeff) {
        super(x, y, objX, objY, vitesse, coeff);
        if (objX > x) {
            sens = 1;
        } else {
            sens = -1;
        }
    }

    public VectHorizontal(Vector vect) {
        super(vect);
    }

    public VectHorizontal copy() {
        return new VectHorizontal(this);
    } 

    public void glissement() {
        x += sens*vitesse;
        if ((sens == 1 && x > objX) || (sens == -1 && x < objX)) {
            x = objX;
        }
    }
}