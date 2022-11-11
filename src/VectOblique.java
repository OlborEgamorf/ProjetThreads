public class VectOblique extends Vector {
    private double m, p;

    VectOblique(VectOblique vect) {
        super(vect);
        this.m = vect.m;
        this.p = vect.p;
    }

    public double getM() {
        return m;
    }

    public double getP() {
        return p;
    }

    public void setM() {
        this.m = (objY-y)/(objX-x);
    }

    public void setP() {
        this.p = y-m*x;
    }

    public void glissement() {
        x = x + sens*vitesse / m;
        y = x * m + p;  
        if ((sens == 1 && x > objX && y > objY) || (sens == -1 && x < objX && y < objY)) {
            x = objX;
            y = objY;
        }
    }
}

//(1,2) -> (4,4)
