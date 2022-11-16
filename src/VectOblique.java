public class VectOblique extends Vector {
    private double m, p;

    public VectOblique(double x, double y, double objX, double objY, double vitesse, double coeff, int timing) {
        super(x, y, objX, objY, vitesse, coeff,timing);
        setM();
        setP();

        //System.out.println(x+" "+y+" "+objX+" "+objY+" V:"+vitesse+" "+m+" "+p+" "+sensX+" "+sensY);
    }

    VectOblique(VectOblique vect) {
        this(vect.x,vect.y,vect.objX,vect.objY,vect.vitesse,vect.coeff,vect.timing);
    }

    public VectOblique copy() {
        return new VectOblique(this);
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
        x += sensX*Math.sqrt(Math.pow(vitesse,2)/(Math.pow(m,2)+1));
        y = m*x +p;
        if ((sensX == 1 && sensY == 1 && x >= objX && y >= objY) || (sensX == 1 && sensY == -1 && x >= objX && y <= objY) || (sensX == -1 && sensY == 1 && x <= objX && y >= objY) || (sensX == -1 && sensY == -1 && x <= objX && y <= objY)) {
            x = objX;
            y = objY;
        }
    }
}
